package net.msk.utils.io;

/**
 * Überschrift:	BAW PDBB-Browser
 * Beschreibung:
 * Copyright:	  Copyright (c) 2001
 * Organisation:  Dr. Busch&Partner GmbH
 *
 * @author skoehler
 * @version 1.0
 */

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/** Es soll eine Datein eingelesen werden, und alle Platzhalter durch den
 *  entsprechenden Inhalt ersetzt werden.
 */
public class ReadResource
{

    public ReadResource()
    {
    }

    static public ImageIcon LoadIconResource(String res)
    {
        URL iconurl = ClassLoader.getSystemResource(res);
        if (iconurl != null)
        {
            System.out.println("found: " + iconurl.getPath());
            return new ImageIcon(iconurl);
        } else
        {
            return null;
        }
    }

    static public String LoadTextResource(String res)
    {
        String str = null;
        // first load the resource if possible
        URL xmltempl = ClassLoader.getSystemResource(res);
        if (xmltempl != null)
        {
            try
            {
                // found the resource
                InputStream is = xmltempl.openStream();
                str = "";
                int num = is.available();
                byte[] bfield = new byte[num];
                is.read(bfield, 0, num);
                for (int i = 0; i < num; i++)
                {
                    str += (char) bfield[i];
                }
            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        } else
        {
            System.out.println("no resource found: " + res);
        }
        return str;
    }
}
