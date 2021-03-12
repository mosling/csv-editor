package net.msk.utils.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TokenList
{

    private static Logger LOGGER = LogManager.getLogger(TokenList.class);

    static public ArrayList createList(String st, String sep)
    {

        ArrayList al = new ArrayList();
        StringTokenizer stok = new StringTokenizer(st, sep);
        while (stok.hasMoreTokens())
        {
            al.add(stok.nextToken());
        }
        return al;
    }
}

