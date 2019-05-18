package net.msk.csvedit.CSVDatatypes;

import javax.swing.*;

abstract public class CSVDatatype
{

    /**
     * mit getVisualComponent() wird ein neue Instanz auf currentVisualComponent
     * angelegt, bis zu einem neuen Aufruf von getVisualComponent() befindet sich
     * hier die aktuelle Instanz
     */
    protected JComponent currentVisualComponent;
    protected boolean isNumericalType = false;
    protected boolean isMandatory     = false;
    private int jTextboxWidth;

    /**
     * Konstruktor der Klasse CSVDatatype
     */
    public CSVDatatype()
    {
    }

    /**
     * Nötig zur Initialisierung der Instanz, setzt die speziellen Eigenschaften
     * dieses Datentyps auf Defaultwerte, bzw. nutzt dafür den Parameter args.
     */
    public abstract void init( java.lang.String[] args );

    /**
     * Gibt an, ob der mit getVisualEntry() gelieferte Eintrag gueltig ist
     */
    public abstract boolean isValidEntry();

    /**
     * Liefert einen gueltigen Eintrag. Dies kann unter Benutzung der currentVisualComponent
     * geschehen.
     */
    public abstract String getVisualComponentEntry();

    /**
     * Muss eine JComponent zurückliefern, die als Visualisierung für den Datentyp
     * gilt. Zusätzlich muss diese Visualisierung der protected Variable
     * currentVisualComponent zugewiesen werden.
     */
    public abstract JComponent getVisualComponent();

    /**
     * Gibt an, wie beim Sortieren die Ordnung der Elemente bestimmt wird.
     * Falls compareTo nicht überschrieben wird, werden die Elemente
     * lexikografisch (behandelt als Strings) geordnet.
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.<p>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of <i>expression</i>
     * is negative, zero or positive.
     * The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)<p>
     * The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.<p>
     * Finally, the implementer must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.<p>
     * It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compare( Object o1, Object o2 )
    {
        if ( o1 == null && o2 == null) return 0;
        if ( o1 == null ) return -1;
        if ( o2 == null ) return 1;

        return o1.toString().compareTo( o2.toString() );
    }

    /**
     * liefert, ob der Datentyp numerisch ist (z.B. für Suchen - Ersetzen),
     * dies muss im Konstruktor des Datatypes mit this.isNumericalType=true
     * angegeben werden, standardmässig sind die Datentypen nichtnumerisch
     */
    public boolean isNumerical()
    {
        return isNumericalType;
    }

    /**
     * liefert, ob die Notwendigkeit des Ausfüllens bei der Eingabe besteht,
     * standardmässig sind die Datentypen nicht mandatory
     */
    public boolean isMandatory()
    {
        return isMandatory;
    }

    /**
     * setzt das Attribut, das die Notwendigkeit des Ausfüllens bei der Eingabe
     * anzeigt
     *
     * @param m mandatory flag
     */
    public void setMandatory( boolean m )
    {
        isMandatory = m;
    }

    /**
     * Liefert den Text der Fehlermeldung, falls isValid(currentVisualComponent)
     * false ist. Falls die Methode nicht überschrieben wird, wird eine Standard
     * fehler Text zurückgegeben.
     */
    public String getConstraintsText()
    {
        return java.util.ResourceBundle.getBundle( "csvlang" ).getString( "Message_Invalid_Entry" );
    }
}
