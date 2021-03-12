package net.msk.utils.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListSelectorGUI extends javax.swing.JDialog
{
    static Logger LOGGER = LogManager.getLogger(ListSelectorGUI.class.getName());
    private DefaultListModel pvalues, svalues;
    private ArrayList val;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton addall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton ok;
    private javax.swing.JList plist;
    private javax.swing.JButton remove;
    private javax.swing.JButton removeall;
    private javax.swing.JList slist;
    /**
     * Creates new form ListSelectorGUI
     */
    public ListSelectorGUI(ArrayList values, java.awt.Frame parent)
    {
        super(parent, true);
        pvalues = new DefaultListModel();
        svalues = new DefaultListModel();

        // fill the pvalues with values
        Iterator it = values.iterator();
        while (it.hasNext()) pvalues.addElement(it.next());
        val = values;
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        ArrayList a = new ArrayList();
        a.add("Steffen");
        a.add("Hanka");
        a.add("Susanne");
        a.add("Fabian");
        a.add("Sven");
        a.add("Annika");
        a.add("Homer und eine besonders lange Zeichenkette");
        a.add("Jochen");
        a.add("Monika");
        a.add("Senta");
        a.add("Klaus");
        a.add("Anneliese");
        new ListSelectorGUI(a, new javax.swing.JFrame()).show();
        System.out.println("Selected items are:");
        System.out.println(a.toString());
        System.exit(2);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        plist = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        addall = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        removeall = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        slist = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ok = new javax.swing.JButton();

        setTitle("Werte selektieren");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(400, 32767));
        plist.setModel(pvalues);
        jScrollPane1.setViewportView(plist);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(4, 1, 3, 3));

        add.setText("hinzuf\u00fcgen  =>");
        add.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        add.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addActionPerformed(evt);
            }
        });

        jPanel4.add(add);

        addall.setText("alle hinzuf\u00fcgen =>");
        addall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addall.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addallActionPerformed(evt);
            }
        });

        jPanel4.add(addall);

        remove.setText("<= entfernen");
        remove.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        remove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                removeActionPerformed(evt);
            }
        });

        jPanel4.add(remove);

        removeall.setText("<= alle entfernen");
        removeall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        removeall.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                removeallActionPerformed(evt);
            }
        });

        jPanel4.add(removeall);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        jPanel2.add(jPanel4, gridBagConstraints);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(400, 32767));
        slist.setModel(svalues);
        jScrollPane2.setViewportView(slist);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabel2.setText("ausgew\u00e4hlte Werte");
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel1.setText("m\u00f6gliche Wert");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel1, gridBagConstraints);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okActionPerformed(evt);
            }
        });

        jPanel3.add(ok);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okActionPerformed
    {//GEN-HEADEREND:event_okActionPerformed
        // Add your handling code here:
        // fill the ArrayList with the values from svalues
        val.clear();
        Object[] ol = svalues.toArray();
        for (int i = 0; i < ol.length; i++)
            val.add(ol[i]);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_removeActionPerformed
    {//GEN-HEADEREND:event_removeActionPerformed
        // Add your handling code here:
        int[] l = slist.getSelectedIndices();
        for (int i = 0; i < l.length; i++)
            pvalues.addElement(svalues.elementAt(l[i]));
        for (int i = l.length - 1; i >= 0; i--)
            svalues.removeElementAt(l[i]);
    }//GEN-LAST:event_removeActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addActionPerformed
    {//GEN-HEADEREND:event_addActionPerformed
        // Add your handling code here:
        int[] l = plist.getSelectedIndices();
        for (int i = 0; i < l.length; i++)
            svalues.addElement(pvalues.elementAt(l[i]));
        for (int i = l.length - 1; i >= 0; i--)
            pvalues.removeElementAt(l[i]);
    }//GEN-LAST:event_addActionPerformed

    private void removeallActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_removeallActionPerformed
    {//GEN-HEADEREND:event_removeallActionPerformed
        // Add your handling code here:
        // move all values from svalues to pvalues
        Object[] ol = svalues.toArray();
        for (int i = 0; i < ol.length; i++)
            pvalues.addElement(ol[i]);
        svalues.removeAllElements();
    }//GEN-LAST:event_removeallActionPerformed

    private void addallActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addallActionPerformed
    {//GEN-HEADEREND:event_addallActionPerformed
        // Add your handling code here:
        // move all values from pvalues to svalues
        Object[] ol = pvalues.toArray();
        for (int i = 0; i < ol.length; i++)
            svalues.addElement(ol[i]);
        pvalues.removeAllElements();
    }//GEN-LAST:event_addallActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_closeDialog
        val.clear();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    // End of variables declaration//GEN-END:variables

}
