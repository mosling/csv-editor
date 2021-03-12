package net.msk.utils.gui;

import net.msk.utils.text.ReleaseInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class InfoScreen
{
    static Logger LOGGER = LogManager.getLogger(InfoScreen.class);

    static public void showDialog(Component c, String paket)
    {
        try
        {
            Class clazz = Class.forName(paket + ".ReleaseInfo");
            ReleaseInfo ri = (ReleaseInfo) clazz.newInstance();

            JOptionPane.showMessageDialog(
                c,
                ri.getProductName() + "\n"
                    + ri.getOrganisation() + "\n"
                    + ri.getEMail() + "\n"
                    + "Version " + ri.getVersion() + "  [" + ri.getDate() + "]",
                "Programm Info",
                JOptionPane.INFORMATION_MESSAGE,
                ri.getIcon()
            );
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(c, "No release information available.");
        }
    }

}

