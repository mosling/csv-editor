package net.msk.utils;

public class StringUtils
{

    /**
     * is the String null or empty or "null" ?
     *
     * @param theString
     * @return result
     */
    public static boolean isEmptyString(String theString)
    {
        return (theString == null || theString.length() == 0 || theString.equalsIgnoreCase("null"));
    }

}
