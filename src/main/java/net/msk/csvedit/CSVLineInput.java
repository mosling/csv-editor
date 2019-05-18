package net.msk.csvedit;

import net.msk.csvedit.CSVDatatypes.CSVDatatype;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class CSVLineInput extends javax.swing.JDialog
{
    boolean exitStateOK = false;
    private CSVLine csvline;
    private CSVTable csvtab;
    private Vector flist;
    private boolean useFormatFile;
    private boolean values;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buttons;
    private javax.swing.JPanel Fields;
    private javax.swing.JPanel Labels;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JPanel jPanel1;

    /**
     * Creates new form CSVLineInput
     *
     * @param parent   of the modal dialog
     * @param b        maintable that sets the number of columns etc.
     * @param c        target line into the entry is added
     * @param defaults sets the entrys into the dialog
     * @param formated if a format file is used the set to true
     */
    public CSVLineInput(java.awt.Frame parent, CSVTable b, CSVLine c, Vector defaults, boolean formated, boolean returnCancelValues)
    {
        super(parent, true);
        csvtab = b;
        csvline = c;
        useFormatFile = formated;
        values = returnCancelValues;
        initComponents();
        if (values) insertButton.setText(java.util.ResourceBundle.getBundle("csvlang").getString("change"));
        setDefaults(defaults);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()
    {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        Fields = new javax.swing.JPanel();
        Labels = new javax.swing.JPanel();
        Buttons = new javax.swing.JPanel();
        insertButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle(java.util.ResourceBundle.getBundle("csvlang").getString("insert_row"));
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout(2, 2));

        Fields.setLayout(new java.awt.GridLayout(0, 1));

        createFields(Fields);
        jPanel1.add(Fields, java.awt.BorderLayout.EAST);

        Labels.setLayout(new java.awt.GridLayout(0, 1));

        createLabels(Labels);
        jPanel1.add(Labels, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        insertButton.setText(java.util.ResourceBundle.getBundle("csvlang").getString("insert"));
        insertButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                insertButtonActionPerformed(evt);
            }
        });

        Buttons.add(insertButton);

        cancelButton.setText(java.util.ResourceBundle.getBundle("csvlang").getString("cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        Buttons.add(cancelButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(Buttons, gridBagConstraints);

        pack();
    }//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_cancelButtonActionPerformed
        // Add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_insertButtonActionPerformed
        // Add your handling code here:
        // create a new CSVLine with the values from the textfields
        int c = csvtab.getColumnCount();
        CSVReader r = csvtab.getReader();

        if (useFormatFile)
        {
            // check if the entries are valid
            for (int i = 0; i < c; i++)
            {
                CSVDatatype coldatatype = (CSVDatatype) csvtab.getColumnDatatype(i);
                if (!coldatatype.isValidEntry())
                {
                    String messageText = coldatatype.getConstraintsText();
                    JOptionPane
                        .showMessageDialog(this, messageText + "\n" + csvtab.getColumnName(i) + ": " + coldatatype
                            .getVisualComponentEntry());
                    return;
                }
                ;
                // alle leeren Textfelder
                if (coldatatype.getVisualComponentEntry().replaceAll(" ", "").equals(""))
                    // falls mandatory müssen sie ausgefüllt sein
                    if (coldatatype.isMandatory())
                    {
                        JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("csvlang")
                                                                                    .getString("Message_Mandatory_Entry") + csvtab
                            .getColumnName(i) + ": " + coldatatype.getVisualComponentEntry());
                        return;
                    }
            }
            // return the entries
            for (int i = 0; i < c; i++)
            {
                CSVDatatype coldatatype = (CSVDatatype) csvtab.getColumnDatatype(i);
                csvline.add(coldatatype.getVisualComponentEntry());
            }
        } else
        {
            for (int i = 0; i < c; i++) csvline.add(((javax.swing.JTextField) flist.get(i)).getText());
        }
        exitStateOK = true;
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_insertButtonActionPerformed

    /**
     * Create the labesl
     */
    private void createLabels(javax.swing.JPanel p)
    {
        int c = csvtab.getColumnCount();
        for (int i = 0; i < c; i++)
        {
            javax.swing.JLabel l = new javax.swing.JLabel(csvtab.getColumnName(i));
            l.setHorizontalAlignment(JLabel.RIGHT);
            p.add(l);
        }
    }

    /**
     * Create the input fields
     */
    private void createFields(javax.swing.JPanel p)
    {
        if (useFormatFile)
        {
            int c = csvtab.getColumnCount();
            flist = new Vector();

            for (int i = 0; i < c; i++)
            {
                CSVDatatype coldatatype = (CSVDatatype) csvtab.getColumnDatatype(i);
                JComponent f = coldatatype.getVisualComponent();
                try
                {
                    ((JTextField) f).setColumns(16);
                } catch (Exception e)
                {
                }
                flist.add(f);
                p.add(f);
            }
        } else
        {
            int c = csvtab.getColumnCount();
            flist = new Vector();

            for (int i = 0; i < c; i++)
            {
                javax.swing.JTextField f = new javax.swing.JTextField(16);
                flist.add(f);
                p.add(f);
            }
        }
    }

    /**
     * Take the default entries from the parameter defaults and set them into
     * the TextFields of the dialog.
     *
     * @param defaults Vector with the default entries
     */
    private void setDefaults(Vector defaults)
    {
        Component deftmp = null;
        Iterator defaultEl = defaults.iterator();
        int i = 0;
        while (defaultEl.hasNext())
        {
            deftmp = Fields.getComponent(i);
            if (deftmp.getClass() == JTextField.class)
            {
                ((JTextField) deftmp).setText((String) defaultEl.next());
            }
            i++;
        }
    }

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    // End of variables declaration//GEN-END:variables

}
