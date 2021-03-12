package net.msk.csvedit;

import gnu.regexp.RE;
import gnu.regexp.REException;
import net.msk.csvedit.CSVDatatypes.CSVDatatype;
import net.msk.csvedit.CSVDatatypes.CSVInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Vector;

/**
 * Implementierung des abstrakten Tabellenmodelles. Dar&uuml;ber hinaus
 * k&ouml;nnen vedeckte Spalten verwaltet werden, dies bedeutet das bei
 * einem Zugriff die verdeckten Spalten herausgenommen werden und der Nutzer
 * den Eindruck gewinnt, da&szlig; weniger Spalten als wirklich existieren.
 */
public class CSVTable extends javax.swing.table.AbstractTableModel implements Cloneable
{
    private static final Logger LOGGER = LogManager.getLogger(CSVTable.class);

    private static CSVReader reader;
    // zur Kontrolle über das Sortieren, wird von CSVLines abgefragt
    private static Object sortclass;
    private static int sortcol = -1;
    private Vector data = new Vector(); // <CSVLine>
    private Vector hiddencols = new Vector(); // <int>
    //aktuelle Einträge der ausgeblendeten Spalten
    private Vector hiddenColEntries = new Vector();
    private String tablename = new String();

    //    // falls manuell auf die Felder in der Tabelle zugegriffen werden soll,
    //    // dann auskommentieren<
    //    public boolean isCellEditable(int row, int col) {
    //        return true;
    //    }
    private int cols;

    /**
     * Erzeugt eine Instanz von CSVTable. Per default ist die Datenbasis leer.
     * Mit setData kann sie gefuellt werden. Mit setDataByReader wird die Datenbasis
     * von der Klassenvariable reader (ein CSVReader) gefuellt.
     */
    public CSVTable(String name, CSVReader r)
    {
        reader = r;
        tablename = name;
        cols = 0;
    }

    /**
     * liefert die mit sort zuletzt sortierte Spaltennummer,
     * falls noch nicht sortiert wurde, dann -1
     **/
    static public int getSortCol()
    {
        return sortcol;
    }

    private void setSortCol(int col)
    {
        sortcol = col;
        sortclass = getColumnDatatype(sortcol);
    }

    /**
     * liefert den Spalten
     **/
    static public Object getSortDatatype()
    {
        if (getSortCol() == -1) LOGGER.error("Aufruf der Methode getSortDatatype() vor sort(int) nicht erlaubt!");
        return sortclass;
    }

    /**
     * Die Datenbasis dieser Instanz der CSVTable wird hiermit angegeben.
     * Es wird die Datenbasis von der Klassenvariable reader (ein CSVReader) gefuellt.
     */
    public void setDataByReader()
    {
        setData((Vector) reader.getData());
    }

    /**
     * es wird eine deep copy angelegt, d.h. alle Daten in den Vektoren dieser
     * Klasse werden gecloned. der CSVReader ist jedoch eine Klassenvariable und
     * bleibt für alle CSVTables gleich.
     */
    public Object clone()
    {
        CSVTable clonedTable = new CSVTable("clone of " + this.tablename, this.reader);
        clonedTable.setData((Vector) this.data.clone());
        if (this.hasHiddenCols())
        {
            // clone the hidden columns
            Vector v = this.getHiddenCol();
            for (int i = 0; i < v.size(); i++)
            {
                clonedTable.addHiddenCol(((Integer) v.elementAt(i)).intValue());
            }
            // clone the hidden columns entries
            clonedTable.hiddenColEntries = (Vector) this.hiddenColEntries.clone();
        }
        return clonedTable;
    }

    /**
     * Liefert den Vektor aller verdeckten Spalten.
     */
    public Vector getHiddenCol()
    {
        return hiddencols;
    }

    /**
     * Liefert einen Vektor mit den Eintraegen aller verdeckten Spalten.
     */
    public Vector getHiddenColEntries()
    {
        return hiddenColEntries;
    }

    /**
     * Liefert true falls nicht alle Spalten sichtbar sind.
     */
    public boolean hasHiddenCols()
    {
        return (hiddencols != null);
    }

