package net.msk.utils.text;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class LengthInputVerifier extends InputVerifier
{
    /**
     * create an Category entry
     */
    private static Logger LOGGER = LogManager.getLogger(LengthInputVerifier.class.getName());
    private int len;

    /**
     * Creates a new instance of LengthInputVerifier
     */
    public LengthInputVerifier(int l)
    {
        len = l;
    }

    public String toString()
    {
        return "Eingabe laenger als " + String.valueOf(len) + " Zeichen";
    }

    public boolean verify(JComponent c)
    {
        String s = ((javax.swing.text.JTextComponent) c).getText();
        boolean success = true;

        if (s.length() > len)
        {
            success = false;
        }
        if (!success)
            LOGGER.warn(this.toString());

        return success;
    }
}

