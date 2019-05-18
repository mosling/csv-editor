package net.msk.csvedit.CSVDatatypes;

import javax.swing.*;

public class CSVSet extends CSVDatatype
{

    private String[] jComboBoxEntries;

    public CSVSet()
    {
        super();
        jComboBoxEntries = new String[]{"Initialisation required"};
    }

    /**
     * @args die Combobox Eintraege
     */
    public void init(String[] args)
    {
        jComboBoxEntries = args;
    }

    public boolean isValidEntry()
    {
        return true;
    }

    public JComponent getVisualComponent()
    {
        currentVisualComponent = new JComboBox(jComboBoxEntries);
        return currentVisualComponent;
    }

    ;

    public String getVisualComponentEntry()
    {
        return ((JComboBox) (currentVisualComponent)).getSelectedItem().toString();
    }

}
