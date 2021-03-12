package net.msk.utils.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Die Java-Methoden zum Lesen von Bytestr&ouml;men verwenden intern die BigEndian Variante.
 * Diese Klasse erm&ouml;glicht das Lesen von LittleEndian Werten aus einem ByteStrom.
 */
public class RawData
{

    private static Logger LOGGER = LogManager.getLogger(RawData.class);

    /*
     * Es werden die n&auml;chsten 2 Byte gelesen und zu einem short-Wert kombiniert.
     */
    public static short readShortLittleEndian(DataInputStream din)
        throws IOException
    {
        int low = din.readUnsignedByte() & 0xff;
        int high = din.readUnsignedByte() & 0xff;

        return (short) (high << 8 | low);
    }

    /*
     * Es werden die n&auml;chsten 4 Byte gelesen und zu einem int-Wert kombiniert.
     */
    public static int readIntLittleEndian(DataInputStream din)
        throws IOException
    {
        int accum = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8)
        {
            accum |= (din.readUnsignedByte() & 0xff) << shiftBy;
        }
        return accum;
    }


    /*
     * Es werden die n&auml;chsten 8 Byte gelesen und zu einem double-Wert kombiniert.
     */
    public static double readDoubleLittleEndian(DataInputStream din)
        throws IOException
    {
        long accum = 0;
        for (int shiftBy = 0; shiftBy < 64; shiftBy += 8)
        {
            accum |= (((long) din.readUnsignedByte()) & 0xff) << shiftBy;
        }
        return Double.longBitsToDouble(accum);
    }
}

