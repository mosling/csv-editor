package net.msk.csvedit;

public class EditNumDialog extends javax.swing.JDialog
{

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Formel;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;

    /**
     * Creates new form EditDialog
     */
    public EditNumDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        new EditNumDialog(new javax.swing.JFrame(), true).show();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        Formel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();

        getContentPane().setLayout(new java.awt.FlowLayout());

        setTitle("input numerical function");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        Formel.setText("Funktion:");
        getContentPane().add(Formel);

        jTextField1.setToolTipText("");
        jTextField1.setText("formular");
        getContentPane().add(jTextField1);

        jToggleButton1.setText("OK");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jToggleButton1ActionPerformed(evt);
            }
        });

        getContentPane().add(jToggleButton1);

        pack();
    }//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jToggleButton1ActionPerformed
    {//GEN-HEADEREND:event_jToggleButton1ActionPerformed
        // Add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    public String getFunction()
    {
        return jTextField1.getText();
    }
    // End of variables declaration//GEN-END:variables

}
