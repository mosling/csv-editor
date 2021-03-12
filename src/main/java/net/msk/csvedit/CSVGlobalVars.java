package net.msk.csvedit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Diese Klasse ist eine Modifikation der Klasse GlobalVars. Es wurde die
 * load()-Methode überschrieben, so dass der Pfad der Datei nicht im
 * Klassenpfad stehen muss.
 */
public class CSVGlobalVars extends net.msk.utils.text.GlobalVars
{
    static Logger LOGGER = LogManager.getLogger(CSVGlobalVars.class);

    public CSVGlobalVars()
    {
        super();
    }

    /**
     * Es werden die Eigenschaften aus der Ressourcendatei im angegebenen Pfad
     * geladen.
     *
     * @param fn Pfad der zu ladenden Resource
     */
    public void load(String fn)
    {
        Properties pr = new Properties();

        if (fn.length() == 0)
            fn = "./gvars.properties";

        try
        {
            InputStream in = new FileInputStream(fn);

            if (in != null)
            {
                LOGGER.info("reading variables from resource: " + fn);
                pr.load(in);
                in.close();
            }
        } catch (FileNotFoundException e)
        {
            LOGGER.warn(e.toString());
            return;
        } catch (IOException e)
        {
            LOGGER.warn(e.toString());
            return;
        }

        // now copy the values into the Hashtable ht
        for (Enumeration e = pr.propertyNames(); e.hasMoreElements(); )
        {
            String s = (String) e.nextElement();
            LOGGER.debug("load variable " + s);
            setVar(s, pr.getProperty(s));
        }
    }
}
