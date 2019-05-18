package net.msk.csvedit.CSVDatatypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class CSVString extends CSVDatatype
{
    static Logger LOGGER = LogManager.getLogger(CSVString.class);

    private String defaultEntry;

    public CSVString()
    {
        super();
        defaultEntry = "Initialisation required";
    }

    /**
     * Konstruktor für CSVString
     *
     * @param entry der Standardwert bei der Eingabemaske
     */
    public CSVString(String entry)
    {
        super();
        defaultEntry = entry;
    }

    public boolean isValidEntry()
    {
        return true;
    }

    public java.lang.String getVisualComponentEntry()
    {
        return ((JTextField) currentVisualComponent).getText();
    }

    public JComponent getVisualComponent()
    {
        currentVisualComponent = new JTextField(defaultEntry);
        return currentVisualComponent;
    }

    /**
     * Nötig zur Initialisierung der Instanz, setzt die speziellen Eigenschaften
     * dieses Datentyps auf Defaultwerte, bzw. nutzt dafür den Parameter args.
     */
    public void init(java.lang.String[] args)
    {
        try
        {
            defaultEntry = args[0];
        } catch (IndexOutOfBoundsException e)
        {
            LOGGER.info("CSVString Defaultwert ''");
            defaultEntry = "";
        }
    }
}
