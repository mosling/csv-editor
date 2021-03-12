package net.msk.utils.gui;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;


public class PrintComponent extends JPanel
{
    static private Logger LOGGER = LogManager.getLogger(PrintComponent.class);

    protected Printable printable;
    protected PageFormat pageFormat;
    protected int displayPage;
    protected double scaleFactor;

    /**
     * Creates a new instance of PrintComponent
     */
    public PrintComponent(Printable p, PageFormat pf)
    {
        setPrintable(p);
        setPageFormat(pf);
        setDisplayPage(0);
        setScaleFactor(100);
        setBackground(Color.white);
    }

    public Dimension getSizeWithScale(double scale)
    {
        Insets insets = getInsets();
        int width = ((int) (pageFormat.getWidth() * scale / 100d)) + insets.left
            + insets.right;

        int height = ((int) (pageFormat.getHeight() * scale / 100d)) + insets.top
            + insets.bottom;

        return new Dimension(width, height);
    }

    public Dimension getPreferredSize()
    {
        return getSizeWithScale(scaleFactor);
    }

    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle cliprect = g2.getClipBounds();
        AffineTransform at = g2.getTransform();

        int x = (int) (pageFormat.getImageableX() * scaleFactor / 100d);
        int y = (int) (pageFormat.getImageableY() * scaleFactor / 100d);
        int w = (int) (pageFormat.getImageableWidth() * scaleFactor / 100d);
        int h = (int) (pageFormat.getImageableHeight() * scaleFactor / 100d);

        g2.clipRect(x, y, w, h);
        g2.scale(scaleFactor / 100, scaleFactor / 100);
        try
        {
            printable.print(g, pageFormat, displayPage);
        } catch (PrinterException pe)
        {
            LOGGER.error(pe.toString());
        }
        g2.setTransform(at);
        g2.setClip(cliprect);
    }


    public void setPrintable(Printable p)
    {

        printable = p;
        revalidate();
    }

    public void setPageFormat(PageFormat pf)
    {

        pageFormat = pf;
        revalidate();
    }

    public void setDisplayPage(int page)
    {

        displayPage = page;
        revalidate();
    }

    public double getScaleFactor()
    {
        return scaleFactor;
    }

    public void setScaleFactor(double factor)
    {

        scaleFactor = factor;
        revalidate();
    }


}
