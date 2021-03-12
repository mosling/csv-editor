package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class NumberInputVerifier extends InputVerifier
{

    private static Logger LOGGER = LogManager.getLogger(NumberInputVerifier.class);
    private boolean showError = false;
    private boolean useDefault = false;
    private String defaultValue;

    /**
     * Creates a new instance of IntegerInputVerifier
     */
    public NumberInputVerifier()
    {
    }

    public NumberInputVerifier(boolean se)
    {
        showError = se;
    }

    public NumberInputVerifier(boolean se, String defVal)
    {
        showError = se;
        useDefault = true;
        defaultValue = defVal;
    }

    public String toString()
    {
        return "keine Zahl";
    }

    public boolean verify(JComponent c)
    {
        String s = ((javax.swing.text.JTextComponent) c).getText();
        boolean success = true;
        System.out.println("Number /" + s + "/");

        try
        {
            double i = Double.parseDouble(s);
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
                javax.swing.JOptionPane.showMessageDialog(c, s + " ist keine Zahl (xx.xxx)!");
                ((javax.swing.text.JTextComponent) c).setText(s);
            }
            if (useDefault)
            {
                ((javax.swing.text.JTextComponent) c).setText(defaultValue);
            }
        }

        return success;
    }
}
