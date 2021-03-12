package net.msk.utils.text;


import net.msk.utils.io.Access;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;


/**
 * Immer wieder werden von Applikationen Informationen ben"otigt, welche nicht
 * <p>
 * innerhalb des Programmtextes stehen sollen. Von Java wird f"ur solche
 * <p>
 * Zwecke die Klasse Property bereitgestellt.
 * <p>
 * Die Funktionalitäten dieser Klasse werden verwendet und erweitert.
 * <p>
 * <p/>
 * <p>
 * Es wird ein Variablenname als Schl&uuml;sselwert verwendet, zu diesem
 * <p>
 * Wert k&ouml;nnen beliebige Objektzeiger gespeichert werden.
 * <p>
 * <p/>
 * <p>
 * F&uuml;r jede Variable k&ouml;nnen Eigenschaften hinterlegt werden.
 * <p>
 * Eigenschaften sind einfache Zeichenketten, die unter dem gleichem
 * <p>
 * Schl&uuml;ssel wie der Wert gespeichert werden. Es k&ouml;nnen
 * <p>
 * Eigenschaften gespeichert werden <B>bevor</B> Werte gespeichert sind.
 * <p>
 * Folgende Eigenschaften werden von der Klasse selbst verwendet.
 * <p>
 * - PROP_PASSWORD  Passwort, wird nicht gespeichert
 * <p>
 * - PROP_SAVE      Save, damit kann ein Passwort gespeichert werden
 * <p>
 * - PROP_TRANSIENT Transient, solche Variablen werden nicht gespeichert
 * <p>
 * - PROP_FILE_SELECTION Datei-Auswahl (relevant für Anzeige)
 * <p>
 * - PROP_PATH_SELECTION Pfad-Auswahl (relevant für Anzeige)
 * <p>
 * - PROP_CALENDAR Kalenderauswahl (relevant für Anzeige)
 * <p>
 * - PROP_DATE     Datum
 * <p>
 * <p/>
 * <p>
 * Es k&ouml;nnen mehrere Eigenschaften pro Variable gespeichert werden.
 * <p>
 * Ebenso k&ouml;nnen Eigenschaften mit Werten gespeichert werden. Diese Klasse
 * <p>
 * bietet die entprechenden Methoden um Eigenschaften und deren Werte zu
 * <p>
 * verwenden.
 */

public class GlobalVars

{

    /**
     * the property password
     */

    public static final String PROP_PASSWORD = "PW";
    /**
     * the property save
     */

    public static final String PROP_SAVE = "SV";
    /**
     * the property transient
     */

    public static final String PROP_TRANSIENT = "TR";
    /**
     * the property password
     */

    public static final String PROP_FILE_SELECTION = "FS";
    /**
     * the property password
     */

    public static final String PROP_PATH_SELECTION = "PS";
    /**
     * the property textarea
     */

    public static final String PROP_TEXTAREA = "TA";
    /**
     * the property calendar
     */

    public static final String PROP_CALENDAR = "CL";
    public static final String PROP_DATE = "DT";
    /**
     * create an LOGGER entry
     */

    private static final Logger LOGGER = LogManager.getLogger(GlobalVars.class);
    private final Hashtable ht;
    private final Hashtable pt;


    /**
     * Erzeugen eines neuen Objektes, dabei werden die beiden Hashtabellen f&uuml;r
     * <p>
     * die Werte und Eigenschaften angelegt.
     */

    public GlobalVars()

    {

        LOGGER.debug("Creating class GlobalVars.");

        ht = new Hashtable();

        pt = new Hashtable();

    }


    /**
     * Es wird &uuml;berpr&uuml;ft ob eine Variable existiert.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @return <CODE>true</CODE> falls ein Eintrag mit diesem Schl&uuml;ssel existiert.
     */

    public boolean existsVar(String k)

    {
        return ht.containsKey(k);
    }


