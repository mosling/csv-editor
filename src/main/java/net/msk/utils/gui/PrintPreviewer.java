package net.msk.utils.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Pageable;


public class PrintPreviewer extends JPanel
{
    static private Logger LOGGER = LogManager.getLogger(PrintPreviewer.class);

    protected Pageable pageable;
    protected PrintComponent printComponent;
    protected int pageIndex;

    protected JScrollPane scrollPane;
    protected JButton previousButton;
    protected JButton nextButton;
    protected JButton sizeButton;
    protected JTextField scaleText;


    /**
     * Creates a new instance of PrintPreviewer
     */
    public PrintPreviewer(Pageable p, int page)
    {
        pageable = p;
        pageIndex = page;
        printComponent = new PrintComponent(null, null);
        printComponent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        buildLayout();
        displayPage(pageIndex);

    }


    protected void buildLayout()
    {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(printComponent);
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        add(getBottomPanel(), BorderLayout.SOUTH);
        addListeners();

    }

    protected JPanel getBottomPanel()
    {
        JPanel outer = new JPanel();
        outer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1, 2, 10, 0));

        previousButton = new JButton("previous");
        inner.add(previousButton);

        nextButton = new JButton("next");
        inner.add(nextButton);

        outer.add(inner);
        scaleText = new JTextField(3);
        outer.add(scaleText);

        sizeButton = new JButton("size to fit");
        outer.add(sizeButton);

        return outer;
    }

    protected void addListeners()
    {
        previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                displayPage(pageIndex - 1);
            }
        });

        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                displayPage(pageIndex + 1);
            }
        });

        sizeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                sizeToFit();
            }
        });

        scaleText.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                try
                {
                    int scale = Integer.parseInt(scaleText.getText());
                    printComponent.setScaleFactor(scale);

                } catch (NumberFormatException nfe)
                {
                    LOGGER.error(nfe.toString());
                }
            }
        });
    }

    protected void displayPage(int index)
    {
        pageIndex = index;
        printComponent.setPrintable(pageable.getPrintable(pageIndex));
        printComponent.setPageFormat(pageable.getPageFormat(pageIndex));
        printComponent.setDisplayPage(index);
        previousButton.setEnabled(pageIndex > 0);
        nextButton.setEnabled(pageIndex < (pageable.getNumberOfPages() - 1));
        repaint();
    }

    protected void sizeToFit()
    {

        int newScaleFactor;
        Dimension compSize = printComponent.getSizeWithScale(100d);
        Dimension viewSize = scrollPane.getSize();

        int scaleX = (viewSize.width - 25) * 100 / compSize.width;
        int scaleY = (viewSize.height - 25) * 100 / compSize.height;
        newScaleFactor = Math.min(scaleX, scaleY);

        printComponent.setScaleFactor(newScaleFactor);
        scaleText.setText(Integer.toString(newScaleFactor));
        repaint();
    }
}
