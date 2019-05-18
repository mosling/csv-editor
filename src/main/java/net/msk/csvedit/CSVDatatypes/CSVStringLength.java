package net.msk.csvedit.CSVDatatypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class CSVStringLength extends CSVDatatype
{
    static Logger LOGGER = LogManager.getLogger(CSVStringLength.class);

    private int length;
    private String defaultEntry;

    public CSVStringLength()
    {
        super();
        defaultEntry = "Initialisation required";
    }

    /**
     * @param args Feldindex:<br>
     *             1 - Maximallaenge des strings<br>
     */
    public void init(String[] args)
    {
        try
        {
            defaultEntry = args[0];
        } catch (IndexOutOfBoundsException e)
        {
            LOGGER.info("CSVStringLength Defaultwert ''");
            defaultEntry = "";
        }
        try
        {
            length = Integer.parseInt(args[1]);
        } catch (Exception e)
        {
            LOGGER.info("CSVStringLength Laenge 5");
            length = 5;
        }
    }

    public boolean isValidEntry()
    {
        String entry = ( (JTextField) ( currentVisualComponent ) ).getText();
        return entry.length() < length;
    }

    public JComponent getVisualComponent()
    {
        currentVisualComponent = (JComponent) new JTextField(defaultEntry);
        return currentVisualComponent;
    }

    public String getVisualComponentEntry()
    {
        return ((JTextField) currentVisualComponent).getText();
    }

    /**
     * Liefert den Text der Fehlermeldung, falls isValid(currentVisualComponent)
     * false ist.
     */
    public String getConstraintsText()
    {
        String retValue = java.util.ResourceBundle.getBundle("csvlang")
                                                  .getString("Message_Invalid_Entry_CSVStringLength");
        retValue += "\n" + "maxLength = " + length;
        return retValue;
    }

}

