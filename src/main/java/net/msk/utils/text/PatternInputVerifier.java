package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.regex.Pattern;

public class PatternInputVerifier extends InputVerifier
{
    static Logger LOGGER = LogManager.getLogger(PatternInputVerifier.class);
    private String regex;

    /**
     * Creates a new instance of PatternInputVerifier
     */
    public PatternInputVerifier(String r)
    {
        super();
        regex = r;
    }

    public String toString()
    {
        return regex;
    }

    public boolean verify(JComponent c)
    {
        String s = ((javax.swing.text.JTextComponent) c).getText();

        return Pattern.matches(regex, s);
    }

}
