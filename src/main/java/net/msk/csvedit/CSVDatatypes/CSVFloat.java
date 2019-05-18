package net.msk.csvedit.CSVDatatypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class CSVFloat extends CSVDatatype
{
    static Logger LOGGER = LogManager.getLogger(CSVFloat.class);

    private int nachkommastellen;
    private String defaultEntry;

    public CSVFloat()
    {
        super();
        this.isNumericalType = true;
        defaultEntry = "Initialisation required";
        nachkommastellen = -1;
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
            LOGGER.info("CSVFloat Defaultwert '0.0'");
            defaultEntry = "0.0";
        }
        try
        {
            nachkommastellen = Integer.parseInt(args[1]);
            if (nachkommastellen < 0) throw new Exception();
        } catch (Exception e)
        {
            LOGGER.info("CSVFloat Nachkommastellen '-1'(beliebig)");
            nachkommastellen = -1;
        }
    }

    public boolean isValidEntry()
    {
        String entry = ((JTextField) (currentVisualComponent)).getText();
        try
        {
            double entryf = Double.parseDouble(entry);
            double cuttedcomma = Math.pow(10.0, (double) nachkommastellen);
            double cuttedf = Math.ceil(entryf * cuttedcomma) / cuttedcomma;
            if ((cuttedf != entryf) && (nachkommastellen != -1)) return false;
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Liefert den Text der Fehlermeldung, falls isValid(currentVisualComponent)
     * false ist.
     */
    public String getConstraintsText()
    {
        String retValue = java.util.ResourceBundle.getBundle("csvlang").getString("Message_Invalid_Entry_CSVFloat");
        retValue += "\n" + "decimal digits = " + nachkommastellen;
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


    public int compare(Object o1, Object o2) throws NumberFormatException
    {
        if (o1 == null) o1 = new Double(Double.NEGATIVE_INFINITY);
        if (o2 == null) o2 = new Double(Double.NEGATIVE_INFINITY);
        Double o1dbl = new Double(o1.toString());
        Double o2dbl = new Double(o2.toString());
        return o1dbl.compareTo(o2dbl);
    }

    ;
}