    /**
     * Es wird ein Objekt unter einem Variablennamen in die Hashtabelle aufgenommen.
     * <p>
     * Dabei wird ein vorhandener Wert &uuml;berschrieben. Es werden <B>nur</B> Objekte
     * <p>
     * aufgenommen.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @param o Der Zeiger auf das zu speichernde Objekt.
     */

    public void setVar(String k, Object o)

    {

        if (o == null)

        {

            LOGGER.warn("no object given");

            return;

        }

        if (ht.put(k, o) != null)

            LOGGER.debug("change object for key: " + k + " to " + o.toString());

        else

            LOGGER.debug("create variable: " + k);

    }


    /**
     * Funktioniert wie {@link #setVar(String, Object) } nur kann gleich eine Eigenschaft
     * <p>
     * &uuml;bergeben werden.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @param o Der Zeiger auf das zu speichernde Objekt.
     * @param p Die Eigenschaft der Variablen.
     */

    public void setVar(String k, Object o, String p)

    {

        this.setVar(k, o);

        pt.put(k, o);

    }


    /**
     * Es wird die Variable mit dem Schl&uuml:ssel wieder gel&ouml;scht.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     */

    public void delVar(String k)

    {

        ht.remove(k);

    }


    /**
     * Es wird eine Eigenschaft gesetzt, diese &uuml;berschreibt eine bereits
     * <p>
     * vorhandene Eigenschaft.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @param p Die Eigenschaft.
     */

    public void setProperty(String k, String p)

    {

        pt.put(k, ":" + p + ":");

    }


    /**
     * Eigenschaft f"ur alle Variablen setzen.
     * <p>
     * Dies ist z.B. dann notwendig, wenn Daten aus einer Resource und einer
     * <p>
     * Nutzerdatei kommen und nur die Daten aus der Nutzerdatei wieder gespeichert
     * <p>
     * werden sollen.
     */

    public void setPropertyForall(String p)

    {

        if (ht != null)

        {

            for (Enumeration e = ht.keys(); e.hasMoreElements(); )

            {

                this.setProperty((String) e.nextElement(), p);

            }

        }

    }


    /**
     * Es wird eine vorhandene Eigenschaft zur&uuml;ckgegeben.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @return Die Eigenschaft oder die leere Zeichenkette, falls keine Eigenschaft
     * <p>
     * gefunden wurde.
     */

    public String getProperty(String k)

    {

        Object o = pt.get(k);

        if (o == null)

        {

            return "";

        } else

        {

            return (String) o;

        }

    }


    /**
     * Es wird eine Eigenschaft an die schon vorhandenen angef&uuml;gt
     *
     * @param k Der Schl&uuml;ssel der Variablen
     * @param p die Eigenschaft
     */

    public void addProperty(String k, String p)

    {

        Object o = pt.get(k);

        if (o == null)

        {

            this.setProperty(k, p);

        } else

        {

            String st = o + p + ":";

            this.setProperty(k, st);

        }

    }


    /**
     * Eigenschaft f"ur alle Variablen hinzuf"ugen.
     * <p>
     * Dies ist z.B. dann notwendig, wenn Daten aus einer Resource und einer
     * <p>
     * Nutzerdatei kommen und nur die Daten aus der Nutzerdatei wieder gespeichert
     * <p>
     * werden sollen.
     */

    public void addPropertyForall(String p)

    {

        if (ht != null)

        {

            for (Enumeration e = ht.keys(); e.hasMoreElements(); )

            {

                this.addProperty((String) e.nextElement(), p);

            }

        }

    }


    /**
     * Gibt true zur&uuml;ckm falls die geforderte Eigenschaft gesetzt ist.
     * <p>
     * Eine Eigenschaft ist gesetzt, wenn der Wert zwischen zwei Doppelpunkten
     * <p>
     * existiert oder, falls es ein Parameter mit Wert ist, zwischen Doppelpunkt
     * <p>
     * und Gleichheitszeichen.
     *
     * @param k Schl&uuml;ssel der Variablen
     * @param p die Eigenschaft
     * @return true oder false
     */

