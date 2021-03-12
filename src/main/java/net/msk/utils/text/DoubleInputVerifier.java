package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class DoubleInputVerifier extends InputVerifier
{
    static Logger LOGGER = LogManager.getLogger(DoubleInputVerifier.class);

    public DoubleInputVerifier()
    {
    }

    public String toString()
    {
        return "keine Gleitkommazahl";
    }

    public boolean verify(JComponent jComponent)
    {
        String s = ((JTextComponent) jComponent).getText();
        boolean success = true;

        try
        {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException e)
        {
            success = false;
        }

        if (!success)
        {
            LOGGER.warn(s + " isn't an double value!");
        }

        return success;
    }
}
