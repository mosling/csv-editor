package net.msk.csvedit.CSVDatatypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class CSVInteger
        extends CSVDatatype
{
    static Logger LOGGER = LogManager.getLogger( CSVInteger.class );
    int lowerLimit;
    int upperLimit;
    private String defaultEntry;

    public CSVInteger()
    {
        super();
        this.isNumericalType = true;
        defaultEntry = "Initialisation required";
        lowerLimit = Integer.MIN_VALUE;
        upperLimit = Integer.MAX_VALUE;
    }

    public boolean isValidEntry()
    {
        String entry = ( (JTextField) ( currentVisualComponent ) ).getText();
        try
        {
            Integer.parseInt( entry );
        }
        catch ( NumberFormatException e )
        {
            return false;
        }
        return true;
    }

    public JComponent getVisualComponent()
    {
        currentVisualComponent = (JComponent) new JTextField( defaultEntry );
        return currentVisualComponent;
    }

    public java.lang.String getVisualComponentEntry()
    {
        return ( (JTextField) currentVisualComponent ).getText();
    }

    /**
     * Nötig zur Initialisierung der Instanz, setzt die speziellen Eigenschaften
     * dieses Datentyps auf Defaultwerte, bzw. nutzt dafür den Parameter args.
     */
    public void init( java.lang.String[] args )
    {
        try
        {
            defaultEntry = args[0];
        }
        catch ( IndexOutOfBoundsException e )
        {
            LOGGER.info( "CSVInteger Defaultwert 0" );
            defaultEntry = "0";
        }
        try
        {
            lowerLimit = Integer.parseInt( args[1] );
        }
        catch ( Exception e )
        {
            LOGGER.info( "CSVInteger lowerLimit MIN_VALUE" );
            lowerLimit = Integer.MIN_VALUE;
        }
        try
        {
            upperLimit = Integer.parseInt( args[2] );
        }
        catch ( Exception e )
        {
            LOGGER.info( "CSVInteger upperLimit MAX_VALUE" );
            upperLimit = Integer.MAX_VALUE;
        }
    }

    public int compare( Object o1, Object o2 )
            throws ClassCastException
    {
        if ( o1 == null )
            o1 = new Integer( Integer.MIN_VALUE );
        if ( o2 == null )
            o2 = new Integer( Integer.MIN_VALUE );
        Integer o1int = new Integer( o1.toString() );
        Integer o2int = new Integer( o2.toString() );
        return o1int.compareTo( o2int );
    }

    ;

    /**
     * Liefert den Text der Fehlermeldung, falls isValid(currentVisualComponent)
     * false ist.
     */
    public String getConstraintsText()
    {
        String retValue = java.util.ResourceBundle
                .getBundle( "csvlang" )
                .getString( "Message_Invalid_Entry_CSVInteger" );
        retValue += "\n" + "lower limit = " + lowerLimit;
        retValue += "\n" + "upper limit = " + upperLimit;
        return retValue;
    }

}

