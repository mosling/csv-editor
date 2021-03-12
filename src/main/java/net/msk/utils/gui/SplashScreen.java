package net.msk.utils.gui;

import java.awt.*;
import java.net.URL;

public class SplashScreen extends java.awt.Window
{
    private static final int BORDERSIZE = 0;
    private static final java.awt.Color BORDERCOLOR = java.awt.Color.green;
    private Image splashImage;
    private int imgWidth, imgHeight;
    private String imgName;
    private java.awt.Toolkit tk;

    public SplashScreen(java.awt.Frame f, String imgName)
    {
        super(f);
        this.imgName = imgName;
        tk = java.awt.Toolkit.getDefaultToolkit();
        splashImage = loadSplashImage();
        showSplashScreen();
        f.addWindowListener(new WindowListener());
    }

    public Image loadSplashImage()
    {
        java.awt.MediaTracker tracker = new java.awt.MediaTracker(this);
        Image result;
        URL imgURL = ClassLoader.getSystemResource(imgName);
        if (imgURL == null)
        {
            result = tk.getImage(imgName);
        } else
        {
            result = tk.getImage(imgURL);
        }
        tracker.addImage(result, 0);
        try
        {
            tracker.waitForAll();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        imgWidth = result.getWidth(this);
        imgHeight = result.getHeight(this);
        return (result);
    }

    public void showSplashScreen()
    {
        java.awt.Dimension screenSize = tk.getScreenSize();
        setBackground(BORDERCOLOR);
        int w = imgWidth + (BORDERSIZE * 2);
        int h = imgHeight + (BORDERSIZE * 2);
        int x = (screenSize.width - w) / 2;
        int y = (screenSize.height - h) / 2;
        setBounds(x, y, w, h);
        setVisible(true);
    }

    public void paint(java.awt.Graphics g)
    {
        g.drawImage(splashImage, BORDERSIZE, BORDERSIZE,
            imgWidth, imgHeight, this);
    }

    class WindowListener extends java.awt.event.WindowAdapter
    {
        public void windowOpened(java.awt.event.WindowEvent we)
        {
            dispose();
        }
    }
}

