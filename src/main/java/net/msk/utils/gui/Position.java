package net.msk.utils.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class Position
{

    static Logger LOGGER = LogManager.getLogger(Position.class);

    /**
     * Creates a new instance of Position
     */
    public Position()
    {
    }

    public static void centerDialog(Component d, java.awt.Container f)
    {
        int x;
        int y;

        if (f == null) return;

        Point topLeft = f.getLocationOnScreen();
        Dimension parentSize = f.getSize();

        Dimension mySize = d.getSize();

        if (parentSize.width > mySize.width)
            x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
        else
            x = topLeft.x;

        if (parentSize.height > mySize.height)
            y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
        else
            y = topLeft.y;

        d.setLocation(x, y);
    }

    /**
     * Diese statische Methode zentriert eine Komponente auf ihrem Vater.
     */
    public static void centerParent(Component comp)
    {
        int x;
        int y;

        // Find out our parent
        Container myParent = comp.getParent();
        if (null == myParent)
        {
            return;
        }
        Point topLeft = myParent.getLocationOnScreen();
        Dimension parentSize = myParent.getSize();

        Dimension mySize = comp.getSize();

        if (parentSize.width > mySize.width)
            x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
        else
            x = topLeft.x;

        if (parentSize.height > mySize.height)
            y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
        else
            y = topLeft.y;

        comp.setLocation(x, y);
    }

    public static void fillScreen(java.awt.Component window)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setLocation(0, 0);
        window.setSize(tk.getScreenSize());
    }
}

