package net.msk.utils.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Access
{
    private static Logger LOGGER = LogManager.getLogger(Access.class);

    /**
     * Diese statische Methode liefert einen InputStream auf eine Resource.
     * Die Resource wird durch einen Paketnamen und einen Dateinamen identifiziert.
     * Die Pakete werden durch Punkte getrennt. Innerhalb der Funktion werden die Punkte innerhalb
     * der Pakete durch /-Zeichen ersetzt und es wird ein f&uuml;hrendes /-Zeichen hinzugef&uuml;gt.
     * Ist der Dateiname null, wird pname als Pfad mit Dateiname im aktuellen Dateisystem
     * aufgefaßt.
     */
    static public InputStream getInputStream(String pname, String fname)
    {
        InputStream is = null;

        String resname = "/" + pname.replace('.', '/') + "/" + fname;
        Class clazz = Access.class;
        is = clazz.getResourceAsStream(resname);
        if (is == null)
        {
            LOGGER.info("resource " + resname + " not found.");
        } else
        {
            LOGGER.info("resource " + resname + " found.");
        }

        return is;
    }

    static public InputStream getInputStream(String fname)
    {
        InputStream is = null;

        try
        {
            is = new FileInputStream(fname);
            LOGGER.info("file " + fname + " found");
        } catch (FileNotFoundException ex)
        {
            LOGGER.info("file " + fname + " not found");
        }

        return is;
    }

    /**
     * Diese Methode versucht einen InputStream zu erzeugen indem sie
     * versucht eine Resource zu laden, scheitert dies wird versucht
     * eine Datei als Eingabe zu verwenden.
     *
     * @param pn Pfad(Paket)name z.B. com.bp.baw oder C:/tmp
     * @param fn Dateiname z.B. logo.png oder gvars.properties
     * @return der gefundene Strom oder null
     */
    static public InputStream searchInputStream(String pn, String fn)
    {
        InputStream is = null;

        is = getInputStream(pn, fn);
        if (is == null)
        {
            is = getInputStream(pn + System.getProperty("file.separator") + fn);
        }

        return is;
    }

    static public DataInputStream getDataInputStream(String path, String fname)
    {
        DataInputStream din = null;

        InputStream is = Access.searchInputStream(path, fname);
        if (is != null)
        {
            din = new DataInputStream(new BufferedInputStream(is));
        }
        return din;
    }

    static public OutputStream getOutputStream(String name)
    {
        OutputStream os = null;

        try
        {
            os = new FileOutputStream(name);
        } catch (FileNotFoundException ex)
        {
            LOGGER.warn("file open failed (" + name + ")");
        }

        return os;
    }
}