    /**
     * Damit die sp&auml;tere Bestimmung der tats&auml;chlichen Spalte
     * funktioniert, mu&szlig; die Liste der unsichtbaren Spalten sortiert
     * sein. Dies erfolgt durch ein simples O(n^2) insertion sort, wobei der
     * Einf&uuml;geschritt gleich beim Aufnehmen der Spalte geschieht.
     */
    private void addHiddenCol(int c)
    {
        if (hiddencols == null)
        {
            hiddencols = new Vector();
            hiddencols.add(c);
            return;
        }

        int pos = -1;

        for (int i = 0; i < hiddencols.size(); i++)
        {
            if ((Integer) hiddencols.elementAt(i) < c)
                pos = i;
        }
        hiddencols.insertElementAt(c, pos + 1);
    }

    /**
     * @param entry der Eintrag der defaultmässig für diese Spalte steht
     */
    public void addHiddenCol(int c, String entry)
    {
        addHiddenCol(c);
        if (hiddenColEntries.size() < c + 1) hiddenColEntries.setSize(c + 1);
        hiddenColEntries.set(c, entry);
    }

    /**
     * Setze den Namen der Tabelle, dieser erscheint im Selektionsbaum und wird
     * durch die Methode toString geliefert
     **/
    public void setName(String s)
    {
        tablename = s;
    }

    public String toString()
    {
        return tablename;
    }

    public CSVReader getReader()
    {
        return reader;
    }

    public Vector getData()
    {
        return data;
    }

    /**
     * Die Datenbasis dieser Instanz der CSVTable wird hiermit angegeben.
     */
    public void setData(Vector newData)
    {
        data = newData;
        computeCols();
    }

    //    /**
    //     * Liefert den Klassentyp der Spalte, diese wird vom CSVReader ermittelt
    //     * und kann dort im Vektor datatype erfragt werden.
    //     * @param param die Spalte DIESER Tabeller (d.h.) ohne Beachtung der hiddencols
    //     */
    //    public java.lang.Class getColumnClass(int param) {
    //        param=getRealCol(param);
    //        return reader.getDataType().elementAt(param).getClass();
    //    }

    public Vector getTableLines()
    {
        return data;
    }

    /**
     * Liefert die Anzahl der sichtbaren Spalten, falls cols==0 ist, wird zuerst die Anzahl
     * berechnet.
     */
    public int getColumnCount()
    {
        if (cols == 0) computeCols();
        if (hiddencols == null)
            return cols;
        else
            return cols - hiddencols.size();
    }

    /**
     * Anzahl der Zeilen, wird jedesmal bestimmt, damit m&uuml;ssen beim
     * Einf&uuml;gen bzw. L&ouml;schen keine weiteren Vorkehrungen
     * getroffen werden.
     */
    public int getRowCount()
    {
        return data.size();
    }

    /**
     * Name der Spalte, falls param au&szlig;erhalb der Grenzen liegt,
     * wird die Zeichenkette "" geliefert.
     */
    public String getColumnName(int param)
    {
        int rcol = this.getRealCol(param);
        if (rcol >= reader.getHeader().size())
            return "";
        else
            return (String) reader.getHeader().get(rcol);
    }

    /**
     * Falls die Zeile existiert, wird ein Objekt vom Typ CSVLine geliefert.
     */
    public CSVLine getRow(int r)
    {
        if (r >= data.size())
            return null;
        else
            return (CSVLine) data.get(r);
    }

    /**
     * Liefert den DatentypWrapper der Spalte, diese wird vom CSVReader ermittelt
     * und kann dort im Vektor datatype erfragt werden.
     *
     * @param param die Spalte DIESER Tabeller (d.h.) ohne Beachtung der hiddencols
     */
    public Object getColumnDatatype(int param)
    {
        param = getRealCol(param);
        return reader.getDataTypes().elementAt(param);
    }

    public java.lang.Object getValueAt(int row, int col)
    {
        return ((CSVLine) data.get(row)).get(this.getRealCol(col));
    }

    public void setValueAt(java.lang.Object obj, int row, int col)
    {
        ((CSVLine) data.get(row)).set(getRealCol(col), obj);
    }

