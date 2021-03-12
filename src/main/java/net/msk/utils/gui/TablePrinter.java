package net.msk.utils.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

public class TablePrinter implements Printable, Pageable
{
    public static final int ALL_PAGES = 0;
    public static final int FIRST_PAGE_ONLY = 1;
    public static final int NO_PAGES = 2;
    static private Logger LOGGER = LogManager.getLogger(TablePrinter.class);
    protected JTable table;
    protected PageFormat pageformat;
    protected int headerStatus = ALL_PAGES;

    /**
     * Creates a new instance of TablePrinter
     */
    public TablePrinter(JTable tbl, PageFormat pf)
    {
        this.table = tbl;
        this.pageformat = pf;
    }

    public int print(java.awt.Graphics graphics, java.awt.print.PageFormat pageFormat, int Index) throws java.awt.print.PrinterException
    {
        Dimension size = null;
        // get the table's preferred size
        if ((table.getWidth() == 0) || (table.getHeight() == 0))
        {
            table.setSize(table.getPreferredSize());
        }
        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        int positionX = 0;
        int positionY = 0;

        // Loop until we have printed the entire table
        int pageIndex = 0;

        while (positionY < tableHeight)
        {
            positionX = 0;
            while (positionX < tableWidth)
            {
                size = getPrintSize(positionX, positionY);
                if (pageIndex == Index)
                {
                    // paint as much as the table fit on the page
                    paintTable(graphics, positionX, positionY, size);
                    return Printable.PAGE_EXISTS;

                }
                pageIndex++;
                positionX += size.width;

            }
            positionY += size.height;

        }
        return Printable.NO_SUCH_PAGE;

    }

    protected void paintTable(java.awt.Graphics g, int positionX, int positionY, Dimension size)
    {
        int offsetX = (int) (pageformat.getImageableX());
        int offsetY = (int) (pageformat.getImageableY());

        if (displayHeaderOnPage(positionY))
        {
            JTableHeader tableheader = table.getTableHeader();
            if (tableheader.getWidth() == 0 || tableheader.getHeight() == 0)
            {
                tableheader.setSize(tableheader.getPreferredSize());
            }
            int headerHeight = tableheader.getHeight();
            g.translate(offsetX - positionX, offsetY);
            g.clipRect(positionX, 0, size.width, size.height + headerHeight);
            tableheader.paint(g);
            g.translate(0, headerHeight - positionY);
            g.clipRect(positionX, positionY, size.width, size.height);

        } else
        {
            g.translate(offsetX - positionX, offsetY - positionY);
            g.clipRect(positionX, positionY, size.width, size.height);
        }
        table.paint(g);
    }


    protected Dimension getPrintSize(int positionX, int positionY)
    {
        Rectangle rect;
        int printWidth;
        int printHeight;

        int firstCol = table.columnAtPoint(new Point(positionX, positionY));
        int firstRow = table.rowAtPoint(new Point(positionX, positionY));

        int maxWidth = (int) (pageformat.getImageableWidth());
        int maxHeight = (int) (pageformat.getImageableHeight());

        if (displayHeaderOnPage(positionY))
        {
            maxHeight -= table.getTableHeader().getHeight();
        }

        int lastCol = table.columnAtPoint(new Point(positionX + maxWidth, positionY));
        if (lastCol == -1)
        {
            printWidth = table.getWidth() - positionX;
        } else
        {
            rect = table.getCellRect(0, lastCol - 1, true);
            printWidth = rect.x + rect.width - positionX;
        }

        int lastRow = table.rowAtPoint(new Point(positionX, positionY + maxHeight));
        if (lastRow == -1)
        {
            printHeight = table.getHeight() - positionY;
        } else
        {
            rect = table.getCellRect(lastRow - 1, 0, true);
            printHeight = rect.y + rect.height - positionY;
        }

        return new Dimension(printWidth, printHeight);
    }

    protected boolean displayHeaderOnPage(int positionY)
    {
        return ((headerStatus == ALL_PAGES) ||
            ((headerStatus == FIRST_PAGE_ONLY) &&
                positionY == 0));
    }

    public int getNumberOfPages()
    {
        Dimension size = null;
        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        LOGGER.debug("tableWidth : " + tableWidth);
        LOGGER.debug("tableHeight : " + tableHeight);
        int positionX = 0;
        int positionY = 0;

        int pageIndex = 0;
        while (positionY == 0)
        {
            positionX = 0;
            while (positionX == 0)
            {
                size = getPrintSize(positionX, positionY);
                LOGGER.debug("size.width : " + size.width);
                LOGGER.debug("size.height : " + size.height);
                positionX += size.width;
                pageIndex++;
            }
            positionY += size.height;
            pageIndex++;
        }

        return pageIndex;
    }

    public java.awt.print.PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException
    {
        return pageformat;
    }

    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException
    {
        return this;
    }

}
