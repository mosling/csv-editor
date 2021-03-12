package net.msk.utils.text;

import javax.swing.*;


public class GUIVar
{
    /**
     * the name of the variable
     ***/
    private String name = null;


    /**
     * the name of the variable
     ***/
    private JLabel label = null;

    /**
     * Creates a new instance of GUIVar
     */
    public GUIVar()
    {
    }

    /**
     * Getter for property name.
     *
     * @return Value of property name.
     */
    public java.lang.String getName()
    {
        return name;
    }

    /**
     * Setter for property name.
     *
     * @param name New value of property name.
     */
    public void setName(java.lang.String name)
    {
        this.name = name;
    }

    /**
     * Getter for property label.
     *
     * @return Value of property label.
     */
    public javax.swing.JLabel getLabel()
    {
        return label;
    }

    /**
     * Setter for property label.
     *
     * @param label New value of property label.
     */
    public void setLabel(javax.swing.JLabel label)
    {
        this.label = label;
    }

    /**
     * getter for text of label.
     */
    public String getLabelText()
    {
        if (this.label != null)
            return this.label.getText();

        return null;
    }

    /**
     * Setter for text of label.
     *
     * @param labelText
     */
    public void setLabelText(String labelText)
    {
        if (this.label == null)
            label = new JLabel();

        this.label.setText(labelText);
    }


}