    public boolean hasProperty(String k, String p)

    {

        Object o = pt.get(k);

        if (o == null)

        {

            return false;

        } else

        {

            return

                    ((((String) o).indexOf(":" + p + ":")) == 0

                            ||

                            (((String) o).indexOf(":" + p + "=")) == 0

                    );

        }

    }


    /**
     * Es gibt neben reinen Anzeigeeigeschaften auch Eigenschaften mit
     * <p>
     * Parametern, diese Methode liefert nur den Parameter, er erstreckt
     * <p>
     * sich nach dem Gleichheitszeichen des Paramaters bis zum n"achsten
     * <p>
     * Doppelpunkt.
     *
     * @param k Schl&uuml;ssel der Variablen
     * @param p die Eigenschaft
     * @return Parameter als Eigenschaft
     */

    public String getPropertyParam(String k, String p)

    {

        String r = this.getProperty(k);

        if (r.length() == 0)

        {

            return "";

        } else

        {

            int s = r.indexOf(":" + p + "=") + 2 + p.length();

            int e = r.indexOf(':', s);

            LOGGER.debug("property parameter /" + r + "/ is substring: " + s + " to " + e);

            return r.substring(s, e);

        }

    }


    /**
     * Diese Methode liefert das Object zum Schl&uuml;ssel, falls der
     * <p>
     * Schl&uuml;ssel nicht existiert wird null geliefert.
     *
     * @param k Zeichenkettenschl&uuml;ssel
     * @return Liefert das Object.
     */

    public Object getVar(String k)

    {

        Object o = ht.get(k);

        if (o == null)

        {

            LOGGER.error("variable doesn't exists: /" + k + "/");

        }

        return o;

    }


    /**
     * Die meisten Variablen werden sicher Zeichenketten sein, deshalb
     * <p>
     * existiert diese Methode, welche als Resultatwert eine Zeichenkette
     * <p>
     * liefert und somit das sonst notwendige casten kapselt.
     *
     * @param k Zeichenkettenschl&uuml;ssel
     * @return Ergebnis als Zeichenkette
     */

    public String getVarString(String k)

    {

        Object o = getVar(k);

        if (o == null)

            return "";

        else

            return o.toString();

    }


    /**
     * Liefert einen Int-Wert. Damit erspart sich die Applikation eine Umrechnung.
     * <p>
     * Allerdings mit der Ungewissheit ob die Variable 0 ist oder ob ein Fehler
     * <p>
     * aufgetreten ist.
     *
     * @return Wert oder 0
     */

    public int getVarInt(String k)

    {

        int i = 0;


        Object o = this.getVar(k);

        if (o != null)

        {

            try

            {

                i = Integer.parseInt(o.toString());

            } catch (NumberFormatException ex)

            {

                LOGGER.warn("Var " + k + ": could not parse " + o.toString() + " to int (" + ex.getMessage() + ")");

                i = 0;

            }

        }


        return i;

    }


    /**
     * Liefert einen Double-Wert. Damit erspart sich die Applikation eine Umrechnung.
     * <p>
     * Allerdings mit der Ungewissheit ob die Variable 0 ist oder ob ein Fehler
     * <p>
     * aufgetreten ist.
     *
     * @return Wert oder 0
     */

    public double getVarDouble(String k)

    {

        double d = 0.0D;


        Object o = this.getVar(k);

        if (o != null)

        {

            try

            {

                d = Double.parseDouble(o.toString());

            } catch (NumberFormatException ex)

            {

                LOGGER.warn("Var " + k + ": could not parse " + o.toString() + " to double (" + ex.getMessage() + ")");

                d = 0;

            }

        }


        return d;

    }


