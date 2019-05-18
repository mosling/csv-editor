package net.msk.csvedit.CSVDatatypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class CSVDate extends CSVDatatype
{
    static Logger LOGGER = LogManager.getLogger(CSVDate.class);

    private String dateFormat;
    private String defaultEntry;

    public CSVDate()
    {
        super();
        dateFormat = "dd.MM.yyyy";
        defaultEntry = "Initialisation required";
    }

    /**
     * @param args Feldindex:<br>
     *             1 - defaultwert<br>
     *             2 - date-formatierung
     */
    public void init(java.lang.String[] args)
    {
        try
        {
            defaultEntry = args[0];
        } catch (IndexOutOfBoundsException e)
        {
            LOGGER.info("CSVDate Defaultwert 01.01.2003");
            defaultEntry = "01.01.2003";
        }
        try
        {
            dateFormat = args[1];
        } catch (IndexOutOfBoundsException e)
        {
            LOGGER.info("CSVDate Datumsformat dd.MM.yyyy");
            dateFormat = "dd.MM.yyyy";
        }
    }

    public boolean isValidEntry()
    {
        return true;
    }

    /**
     * Liefert den Text der Fehlermeldung, falls isValid(currentVisualComponent)
     * false ist.
     */
    public String getConstraintsText()
    {
        String retValue = java.util.ResourceBundle.getBundle("csvlang").getString("Message_Invalid_Entry_CSVDate");
        retValue += "\n" + "date format = " + dateFormat;
        return retValue;
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
}

