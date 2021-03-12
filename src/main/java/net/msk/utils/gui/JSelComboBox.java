package net.msk.utils.gui;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Eine editierbare Combobox, die den nächsten wahrscheinlichen
 * Eintrag vorschlägt. Mit der Methode setInputListItemsOnly(bOnly)
 * kann festgelegt werden, ob nur die Listeneinträge eingegeben werden
 * können, oder beliebige Eingaben erlaubt werden.
 * Als Standard ist festgelegt, dass alle Eingaben erlaubt sind.
 * <p>
 * Implementations-Info: Um die Logik zu vereinfachen wird bei der
 * Taste [Entf] das gesamte Feld gelöscht. Sonderfunktionen wie z.B.
 * Aussschneiden werden noch nicht berücksichtigt.
 * Die Einträge sollten alphabetisch sortiert sein.
 */
public class JSelComboBox extends JComboBox implements KeyListener
{

    static Logger LOGGER = LogManager.getLogger(JSelComboBox.class);
    // Nur Einträge der Liste editierbar
    boolean inputListItemsOnly = false;
    boolean caseSensitive = false;

    public JSelComboBox()
    {
        super();
        setEditable(true);
        getEditor().getEditorComponent().addKeyListener(this);
    }

    public JSelComboBox(Object[] items)
    {
        super(items);
        setEditable(true);
        getEditor().getEditorComponent().addKeyListener(this);
    }

    /**
     * Gibt das Verhalten für Eingaben, die nicht den Listeneinträgen
     * entsprechen zurück.
     *
     * @return true: nur Listeinträge; false: alle Eingaben erlauben
     */
    public boolean isInputListItemsOnly()
    {
        return inputListItemsOnly;
    }

    /**
     * Setzt das Verhalten für Eingaben, die nicht den Listeneinträgen
     * entsprechen.
     *
     * @param bOnly true: nur Listeinträge; false: alle Eingaben
     *              erlauben.
     */
    public void setInputListItemsOnly(boolean bOnly)
    {
        inputListItemsOnly = bOnly;
    }

    /**
     * Gibt das Verhalten für die Suche der Listeneinträge zurück.
     */
    public boolean isCaseSensitive()
    {
        return caseSensitive;
    }

    /**
     * Setzt das Verhalten für die Suche der Listeneinträge.
     *
     * @param bCaseSensitive true: Groß/Kleinschreibung ist notwendig
     */
    public void setCaseSensitive(boolean bCaseSensitive)
    {
        caseSensitive = bCaseSensitive;
    }

    public void keyReleased(final java.awt.event.KeyEvent p1)
    {
    }

    public void keyPressed(final java.awt.event.KeyEvent p1)
    {
        if (!isPopupVisible())
        {
            setPopupVisible(true);
        }
        if (p1.getKeyCode() == KeyEvent.VK_DELETE)
        {
            JTextField jtf = (JTextField) getEditor().getEditorComponent();
            resetInput();
            p1.consume();
        }
    }

    public void keyTyped(final java.awt.event.KeyEvent p1)
    {
        int nPos = -1;
        String search = null;

        JTextField jtf = (JTextField) getEditor().getEditorComponent();

        nPos = jtf.getSelectionStart();
        if (nPos != -1)
        {
            if (p1.getKeyChar() == KeyEvent.VK_BACK_SPACE)
            {
                search = jtf.getText()
                            .substring(0, nPos == 0 ? nPos : nPos - 1);
                if (search.equals(""))
                {
                    resetInput();
                    p1.consume();
                    return;
                }
            } else
            {
                search = jtf.getText().substring(0, nPos);
                search = search + p1.getKeyChar();
            }

            findAndSel(search, p1);
        } else
        {
            setSelectedIndex(-1);
        }
    }

    /**
     * Sucht einen Eintrag anhand einer Zeichenkette und wählt evtl.
     * eine Zeile aus.
     * Wenn nur Listeneinträge ausgewählt werden dürfen und die
     * Zeichenkette nicht gefunden wurde, dann wird die Eingabe
     * verhindert.
     *
     * @param search Zeichenkette für die Suche
     * @param p1     Event des Tastendruckes
     */
    protected void findAndSel(String search,
                              java.awt.event.KeyEvent p1)
    {
        JTextField jtf = (JTextField) getEditor().getEditorComponent();
        int start = getSelectedIndex() + 1;
        int index = findString(search, start);

        if (index == -1)
            index = findString(search, start - 1);

        if (index == -1)
            index = findString(search, 0);

        if (index != -1)
        {
            System.out.println(index);
            String found = getModel().getElementAt(index).toString();
            setSelectedIndex(index);
            jtf.setText(found);
            jtf.setCaretPosition(search.length());
            jtf.setSelectionEnd(found.length());
            jtf.setSelectionStart(search.length());
            p1.consume();
        } else
        {
            if (isInputListItemsOnly())
            {
                getToolkit().beep();
                p1.consume();
            }
        }
    }

    /**
     * Sucht einen Eintrag anhand einer Zeichenkette und gibt die Zeile
     * zurück.
     */
    protected int findString(String search, int start)
    {
        ComboBoxModel cbm = getModel();
        if (start < 0) start = 0;
        for (int i = start; i < cbm.getSize(); i++)
        {
            if (isCaseSensitive())
            {
                if (cbm.getElementAt(i).toString().startsWith(search))
                {
                    return i;
                }
            } else
            {
                if (cbm.getElementAt(i).toString().toLowerCase()
                       .startsWith(search.toLowerCase()))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Setzt die Eingaben und die Auswahlzeile zurück.
     */
    protected void resetInput()
    {
        JTextField jtf = (JTextField) getEditor().getEditorComponent();
        if (isInputListItemsOnly() == false)
        {
            jtf.setText("");
            jtf.setCaretPosition(0);
            setSelectedIndex(-1);
        } else
        {
            jtf.setText(getModel().getElementAt(0).toString());
            setSelectedIndex(0);
            jtf.setSelectionEnd(jtf.getText().length());
            jtf.setSelectionStart(0);
        }
    }
}

