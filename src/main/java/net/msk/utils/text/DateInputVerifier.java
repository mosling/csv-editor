package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateInputVerifier extends InputVerifier
{
    /**
     * create an Logger entry
     */
    static Logger LOGGER = LogManager.getLogger(DateInputVerifier.class);
    String errmsg;
    private SimpleDateFormat sdf;
    private String dfstr;
    private boolean showError = false;

    /**
     * Creates a new instance of DateInputVerifier
     */
    public DateInputVerifier(String df)
    {
        super();
        sdf = new SimpleDateFormat(df);
        errmsg = new String();
        dfstr = df;
    }

    public DateInputVerifier(String df, boolean se)
    {
        this(df);
        showError = se;
    }

    public String toString()
    {
        return "falsches Datum: " + errmsg;
    }

    public boolean verify(JComponent c)
    {
        String s = ((javax.swing.text.JTextComponent) c).getText();
        boolean success = true;

        try
        {
            Date d = sdf.parse(s);
            // invers the parse function
            String stmp = sdf.format(d);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(d);
            int year = cal.get(Calendar.YEAR);
            LOGGER.debug("compareTo " + stmp + " year: " + year);
            if (stmp.compareTo(s) == 0)
            {
                // years counted from 1900
                if (year > 2100 || year < 1900)
                {
                    errmsg = "Jahr zu gross";
                    success = false;
                }
            } else
            {
                errmsg = "Datum nicht im richtigen Format (" + dfstr + ")";
                success = false;
            }
        } catch (Exception e)
        {
            errmsg = "Datum nicht im richtigen Format (" + dfstr + ")";
            success = false;
        }
        if (!success)
        {
            LOGGER.warn(errmsg);
            if (showError == true)
            {
                javax.swing.JOptionPane.showMessageDialog(c, s + " >> " + errmsg);
            }
        }

        return success;
    }
}

