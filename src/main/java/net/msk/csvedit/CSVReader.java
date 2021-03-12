package net.msk.csvedit;

import net.msk.csvedit.CSVDatatypes.CSVDatatype;
import net.msk.csvedit.CSVDatatypes.CSVString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Der CSVReader liest eine Datei ein und zerlegt sie in die Bestandteile. Dies
 * sind die Zeilen und deren Werte. Ebenso wird ein &Uuml;berschriftenvektor und
 * ein Datentypvektor angelegt. Der hier erstellte Vektor stellt die Basis aller
 * Sichten dar, diese verwenden nur Referenzen auf diese Daten.
 */
public class CSVReader
{
    static Logger LOGGER = LogManager.getLogger(CSVReader.class);
    private boolean useFormatFile;
    private String filename;
    // Kopfzeile der Tabelle
    private CSVLine header;
    private List<String> preHeaderLines = new ArrayList<>();
    // Vektor von CSVLines
    private CSVLine csvdata;
    // Vektor von CSVDatatype Instanzen
    private Vector datatype;

    /**
     * Creates new CSVReader
     */
    public CSVReader(String filename)
    {
        this.filename = filename;
        header = null;
        datatype = new Vector();
        csvdata = new CSVLine();
    }

    /**
     * Gibt den Vektor, der die Spaltennamen enthält zurück.
     */
    public CSVLine getHeader()
    {
        return header;
    }

    /**
     * Gibt an, ob eine Formatierungsdatei für den Datensatz verwendet wird.
     */
    public boolean isFormatFileUsed()
    {
        return useFormatFile;
    }

