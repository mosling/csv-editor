/*
 * Created on 5. Juni 2003, 11:26 by Koehler
 *
 * organisation: 3Kon - Gesellschaft für Konstruktion Planung und Softwareentwicklung mbH
 * copyright: (c) 2003-2004
 *
 * $Id: ReleaseInfo.java,v 1.1 2005/03/02 08:00:52 skoehler Exp $
 */

package net.msk.utils.text;


abstract public class ReleaseInfo
{
    abstract public String getVersion();

    abstract public String getProductName();

    abstract public String getEMail();

    abstract public String getOrganisation();

    abstract public javax.swing.ImageIcon getIcon();

    abstract public String getDate();
}

