package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class IntegerInputVerifier extends InputVerifier
{
    private static Logger LOGGER = LogManager.getLogger(IntegerInputVerifier.class);
    /**
     * create an Category entry
     */
    private boolean showError = false;

    /**
     * Creates a new instance of IntegerInputVerifier
     */
    public IntegerInputVerifier()
    {
    }

    public IntegerInputVerifier(boolean se)
    {
        showError = se;
    }

    public String toString()
    {
        return "keine Ganzzahl";
    }

    public boolean verify(JComponent c)
    {
        String s = ((javax.swing.text.JTextComponent) c).getText();
        boolean success = true;

        try
        {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e)
        {
            success = false;
        }
        if (!success)
        {
            LOGGER.warn(this.toString());
            if (showError == true)
            {
                ((javax.swing.text.JTextComponent) c).setText("0");
                javax.swing.JOptionPane.showMessageDialog(c, s + " ist keine ganze Zahl !");
                ((javax.swing.text.JTextComponent) c).setText(s);
            }
        }

        return success;
    }
}

