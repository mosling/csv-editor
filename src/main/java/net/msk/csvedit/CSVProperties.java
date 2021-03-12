package net.msk.csvedit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CSVProperties enthält alle Einstellungen.
 */
public class CSVProperties extends CSVGlobalVars
{
    static Logger LOGGER = LogManager.getLogger(CSVProperties.class);
    final private String fs = System.getProperty("file.separator");

    /**
     * Creates new CSVProperties.
     */
    public CSVProperties()
    {
        super();
        setVar("HeaderLines", "0");
        setVar("SeparationSign", "|");
        setVar("DividerLocation", "-1");
        setVar("QuotationSign", "\"");
        setVar("TreeVisible", "true");
        setVar("LargeIcons", "false");
        setVar("CurrentDir", System.getProperty("user.home"));
    }

    /**
     * This means a new file named %HOMEDIR%/csveditor.properties with predefinied
     * properties will be created and will set the properties. Or the existing
     * file will be readed out and will set the properties.
     */
    public void load()
    {
        String path = System.getProperty("user.home") + fs + "csveditor.properties";
        load(path);
    }

    /**
     * This means a new file on the location path with predefinied properties will
     * be created and will set the properties. Or the existing file will be readed out.
     *
     * @param path full path.
     */
    public void load(String path)
    {
        super.load(path);
        store();
        show();
    }

    public String[] getPublicVarsArray()
    {
        setProperty("SeparationSign", "L1");
        setProperty("QuotationSign", "L1");

        return new String[]{ "HeaderLines", "SeparationSign", "QuotationSign", "CurrentDir"};
    }

    /**
     * This means a file named %HOMEDIR%/csveditor.properties with the actual
     * properties will be stored.
     */
    public void store()
    {
        String path = System.getProperty("user.home") + fs + "csveditor.properties";
        store(path);
    }

    /**
     * This means a file on the location path with the actual properties will be stored.
     *
     * @param path full path and name of the properties file
     */
    public void store(String path)
    {
        try
        {
            super.save(path);
        } catch (Exception e)
        {
            LOGGER.debug("csveditor.properties cannot be saved: " + e.getMessage());
        }
    }


    public void setSepAndQSign(char s, char q)
    {
        setSeparationSign(s);
        setQuotationSign(q);
    }

    public char getSeparationSign()
    {
        return getVarString("SeparationSign").charAt(0);
    }

    public void setSeparationSign(char s)
    {
        if (s == 't')
        {
            setVar("SeparationSign", Character.toString('\t'));
        } else
        {
            setVar("SeparationSign", Character.toString(s));
        }
    }

    public char getQuotationSign()
    {
        return getVarString("QuotationSign").charAt(0);
    }

    public void setQuotationSign(char q)
    {
        setVar("QuotationSign", Character.toString(q));
    }

    public int getHeaderLines()
    {
        return Integer.parseInt(getVarString("HeaderLines"));
    }

    public void setHeaderLines(int divloc)
    {
        setVar("HeaderLines", String.valueOf(divloc));
    }

    public int getDividerLocation()
    {
        return Integer.parseInt(getVarString("DividerLocation"));
    }

    /**
     * Es wird die Divider Location gesetzt.
     *
     * @param divloc divider location
     */
    public void setDividerLocation(int divloc)
    {
        setVar("DividerLocation", String.valueOf(divloc));
    }

    /**
     * Es wird ein Pfad zurueckgegeben. Mit setCurrentDir() kann so das aktuelle
     * Verzeichnis im Oeffnen/Schliessen Dialogfeld gesetzt werden.
     */
    public String getCurrentDir()
    {
        return getVarString("CurrentDir");
    }

    /**
     * Es wird ein Pfad gesetzt. Mit getCurrentDir() kann so das aktuelle
     * Verzeichnis im Oeffnen/Schliessen Dialogfeld gelesen werden.
     *
     * @param path Verzeichnispfad
     */
    public void setCurrentDir(String path)
    {
        setVar("CurrentDir", path);
    }

    /**
     * Es wird zurueckgegeben, ob die Baumstruktur sichtbar sein soll.
     */
    public boolean isTreeVisible()
    {
        String treevisibleStr = getVarString("TreeVisible");
        return Boolean.valueOf(treevisibleStr).booleanValue();
    }

    /**
     * Es wird gesetzt, ob die Baumstruktur sichtbar sein soll.
     */
    public void setTreeVisible(boolean v)
    {
        setVar("TreeVisible", String.valueOf(v));
    }

    /**
     * Es wird zurueckgegeben, ob grosse Icons sichtbar sein sollen.
     */
    public boolean isLargeIconsEnabled()
    {
        String largeiconsStr = getVarString("LargeIcons");
        return Boolean.valueOf(largeiconsStr).booleanValue();
    }

    /**
     * Es wird gesetzt, ob grosse Icons sichtbar sein sollen.
     */
    public void setLargeIcons(boolean v)
    {
        setVar("LargeIcons", String.valueOf(v));
    }
}