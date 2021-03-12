package net.msk.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils
{

    /**
     * A pattern representing the Date-String
     */
    public static final String dateMinutePattern = "yyyy-MM-dd HH:mm:ss";
    public static final String timeStampPattern = "yyyy_MM_dd_HH_mm_ss";
    public static final String dateOnlyPattern = "yyyy-MM-dd";
    public static SimpleDateFormat dateMinuteFormat = new SimpleDateFormat(dateMinutePattern);
    public static SimpleDateFormat dateOnlyFormat = new SimpleDateFormat(dateOnlyPattern);
    public static SimpleDateFormat timeStampFormat = new SimpleDateFormat(timeStampPattern);

    /**
     * get the current time
     *
     * @return now
     */
    public static Date getCurrentTime()
    {
        return (new GregorianCalendar()).getTime();
    }

    /**
     * This method converts a Date object to a Date String
     *
     * @param aDate     a Date
     * @param theFormat the used dateformat
     * @return the date
     */
    public static String createDateString(Date aDate, SimpleDateFormat theFormat)
    {
        String dateString = "";
        if (aDate != null)
            dateString = theFormat.format(aDate);
        return dateString;
    }

    /**
     * This method converts a Date object to a Date String
     *
     * @param aDate       a Date
     * @param datePattern the used dateformat
     * @return the date
     */
    public static String createDateString(Date aDate, String datePattern)
        throws ParseException
    {
        String dateString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        if (aDate != null)
            dateString = dateFormat.format(aDate);
        return dateString;
    }

    /**
     * creates a date from string according to dateOnlyPattern
     *
     * @param dateString
     * @param datePattern
     * @return date
     * @throws ParseException
     */
    public static Date createDate(String dateString, String datePattern)
        throws ParseException
    {

        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        if (isValidDate(dateString, dateFormat))
        {
            date = dateFormat.parse(dateString);
        }
        return date;
    }

    /**
     * This helper method checks the given date string for validity.
     *
     * @param dateStr    The date string to be checked.
     * @param dateFormat
     * @return boolean - true or false.
     */
    public static boolean isValidDate(String dateStr, SimpleDateFormat dateFormat)
    {
        boolean isValid = false;

        Date date = null;
        String parsingDate = null;

        try
        {

            date = dateFormat.parse(dateStr);
            parsingDate = dateFormat.format(date);

            isValid = true;
            if (!dateStr.equals(parsingDate))
            {
                isValid = false;
            }
        } catch (ParseException pe)
        {
            isValid = false;
        }

        return isValid;
    }

    /**
     * @param datePattern
     * @param date
     * @return formatted dateString
     */
    public static String formatDate(String datePattern, Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        return createDateString(date, dateFormat);
    }


    /**
     * put date (without time) and time into date
     *
     * @param theDate
     * @param theTime
     * @return resulting date
     */
    public static Date putTimeToDate(Date theDate, Time theTime)
    {
        GregorianCalendar calOfDate = new GregorianCalendar();
        calOfDate.setTime(theDate);

        GregorianCalendar calOfTime = new GregorianCalendar();
        calOfTime.setTimeInMillis(theTime.getTime());

        calOfDate.set(Calendar.HOUR_OF_DAY, calOfTime.get(Calendar.HOUR_OF_DAY));
        calOfDate.set(Calendar.MINUTE, calOfTime.get(Calendar.MINUTE));
        calOfDate.set(Calendar.SECOND, calOfTime.get(Calendar.SECOND));

        return calOfDate.getTime();
    }

    public static Date parseDateString(String dateString, String dateFormat)
    {
        try
        {
            return createDate(dateString, dateFormat);
        } catch (ParseException e)
        {
            System.out.println(e.toString());
        }

        return null;
    }

}