    /**
     * F"ur die R"uckgabe von Zeichenketten existiert noch die Variante mit
     * <p>
     * Angabe eines Wertes. Dieser Wert wird geliefert, falls die Variable
     * <p>
     * nicht existiert. Weiterhin wird die Variable mit dem Wert angelegt.
     */

    public String getVarString(String k, String def)

    {
        if (!existsVar(k))

        {

            this.setVar(k, def);

            return def;

        } else

        {

            return getVar(k).toString();

        }

    }


    /**
     * Neben dem untypisierten Zugriff auf eine Variable, kann mit dieser Funktion
     * <p>
     * sichergestellt werden, da&szlig; die Variable auch einen bestimmten Typ hat.
     * <p>
     * Damit kann ohne weitere Abfragen oder Ausnahmen gecastet werden.
     *
     * @param k Der Schl&uuml;ssel der Variablen.
     * @param c Die geforderte Klasse der Variablen.
     * @return null oder ein Objekt
     */

    public Object getTypedVar(String k, String c)

    {

        Object o = ht.get(k);

        if (o != null)

        {

            if (o.getClass().getName().compareTo(c) != 0)

            {

                LOGGER.warn("objects have different types: Hashtable(" +

                        o.getClass().getName() + ") Parameter(" + c + ")");

                o = null;

            }

        } else

        {

            LOGGER.warn("variable doesn't exists: /" + k + "/");

        }

        return o;

    }


    /**
     * Liste der vorhandenen Schl&uuml;ssel.
     *
     * @return Aufz&auml;hlung mit allen Schl&uuml;sselwerten.
     */

    public Enumeration getKeys()

    {

        return ht.keys();

    }


    /**
     * Diese Methode liefert eine Liste der erfassten Schlüsselwerte.
     */
    public String[] getVarArray()

    {

        String[] list = new String[ht.size()];

        int i = 0;

        for (Enumeration e = ht.keys(); e.hasMoreElements(); )

        {

            list[i] = (String) e.nextElement();

            i++;

        }

        return list;

    }

    /**
     * Es werden alle Zeichenkettenvariablen (java.lang.String) in eine Property-Datei
     * <p>
     * gespeichert. Dabei werden die Zeichenketten mit der Eigenschaft Passwort ausgelassen.
     *
     * @param fn Der Dateiname, falls nicht &uuml;bergeben wird der Pfad aus der Variablen
     *           <p>
     *           <code>installdir/etc/gvars.properties</code> &uuml;bernommen, ist dieser Wert ebenfalls leer wird
     *           <p>
     *           <code>c:\temp\gvars.properties</code> verwendet.
     */

    public void save(String fn)

    {

        OutputStream os = Access.getOutputStream(fn);


        if (os != null)

        {

            LOGGER.info("write to " + fn);

            Properties pr = new Properties();


            // firts copy all Strings to pr

            for (Enumeration e = ht.keys(); e.hasMoreElements(); )

            {

                String k = (String) e.nextElement();

                if (

                        (!hasProperty(k, PROP_TRANSIENT))

                                &&

                                (getVar(k).getClass().getName().compareTo("java.lang.String") == 0)

                                &&

                                ((!hasProperty(k, PROP_PASSWORD))

                                        ||

                                        (hasProperty(k, PROP_PASSWORD) && hasProperty(k, PROP_SAVE)))

                )

                {

                    pr.put(k, ht.get(k));

                    String prop = (String) pt.get(k);

                    if (prop != null)

                    {

                        pr.put("P__" + k, prop.substring(1, prop.length() - 1));

                    }

                }

            }

            try

            {

                if (pr.size() > 0)

                {

                    pr.store(os, "");

                }

                os.close();

            } catch (IOException e)

            {

                LOGGER.error(e.toString());

            }

        }

    }


    /**
     * Diese Methode erlaubt die &Uuml;bergabe eines Pfades und eines
     * <p>
     * Dateinamens. Da nur Dateien (keine Resourcen) gespeichert werden,
     * <p>
     * werden diese beiden Komponenten mit dem Systempfadtrennzeichen
     * <p>
     * verbunden.
     */

