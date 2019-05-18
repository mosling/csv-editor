package net.msk.csvedit;

import net.msk.csvedit.CSVDatatypes.CSVDatatype;
import net.msk.csvedit.CSVDatatypes.CSVString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Diese Klasse enth&auml;lt genau eine Zeile der Tabelle. Eine Zeile
 * besteht aus einem Vektor von Objekten, Objekte sind n&ouml;tig damit die
 * verschiedenen Typen richtig gespeichert werden k&ouml;nnen. Diese Information
 * spielt f&uuml;r Java keine Rolle da keine templates unterst"utzt werden.
 */
public class CSVLine extends Vector implements Comparable
{
    static Logger LOGGER = LogManager.getLogger(CSVLine.class);
    private Vector tablis;

    /**
     * Erzeugt eine neue Zeile, es wird ein neuer Vektor initialisiert.
     */
    public CSVLine()
    {
        super();
        tablis = new Vector();
    }

    /**
     * Es wird das i-te Element des Vektor geliefert, liegt i au&szlig;erhalb
     * der Vektorgrenzen liefert diese Methode null.
     */
    public Object get(int i)
    {
        return (i >= size()) ? null : super.get(i);
    }

    /**
     * Es wird eine Zeile gel&ouml;scht, dabei werden alle angemeldeten Sichten
     * (CSVTable) informiert, da&szlig; diese Zeile gel&ouml;scht wird.
     */
    public void remove()
    {
        for (Enumeration e = tablis.elements(); e.hasMoreElements(); )
        {
            CSVTable t = (CSVTable) e.nextElement();
            t.deleteInternalRow(this);
        }
        tablis = null;
    }

    /**
     * Falls eine CSVTable diese Zeile anzeigen m"ochte, sollte sich die CSVTable
     * bei der Zeile anmelden, um zu erfahren wenn die Zeile gel&ouml;scht wird.
     */
    public void addTableListener(CSVTable t)
    {
        tablis.add(t);
        System.out.println("adding " + t.toString());
    }

    /**
     * Ausgabe dieser Zeile auf Standardout, nur zu Testzwecken benutzen als
     * Ausgabe der Daten wird die Klasse CSVWriter verwendet.
     */
    public void output()
    {
        for (int i = 0; i < size(); i++)
        {
            System.out.print(get(i).toString());
            if (i < size() - 1) System.out.print("|");
        }
        System.out.println();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.<p>
     *
     * @param o the Object to be compared.
     */
    public int compareTo(Object o)
    {
        Object o1 = get(CSVTable.getSortCol());
        Object o2 = ((CSVLine) o).get(CSVTable.getSortCol());
        try
        {
            return ((CSVDatatype) (CSVTable.getSortDatatype())).compare(o1, o2);
        } catch (Exception e)
        {
            LOGGER.warn("ungeeigneter CSV Datentyp in Formatdatei");
            // Vergleich erfolgt auf lexikografische Weise
            return (new CSVString()).compare(o1, o2);
        }
    }
}