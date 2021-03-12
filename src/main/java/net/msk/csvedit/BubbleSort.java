/*
 * BubbleSort.java
 *
 * Created on 26. Oktober 2001, 15:58
 */
package net.msk.csvedit;

import java.util.Vector;

/**
 * @author Koehler
 */
public class BubbleSort
{

    private static int column;

    public static void sort(Vector v, int col)
    {
        boolean changed = true;

        column = col;

        while (changed)
        {
            changed = false;
            for (int i = 0; i < v.size() - 1; i++)
            {
                if (compare(getElem(v, i), getElem(v, i + 1)) > 0)
                {
                    swapElements(v, i, i + 1);
                    changed = true;
                }
            }
        }
    }

    private static int compare(Object o1, Object o2)
    {
        // objects with different are uncomparable
        if (o1.getClass() != o2.getClass()) return 0;
        if (o1.getClass() == java.lang.Double.class)
        {
            double d1 = ((Double) o1).doubleValue();
            double d2 = ((Double) o2).doubleValue();
            if (d1 < d2) return -1;
            if (d1 == d2) return 0;
            return 1;
        }
        if (o1.getClass() == java.lang.String.class)
        {
            return ((String) o1).compareTo((String) o2);
        }
        return 0;
    }

    private static Object getElem(Vector v, int e)
    {
        CSVLine l = (CSVLine) v.elementAt(e);
        return l.elementAt(column);
    }

    private static void swapElements(Vector v, int a, int b)
    {
        Object temp;

        temp = v.elementAt(a);
        v.setElementAt(v.elementAt(b), a);
        v.setElementAt(temp, b);
    }
}