    public void save(String pn, String fn)

    {

        this.save(pn + System.getProperty("file.separator") + fn);

    }


    /**
     * Oftmals m&ouml;chte man die Informationen f&uuml;r einen bestimmten
     * <p>
     * Nutzer speichern. Diese Methode erzeugt aus dem &uuml;bergebenen Dateinamen
     * <p>
     * einen absoluten Pfad unter Verwendung der Systemeigenschaft user.home.
     */

    public void saveUser(String fn)

    {

        this.save(System.getProperty("user.home")

                + System.getProperty("file.separator") + fn);

    }


    /**
     * Diese Methode liest die Werte aus dem Datenstrom und speichert sie
     * <p>
     * in der Hashtabelle ab.
     */

    private void load(InputStream in, boolean overwrite)

    {

        Properties pr = new Properties();


        try

        {

            pr.load(in);

            in.close();

        } catch (java.io.IOException ex)

        {

            LOGGER.error(ex.getMessage());

            return;

        }


        // now copy the values into the Hashtable ht

        for (Enumeration e = pr.propertyNames(); e.hasMoreElements(); )

        {

            String s = (String) e.nextElement();

            if (s.startsWith("P__"))

            {

                if (LOGGER.isDebugEnabled())

                {

                    LOGGER.debug("load properties " + s);

                }

                if (overwrite || !this.existsVar(s.substring(3)))

                {

                    this.setProperty(s.substring(3), pr.getProperty(s));

                }

            } else

            {

                if (LOGGER.isDebugEnabled())

                {

                    LOGGER.debug("load variable " + s);

                }

                if (overwrite || !ht.containsKey(s))

                {

                    ht.put(s, pr.getProperty(s));

                }

            }

        }

    }


    /**
     *
     */

    public void load(String pn, String fn, boolean overwrite)

    {

        if (pn.length() == 0)

            pn = System.getProperty("user.home");


        if (fn.length() == 0)

            fn = "gvars.properties";


        InputStream in = Access.searchInputStream(pn, fn);

        if (in != null)

        {

            load(in, overwrite);

        }

    }


    public void load(String pn, String fn)

    {

        this.load(pn, fn, true);

    }


    /**
     * Lade die Variablen aus dem Heimatverzeichnis des Nutzers und
     * <p>
     * verwende die &uuml;bergebenen Variable um die &Uuml;berschreibung
     * <p>
     * der Variablen zu steuern.
     *
     * @param fn        Name der Datei, welche die Variablen enth&auml;lt
     * @param overwrite true, falls Nutzervariablen schon vorhanden
     *                  <p>
     *                  &uuml;berschreiben sollen.
     */

    public void loadUser(String fn, boolean overwrite)

    {

        this.load("", fn, overwrite);

    }


    /**
     * Lade die Variablen aus dem Heimatverzeichnis des Nutzers und
     * <p>
     * &uuml;berschreibe schon vorhandene Variablen.
     */

    public void loadUser(String fn)

    {

        this.load("", fn, true);

    }


    public void load(String fn, boolean overwrite)

    {

        InputStream in = Access.getInputStream(fn);

        if (in != null)

        {

            load(in, overwrite);

        }

    }


    /**
     * Zeige alle gespeicherten Werte an
     */

    public void show()

    {

        LOGGER.debug("----------- contents of GlobalVars -----------");

        for (Enumeration e = ht.keys(); e.hasMoreElements(); )

        {

            String k = (String) e.nextElement();

            String prop = "no properties";

            if (pt.containsKey(k))

            {

                prop = (String) pt.get(k);

            }

            LOGGER.debug(k + "|" + "|" + ht.get(k) + " [" + prop + "]");

        }

        LOGGER.debug("----------------------------------------------");

    }

}