    /**
     * Diese Methode ermittelt die Anzahl der Spalten und f&uuml;gt das Objekt
     * zu den Tabellen zu der CSVLine hinzu, welche bei &Aumml;nderungen
     * informiert werden sollen.
     */
    public void computeCols()
    {
        int c = 0;

        for (int i = 0; i < data.size(); i++)
        {
            if ((c = ((CSVLine) data.get(i)).size()) > cols) cols = c;
            ((CSVLine) data.elementAt(i)).addTableListener(this);
        }

    }

    /**
     * Diese Methode sortiert die Tabelle nach den Sortierkriterien der Spalte col
     *
     * @param col zu sortierende Spalte
     */
    public void sort(int col)
    {
        setSortCol(col);
        java.util.Collections.sort(getTableLines());
    }

    /**
     * Diese Methode wird von CSVLine aufgerufen und l&ouml;scht eine Zeile
     * aus der Tabelle.
     */
    public void deleteInternalRow(CSVLine l)
    {
        data.remove(l);
    }

    public void deleteRow(int row)
    {
        ((CSVLine) data.elementAt(row)).remove();
    }

    /**
     * Wir erzeugen einen neuen Vektor und hoffen, da&szlig; der garbage collector
     * die alten Daten irgenwann entsorgt.
     */
    public void deleteAllRows()
    {
        data = null;
        data = new Vector();
        cols = 0;
    }

    public void addRow(CSVLine l)
    {
        data.add(l);
    }

    /**
     * Diese Funktion berechnet zur gew"unschten Spalte die tats"achliche
     * Spalte. Das hei&szlig;t es werden unsichtbare Spalten &uuml;bersprungen.
     */
    public int getRealCol(int col)
    {
        if (hiddencols == null)
        {
            return col;
        }
        for (int i = 0; i < hiddencols.size(); i++)
        {
            if (col >= ((Integer) hiddencols.elementAt(i)).intValue())
                col++;
        }
        return col;
    }

    public void deleteColumn(int col)
    {
        for (int i = 0; i < data.size(); i++)
        {
            CSVLine l = (CSVLine) data.get(i);
            if (l.size() > col) l.remove(col);
        }
        reader.getHeader().remove(col);
        reader.getDataTypes().remove(col);
        // the number of columns is reduced by 1
        cols--;
    }

    /**
     * Diese Funktion beschreibt das Ver"andern der vorhandenen Daten, dabei
     * soll dem Nutzer sp"ater datentypangepa"st ein Dialog erscheinen der
     * folgenden Aktionen beinhalten soll
     * <ul>
     * <li> F&uuml;llen mit einem Wert (set) </li>
     * <li> numerische Typen
     * <ul>
     * <li> Eingabe einer Funktion (compute) </li>
     * </ul></li>
     * <li> Zeichenketten
     * <ul>
     * <li> Suchen und Ersetzen (substitute)</li>
     * </ul> </li>
     * </ul>
     */
    public void changeData(int col)
    {
        boolean numeric = ((CSVDatatype) (reader.getDataTypes().elementAt(getRealCol(col)))).isNumerical();
        if (!numeric)
        {
            EditSubstDialog sdia = new EditSubstDialog(null, true);
            sdia.show();
            try
            {
                RE re = new RE(sdia.getSearch());

                for (int i = 0; i < data.size(); i++)
                {
                    if (sdia.getAll())
                        setValueAt(re.substituteAll((String) getValueAt(i, col), sdia.getSubst()), i, col);
                    else
                        setValueAt(re.substitute((String) getValueAt(i, col), sdia.getSubst()), i, col);
                }
            } catch (REException e)
            {
                System.err.println(e.getMessage());
            }
        } else // a numerical value
        {
            ComputeExpression ce = new ComputeExpression();
            EditNumDialog edia = new EditNumDialog(null, true);

            edia.show();
            ce.setExpression(edia.getFunction());

            for (int i = 0; i < data.size(); i++)
            {
                Double operand;
                try
                {
                    operand = new Double(getValueAt(i, col).toString());
                } catch (NumberFormatException e)
                {
                    operand = new Double("0.0");
                }
                ce.putVariable("x", operand);
                Object calcValue = ce.computeExpr();
                if (getColumnDatatype(col).getClass() == CSVInteger.class)
                    calcValue = new Integer(Math.round(Float.parseFloat(calcValue.toString())));
                setValueAt(calcValue, i, col);
            }
        }
    }
}