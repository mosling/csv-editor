package net.msk.utils.gui;

import net.msk.utils.text.ReleaseInfo;

public class VerboseSplashScreen extends javax.swing.JFrame
{
    protected javax.swing.JPanel jPanel1;
    private java.awt.Toolkit tk;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dreikonL;
    private javax.swing.JLabel infotechL;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logoL;
    private javax.swing.JLabel nameL;
    private javax.swing.JLabel supportL;
    private javax.swing.JLabel versionL;
    private javax.swing.JLabel webL;
    private javax.swing.JLabel workL;
    private javax.swing.JProgressBar workPB;
    /**
     * Creates new form VerboseSplashScreen
     */
    public VerboseSplashScreen(ReleaseInfo ri)
    {
        initComponents();
        nameL.setText(ri.getProductName());
        versionL.setText("Version " + ri.getVersion());
        supportL.setText(ri.getEMail());
        pack();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setBounds(x, y, this.getWidth(), this.getHeight());
        this.show();
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
        dreikonL = new javax.swing.JLabel();
        infotechL = new javax.swing.JLabel();
        logoL = new javax.swing.JLabel();
        nameL = new javax.swing.JLabel();
        workPB = new javax.swing.JProgressBar();
        workL = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        webL = new javax.swing.JLabel();
        supportL = new javax.swing.JLabel();
        versionL = new javax.swing.JLabel();

        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 128, 95));
        jPanel1
            .setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 88, 95), new java.awt.Color(0, 168, 95)));
        dreikonL.setFont(new java.awt.Font("Humnst777 BT", 1, 18));
        dreikonL.setForeground(java.awt.Color.white);
        dreikonL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dreikonL.setText("3Kon GmbH");
        dreikonL.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel1.add(dreikonL, gridBagConstraints);

        infotechL.setFont(new java.awt.Font("Humnst777 BT", 1, 12));
        infotechL.setForeground(java.awt.Color.white);
        infotechL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infotechL.setText("Informationstechnologie");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 3);
        jPanel1.add(infotechL, gridBagConstraints);

        logoL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bp/images/3kon-logo.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 3);
        jPanel1.add(logoL, gridBagConstraints);

        nameL.setFont(new java.awt.Font("Humnst777 BT", 1, 14));
        nameL.setForeground(java.awt.Color.white);
        nameL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameL.setText("SPLASHSCREEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel1.add(nameL, gridBagConstraints);

        workPB.setForeground(new java.awt.Color(51, 153, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 40);
        jPanel1.add(workPB, gridBagConstraints);

        workL.setFont(new java.awt.Font("Humnst777 BT", 1, 10));
        workL.setForeground(java.awt.Color.white);
        workL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        workL.setText("loading ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 8, 40);
        jPanel1.add(workL, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(0, 128, 95));
        webL.setFont(new java.awt.Font("Humnst777 BT", 1, 10));
        webL.setForeground(java.awt.Color.white);
        webL.setText("www.buschjena.de");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 100.0;
        jPanel3.add(webL, gridBagConstraints);

        supportL.setFont(new java.awt.Font("Humnst777 BT", 1, 10));
        supportL.setForeground(java.awt.Color.white);
        supportL.setText("support@buschjena.de");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 100.0;
        jPanel3.add(supportL, gridBagConstraints);

        versionL.setFont(new java.awt.Font("Humnst777 BT", 1, 10));
        versionL.setForeground(java.awt.Color.white);
        versionL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        versionL.setText("Version: 1.23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 100.0;
        jPanel3.add(versionL, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt)//GEN-FIRST:event_exitForm
    {
        dispose();
    }//GEN-LAST:event_exitForm

    public void setProgress(String s, int p)
    {
        workL.setText(s + " ...");
        workPB.setValue(workPB.getValue() + p);
    }

    public void finish()
    {
        this.exitForm(null);
    }
    // End of variables declaration//GEN-END:variables
}