    /**
     * Gibt an, ob eine Formatierungsdatei für den Datensatz verfügbar ist.
     */
    public boolean isFormatFileAvailable()
    {
        String filenameFormatFile = getFormatFilename();
        try
        {
            new FileReader(filenameFormatFile);
        } catch (FileNotFoundException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Gib den Vektor mit den Datentypen zurück.
     */
    public Vector getDataTypes()
    {
        return datatype;
    }

    /**
     * Gib den Vektor mit den eigentlichen Daten zur&uuml;ck. Dies ist ein
     * Vektor von CSVLines.
     */
    public Vector getData()
    {
        return csvdata;
    }

    /**
     * Es wird eine Datei geöffnet und als CSV-Datei verarbeitet, d.h. die Felder
     * werden durch den übergebenen Trenner (sep) getrennt. Falls direkt nach und
     * vor einem Trenner Quotezeichen stehen wird der quotierte Text zum Feld.
     * Die erste Zeile enthält die Spaltenüberschriften.
     */
    public void readFile()
    {
        useFormatFile = false;
        header = null;
        // die Zeichen zwischen Spaltenname und Datentyp
        char sep = CSVEditor.csvproperties.getSeparationSign();
        char qsign = CSVEditor.csvproperties.getQuotationSign();
        int hl = CSVEditor.csvproperties.getHeaderLines();
        String dataline;
        preHeaderLines.clear();

        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(filename));
            while ((dataline = in.readLine()) != null)
            {
                if (hl > 0)
                {
                    preHeaderLines.add(dataline);
                    hl--;
                    continue;
                }
                if (dataline.replaceAll(" ", "").equals(""))
                {
                    continue;
                }
                String[] sepsrc = dataline.split(String.valueOf(sep));
                CSVLine l = new CSVLine(); // eine Zeile im Datensatz
                boolean quoted = false; // ist die Zelle gequotet?
                StringBuilder currcell = new StringBuilder();
                int colcount = 0;
                for (int i = 0; i < sepsrc.length; i++)
                {
                    if (!quoted && (sepsrc[i].length() > 0) && (sepsrc[i].charAt(0) == qsign))
                    {
                        sepsrc[i] = sepsrc[i].substring(1);
                        quoted = true;
                    }
                    if (quoted && (sepsrc[i].length() > 0) && (sepsrc[i].charAt(sepsrc[i].length() - 1) == qsign))
                    {
                        sepsrc[i] = sepsrc[i].substring(0, sepsrc[i].length() - 1);
                        quoted = false;
                    }
                    // solange quoted => in cell schreiben, sonst => dem Vektor hinzufügen
                    if (quoted)
                    {
                        currcell.append(sepsrc[i]).append(sep);
                    }
                    else
                    {
                        currcell.append(sepsrc[i]);
                        l.add(currcell.toString());
                        colcount++;
                        //als Datentyp wird CSVString verwendet
                        while (datatype.size() < colcount)
                        {
                            datatype.add(new CSVString(""));
                        }
                        currcell = new StringBuilder();
                    }
                }
                if (header == null)
                {
                    header = l;
                }
                else
                {
                    csvdata.add(l);
                }
            }//endwhile
        } catch (IOException e)
        {
            LOGGER.warn("failure at reading datafile: " + e.toString());
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException e)
            {
                LOGGER.debug("failure at closing datafile: " + e.toString());
            }
            ;
        }
    }

    /**
     * Speichert die aktuelle Tabelle als Datensatz-Textdatei.
     *
     * @param fileName   Name der Textdatei
     * @param withHeader gibt an, ob ein Header mit abgespeichert werden soll
     */
    public void saveFile(String fileName, boolean withHeader)
    {
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            // Stored Pre Header lines are written
            if (preHeaderLines.size() > 0)
            {
                for (String l : preHeaderLines)
                {
                    bw.write(l);
                    bw.newLine();
                }
            }

            // Header-Zeile wird geschrieben
            if (withHeader)
            {
                Enumeration headerEl = this.getHeader().elements();
                for (; headerEl.hasMoreElements(); )
                {
                    bw.write(headerEl.nextElement().toString());
                    if (headerEl.hasMoreElements())
                    {
                        bw.write(CSVEditor.csvproperties.getSeparationSign());
                    }
                }
                bw.newLine();
            }
            ;
            // Tabelle wird geschrieben
            for (Enumeration outerEl = this.getData().elements(); outerEl.hasMoreElements(); )
            {
                Vector innerVec = (Vector) outerEl.nextElement();
                Enumeration edatatype = getDataTypes().elements();
                for (Enumeration innerEl = innerVec.elements(); innerEl.hasMoreElements(); )
                {
                    Object entry = innerEl.nextElement();
                    Object entrytype;
                    entrytype = (edatatype.hasMoreElements() ? edatatype.nextElement() :
                            new String());
                    if (entrytype.getClass() == String.class)
                    {
                        bw.write(CSVEditor.csvproperties.getQuotationSign());
                    }
                    bw.write(entry.toString());
                    if (entrytype.getClass() == String.class)
                    {
                        bw.write(CSVEditor.csvproperties.getQuotationSign());
                    }
                    if (innerEl.hasMoreElements())
                    {
                        bw.write(CSVEditor.csvproperties.getSeparationSign());
                    }
                }
                bw.newLine();
            }
        } catch (IOException e)
        {
            LOGGER.debug("failure at saving file: " + e.toString());
        } finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }
            } catch (IOException e)
            {
                LOGGER.debug("failure at closing datafile: " + e.toString());
            }
            ;
        }
    }

    public String getFormatFilename()
    {
        return filename + ".format";
    }

    /**
     * Falls eine Format-Datei verwendet werden soll, dann muss diese Methode
     * aufgerufen werden, um die neuen Header und Datentypen der bereits in den
     * Reader geladen CSV-Datei zu setzen. Die Format-Datei liegt im selben
     * Verzeichnis, wie die CSV-Datei und heisst auch genauso, besitzt aber
     * zusaetzlich die Extension .format
     */
    public void readFormatFile()
    {
        String filenameFormatFile = getFormatFilename();
        useFormatFile = true;
        header = (CSVLine) getHeaderFromFormatFile(filenameFormatFile);

        int colcount = datatype.size();
        datatype = getDatatypesFromFormatFile(filenameFormatFile);
        //für in Formatdatei nicht aufgelistete Spalten wird CSVString verwendet
        while (datatype.size() < colcount)
        {
            datatype.add(new CSVString(""));
        }
    }

    /**
     * Es wird eine Datei geöffnet und als CSVFormat-Datei verarbeitet,
     * Es wird ein Vector mit dummy-Instanzen der CSV Datentypen geliefert
     */
    private Vector getDatatypesFromFormatFile(String fnameFormatFile)
    {
        // die Zeichen zwischen Spaltenname und Datentyp
        String sep = "::";
        String dataline;
        Vector mandatoryFlags = new Vector();
        Vector datatypesStr = new Vector();
        Vector datatypesStrParameters = new Vector();
        Vector datatypesObj = new Vector();

        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(fnameFormatFile));
            while ((dataline = in.readLine()) != null)
            {
                if (dataline.replaceAll(" ", "").equals(""))
                {
                    continue;
                }
                String[] src = dataline.split(sep);
                String[] dest = new String[(src.length >= 2) ? src.length - 2 : 0];
                try
                {
                    if (src[1].equalsIgnoreCase("m"))
                    {
                        datatypesStr.add(src[2]);
                        mandatoryFlags.add(Boolean.TRUE);
                        System.arraycopy(src, 3, dest, 0, src.length - 3);
                    }
                    else
                    {
                        datatypesStr.add(src[1]);
                        mandatoryFlags.add(Boolean.FALSE);
                        System.arraycopy(src, 2, dest, 0, src.length - 2);
                    }
                } catch (IndexOutOfBoundsException e)
                {
                    datatypesStr.add("CSVString");
                    mandatoryFlags.add(Boolean.FALSE);
                }
                datatypesStrParameters.add(dest);
            }
        } catch (FileNotFoundException e)
        {
            LOGGER.warn("failure at reading formatfile: " + e.toString());
            useFormatFile = false;
        } catch (IOException e)
        {
            LOGGER.debug(e.toString());
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException e)
            {
                LOGGER.debug("failure at closing datafile: " + e.toString());
            }
        }
        /**
         * In datatypesStr liegen die Datentypen nun als Strings vor.
         * Es werden nun die in der Klasse gleichlautenden Dummy-Objekte erzeugt.
         */
        Enumeration eds = datatypesStr.elements();
        Enumeration em = mandatoryFlags.elements();
        Enumeration edsp = datatypesStrParameters.elements();
        while (eds.hasMoreElements())
        {
            Class colclass;
            String dtclassname = eds.nextElement().toString();
            String[] parameters = (String[]) edsp.nextElement();
            boolean isMandatory = ((Boolean) em.nextElement()).booleanValue();

            try
            {
                colclass = Class.forName("net.msk.csvedit.CSVDatatypes." + dtclassname);
            } catch (ClassNotFoundException e)
            {
                LOGGER.debug("net.msk.csvedit.CSVDatatypes." + dtclassname + " nicht definiert, " + "stattdessen wird CSVString verwendet");
                colclass = CSVString.class;
            }
            try
            {
                CSVDatatype newcolclass = (CSVDatatype) colclass.newInstance();
                newcolclass.init(parameters);
                newcolclass.setMandatory(isMandatory);
                datatypesObj.add(newcolclass);
            } catch (Exception e)
            {
                LOGGER.debug("Fehler bei der Erzeugung einer Instanz: " + e.toString());
            }

        }
        return datatypesObj;
    }

    /**
     * Es wird eine Datei geöffnet und als CSVFormat-Datei verarbeitet. Es wird
     * eine CSVLine mit dem Tabellenheader geliefert.
     */
    private CSVLine getHeaderFromFormatFile(String fnameFormatFile)
    {
        // die Zeichen zwischen Spaltenname und Datentyp
        String sep = "::";
        String dataline;
        CSVLine colnames = new CSVLine();

        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(fnameFormatFile));
            while ((dataline = in.readLine()) != null)
            {
                if (dataline.replaceAll(" ", "").equals(""))
                {
                    continue;
                }
                colnames.add(dataline.split(sep)[0]);
            }
        } catch (FileNotFoundException e)
        {
            LOGGER.warn("missing format file " + fnameFormatFile);
            useFormatFile = false;
        } catch (IOException e)
        {
            LOGGER.debug(e.toString());
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException e)
            {
                LOGGER.debug("failure at closing datafile: " + e.toString());
            }
        }
        return colnames;
    }
}