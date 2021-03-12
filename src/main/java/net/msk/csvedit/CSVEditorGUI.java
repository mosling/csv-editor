package net.msk.csvedit;

import net.msk.utils.text.GlobalVarsGUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Es wird eine Tabelle mit den Daten des entsprechenden Modelles angezeigt.
 * Kommuniziert wird mit einem Dialog in dem ein Baum mit den ausgew&auml;hlten
 * Gruppierungen angezeigt wird.
 */
public class CSVEditorGUI extends javax.swing.JFrame
{
    static Logger LOGGER = LogManager.getLogger(CSVEditorGUI.class);

    final private String fs = System.getProperty("file.separator");

    private boolean treeAdjust = false;
    // als Datenquelle
    private CSVReader csvreader;
    // zur Darstellung der Baumstruktur
    private DefaultMutableTreeNode root, selnode;
    //zur Darstellung der Tabelle
    private CSVTable csvtable, seltable;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenu OptionMenu;
    private javax.swing.JMenuItem changerow;
    private javax.swing.JMenuItem delcolumn;
    private javax.swing.JMenuItem deleterow;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenuItem group;
    private javax.swing.JMenuItem insertrow;
    private javax.swing.JButton jButtonChangeRow;
    private javax.swing.JButton jButtonDelCol;
    private javax.swing.JButton jButtonDelRow;
    private javax.swing.JButton jButtonGroupBy;
    private javax.swing.JButton jButtonInsertRow;
    private javax.swing.JButton jButtonSortColumn;
    private javax.swing.JButton jButtonUngroup;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jTablePanel;
    private javax.swing.JScrollPane jTableScrollPane;
    private javax.swing.JToolBar jTableToolBar;
    private javax.swing.JTree jTree;
    private javax.swing.JPanel jTreePanel;
    private javax.swing.JScrollPane jTreeScrollPane;
    private javax.swing.JToolBar jTreeToolBar;
    private javax.swing.JCheckBoxMenuItem largeIcons;
    private javax.swing.JMenuItem onlinehelp;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem preferences;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem sortcolumn;
    private javax.swing.JCheckBoxMenuItem treeVisible;
    private javax.swing.JMenuItem ungroup;
    private javax.swing.JMenuItem upper;

    public CSVEditorGUI()
    {
        super();
        resetCSVEditor();
        initComponents();
        changeActiveComponents();
        //        jTable.setCellSelectionEnabled(false);
    }

    /**
     * Initialisiert die Einstellungen des CSVEditor.
     */
    private void resetCSVEditor()
    {
        csvreader = null;
        csvtable = null;
        seltable = null;
        root = null;
        selnode = null;
    }

    /**
     * Setzt bestimmte Menueeintraege und die Splitpane aktiv/passiv, abhaengig
     * davon, ob csvtable <i>null</i> ist.
     * Setzt den Baum sichtbar/unsichtbar, falls so in den Einstellungen vermerkt
     */
    private void changeActiveComponents()
    {
        boolean a = (csvtable != null);
        save.setEnabled(a);
        EditMenu.setEnabled(a);
        jSplitPane.setVisible(a);

        boolean b = CSVEditor.csvproperties.isTreeVisible();
        jSplitPane.getLeftComponent().setVisible(b);
        if (b)
        {
            jSplitPane.setDividerLocation(CSVEditor.csvproperties.getDividerLocation());
        }

        boolean c = CSVEditor.csvproperties.isLargeIconsEnabled();
        if (c)
        {
            jButtonUngroup.setText(null);
            jButtonGroupBy.setText(null);
            jButtonSortColumn.setText(null);
            jButtonInsertRow.setText(null);
            jButtonChangeRow.setText(null);
            jButtonDelRow.setText(null);

            jButtonUngroup.setIcon(UIManager.getIcon("Tree.collapsedIcon"));
            jButtonGroupBy.setIcon(UIManager.getIcon("Tree.expandedIcon"));
            jButtonSortColumn.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
            jButtonInsertRow.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            jButtonChangeRow.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            jButtonDelRow.setIcon(UIManager.getIcon("OptionPane.informationIcon"));

        }
        else
        {
            jButtonUngroup.setIcon(null);
            jButtonGroupBy.setIcon(null);
            jButtonSortColumn.setIcon(null);
            jButtonInsertRow.setIcon(null);
            jButtonChangeRow.setIcon(null);
            jButtonDelRow.setIcon(null);
            jButtonUngroup.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("ungroup"));
            jButtonGroupBy.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("group_by"));
            jButtonSortColumn.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("sort"));
            jButtonInsertRow.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("insert_row"));
            jButtonChangeRow.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("change_row"));
            jButtonDelRow.setText(
                    java.util.ResourceBundle.getBundle("csvlang").getString("delete_row"));
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButtonDelCol = new javax.swing.JButton();
        delcolumn = new javax.swing.JMenuItem();
        jSplitPane = new javax.swing.JSplitPane();
        jTreePanel = new javax.swing.JPanel();
        jTreeScrollPane = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jTableToolBar = new javax.swing.JToolBar();
        jButtonUngroup = new javax.swing.JButton();
        jTablePanel = new javax.swing.JPanel();
        jTableScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jTreeToolBar = new javax.swing.JToolBar();
        jButtonGroupBy = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonSortColumn = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButtonInsertRow = new javax.swing.JButton();
        jButtonChangeRow = new javax.swing.JButton();
        jButtonDelRow = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        exit = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        insertrow = new javax.swing.JMenuItem();
        changerow = new javax.swing.JMenuItem();
        deleterow = new javax.swing.JMenuItem();
        sortcolumn = new javax.swing.JMenuItem();
        group = new javax.swing.JMenuItem();
        ungroup = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        upper = new javax.swing.JMenuItem();
        OptionMenu = new javax.swing.JMenu();
        preferences = new javax.swing.JMenuItem();
        treeVisible = new javax.swing.JCheckBoxMenuItem();
        largeIcons = new javax.swing.JCheckBoxMenuItem();
        HelpMenu = new javax.swing.JMenu();
        onlinehelp = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("csvlang"); // NOI18N
        jButtonDelCol.setText(bundle.getString("delete_column")); // NOI18N
        jButtonDelCol.setActionCommand("delcol");
        jButtonDelCol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });

        delcolumn.setText(bundle.getString("delete_column")); // NOI18N
        delcolumn.setActionCommand("delcol");
        delcolumn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });

        setTitle(bundle.getString("CSVEditor")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                exitForm(evt);
            }
        });

        jSplitPane.setDividerLocation(CSVEditor.csvproperties.getDividerLocation());
        jSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                jSplitPanePropertyChange(evt);
            }
        });

        jTreePanel.setLayout(new java.awt.BorderLayout());

        jTreeScrollPane.setPreferredSize(new java.awt.Dimension(181, 363));

        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
            {
                jTreeValueChanged(evt);
            }
        });
        jTreeScrollPane.setViewportView(jTree);

        jTreePanel.add(jTreeScrollPane, java.awt.BorderLayout.CENTER);

        jTableToolBar.setFloatable(false);
        jTableToolBar.setRollover(true);

        jButtonUngroup.setText(bundle.getString("ungroup")); // NOI18N
        jButtonUngroup.setToolTipText(bundle.getString("ungroup")); // NOI18N
        jButtonUngroup.setActionCommand("ungroup");
        jButtonUngroup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTableToolBar.add(jButtonUngroup);

        jTreePanel.add(jTableToolBar, java.awt.BorderLayout.NORTH);

        jSplitPane.setLeftComponent(jTreePanel);

        jTablePanel.setLayout(new java.awt.BorderLayout());

        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTableScrollPane.setViewportView(jTable);

        jTablePanel.add(jTableScrollPane, java.awt.BorderLayout.CENTER);

        jTreeToolBar.setFloatable(false);
        jTreeToolBar.setRollover(true);

        jButtonGroupBy.setText(bundle.getString("group_by")); // NOI18N
        jButtonGroupBy.setToolTipText(bundle.getString("group_by")); // NOI18N
        jButtonGroupBy.setActionCommand("groupby");
        jButtonGroupBy.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTreeToolBar.add(jButtonGroupBy);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setMaximumSize(new java.awt.Dimension(2, 32767));
        jTreeToolBar.add(jSeparator3);

        jButtonSortColumn.setText(bundle.getString("sort")); // NOI18N
        jButtonSortColumn.setToolTipText(bundle.getString("sort")); // NOI18N
        jButtonSortColumn.setActionCommand("sortcolumn");
        jButtonSortColumn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTreeToolBar.add(jButtonSortColumn);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setMaximumSize(new java.awt.Dimension(2, 32767));
        jTreeToolBar.add(jSeparator4);

        jButtonInsertRow.setText(bundle.getString("insert_row")); // NOI18N
        jButtonInsertRow.setToolTipText(bundle.getString("insert_row")); // NOI18N
        jButtonInsertRow.setActionCommand("insertrow");
        jButtonInsertRow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTreeToolBar.add(jButtonInsertRow);

        jButtonChangeRow.setText(bundle.getString("change_row")); // NOI18N
        jButtonChangeRow.setToolTipText(bundle.getString("change_row")); // NOI18N
        jButtonChangeRow.setActionCommand("changerow");
        jButtonChangeRow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTreeToolBar.add(jButtonChangeRow);

        jButtonDelRow.setText(bundle.getString("delete_row")); // NOI18N
        jButtonDelRow.setToolTipText(bundle.getString("delete_row")); // NOI18N
        jButtonDelRow.setActionCommand("delrow");
        jButtonDelRow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        jTreeToolBar.add(jButtonDelRow);

        jTablePanel.add(jTreeToolBar, java.awt.BorderLayout.NORTH);

        jSplitPane.setRightComponent(jTablePanel);

        getContentPane().add(jSplitPane, java.awt.BorderLayout.CENTER);

        FileMenu.setText(bundle.getString("File")); // NOI18N

        open.setText(bundle.getString("Open")); // NOI18N
        open.setActionCommand("open");
        open.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        FileMenu.add(open);

        save.setText(bundle.getString("Save")); // NOI18N
        save.setActionCommand("save");
        save.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        FileMenu.add(save);
        FileMenu.add(jSeparator1);

        exit.setText(bundle.getString("Exit")); // NOI18N
        exit.setActionCommand("exit");
        exit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        FileMenu.add(exit);

        jMenuBar.add(FileMenu);

        EditMenu.setText(bundle.getString("Edit")); // NOI18N

        insertrow.setText(bundle.getString("insert_row")); // NOI18N
        insertrow.setActionCommand("insertrow");
        insertrow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(insertrow);

        changerow.setText(bundle.getString("change_row")); // NOI18N
        changerow.setActionCommand("changerow");
        changerow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(changerow);

        deleterow.setText(bundle.getString("delete_row")); // NOI18N
        deleterow.setActionCommand("delrow");
        deleterow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(deleterow);

        sortcolumn.setText(bundle.getString("sort")); // NOI18N
        sortcolumn.setActionCommand("sortcolumn");
        sortcolumn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(sortcolumn);

        group.setText(bundle.getString("group_by")); // NOI18N
        group.setActionCommand("groupby");
        group.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(group);

        ungroup.setText(bundle.getString("ungroup")); // NOI18N
        ungroup.setActionCommand("ungroup");
        ungroup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(ungroup);
        EditMenu.add(jSeparator2);

        upper.setText(bundle.getString("substitute")); // NOI18N
        upper.setActionCommand("change");
        upper.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        EditMenu.add(upper);

        jMenuBar.add(EditMenu);

        OptionMenu.setText(bundle.getString("Options")); // NOI18N

        preferences.setText(bundle.getString("preferences")); // NOI18N
        preferences.setActionCommand("preferences");
        preferences.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        OptionMenu.add(preferences);

        treeVisible.setSelected(CSVEditor.csvproperties.isTreeVisible());
        treeVisible.setText(bundle.getString("tree_visible")); // NOI18N
        treeVisible.setActionCommand("treevisible");
        treeVisible.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        OptionMenu.add(treeVisible);

        largeIcons.setSelected(CSVEditor.csvproperties.isLargeIconsEnabled());
        largeIcons.setText(bundle.getString("large_icons")); // NOI18N
        largeIcons.setActionCommand("largeicons");
        largeIcons.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MenuAction(evt);
            }
        });
        OptionMenu.add(largeIcons);

        jMenuBar.add(OptionMenu);

        jMenuBar.add(Box.createHorizontalGlue());
        HelpMenu.setText(bundle.getString("Help")); // NOI18N

        onlinehelp.setText(bundle.getString("onlinehelp")); // NOI18N
        onlinehelp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onlinehelpActionPerformed(evt);
            }
        });
        HelpMenu.add(onlinehelp);

        jMenuBar.add(HelpMenu);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onlinehelpActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_onlinehelpActionPerformed
        // Add your handling code here:
        CSVHelp help = new CSVHelp(this, false);
        help.show();
    }//GEN-LAST:event_onlinehelpActionPerformed

    /**
     * falls der Divider der JSplitPane bewegt wird, wird die neue Position in
     * den csvproperties nichtpermanent gespeichert
     */
    private void jSplitPanePropertyChange(java.beans.PropertyChangeEvent evt)
    {//GEN-FIRST:event_jSplitPanePropertyChange
        if (evt.getPropertyName().equals(JSplitPane.DIVIDER_LOCATION_PROPERTY))
        {
            int divLoc = jSplitPane.getDividerLocation();
            if (jSplitPane.getLeftComponent().isVisible())
            {
                LOGGER.debug("divider position changed to " + Integer.toString(divLoc));
                CSVEditor.csvproperties.setDividerLocation(divLoc);
            }
        }
    }//GEN-LAST:event_jSplitPanePropertyChange

    private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {//GEN-FIRST:event_jTreeValueChanged
        if (!treeAdjust)
        {
            treeAdjust = true;
            TreePath tp = evt.getPath();
            selnode = (DefaultMutableTreeNode) tp.getLastPathComponent();

            /**
             * Es wird der erste Sohn des gewaelten Knotens gewdhlt, wenn ein Knoten
             * ohne dahinterliegender Tabelle ausgewdhlt wird
             */
            if (!selectableJTreeNode(selnode))
            {
                TreePath oldPath = new TreePath(((DefaultMutableTreeNode) (selnode)).getPath());
                if (jTree.isExpanded(oldPath))
                {
                    jTree.collapsePath(oldPath);
                }
                else
                {
                    jTree.expandPath(oldPath);
                }
                TreeNode newChosenNode = selnode.getParent();
                TreePath newChosenPath = new TreePath(
                        ((DefaultMutableTreeNode) (newChosenNode)).getPath());
                jTree.setSelectionPath(newChosenPath);
                treeAdjust = false;
                return;
            }

            // set the node data
            seltable = (CSVTable) selnode.getUserObject();
            jTable.setModel(seltable);
            jTree.setSelectionPath(tp);

            if (jTable.getRowCount() > 0)
            {
                focusTableRow(0);
            }
            treeAdjust = false;
        }
    }//GEN-LAST:event_jTreeValueChanged

    private void MenuAction(java.awt.event.ActionEvent evt)//GEN-FIRST:event_MenuAction
    {//GEN-HEADEREND:event_MenuAction
        LOGGER.info("MENU: " + evt.getActionCommand());

        if (evt.getActionCommand().equalsIgnoreCase("open"))
        {
            open();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("save"))
        {
            save();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("exit"))
        {
            exitForm(null);
        }
        else if (evt.getActionCommand().equalsIgnoreCase("ungroup"))
        {
            ungroup();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("groupby"))
        {
            groupby();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("treevisible"))
        {
            CSVEditor.csvproperties.setTreeVisible(treeVisible.isSelected());
            changeActiveComponents();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("largeicons"))
        {
            CSVEditor.csvproperties.setLargeIcons(largeIcons.isSelected());
            changeActiveComponents();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("preferences"))
        {
            String rv[] = CSVEditor.csvproperties.getPublicVarsArray();
            GlobalVarsGUI gvg = new GlobalVarsGUI(CSVEditor.csvproperties, rv, this);
            net.msk.utils.gui.Position.centerParent(this);
            gvg.setVisible(true);
            CSVEditor.csvproperties.store();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("delrow"))
        {
            delrow();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("insertrow"))
        {
            insertrow();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("changerow"))
        {
            changerow();
        }
        else if (evt.getActionCommand().equalsIgnoreCase("sortcolumn"))
        {
            int sortCol = seltable.getRealCol(jTable.getSelectedColumn());
            if (sortCol >= 0)
            {
                seltable.sort(sortCol);
                seltable.fireTableStructureChanged();
            }
            else
            {
                LOGGER.info("Vor dem Sortieren Spalte auswaehlen!");
            }
        }
        else if (evt.getActionCommand().equalsIgnoreCase("change"))
        {
            seltable.changeData(jTable.getSelectedColumn());
            seltable.fireTableDataChanged();
        }
        //      else if (evt.getActionCommand().equalsIgnoreCase("useformatfile")) {
        //      useFormatFile(); }
        //      else if (evt.getActionCommand().equalsIgnoreCase("delcol")) { delcol(); }
    }//GEN-LAST:event_MenuAction

    private void FileActionEvent(java.awt.event.ActionEvent evt)//GEN-FIRST:event_FileActionEvent
    {//GEN-HEADEREND:event_FileActionEvent
    }//GEN-LAST:event_FileActionEvent

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_exitForm
        //CSVEditor.csvproperties.store();
        System.exit(0);
    }//GEN-LAST:event_exitForm

    // zur Behandlung des Befehls open
    private void open()
    {
        JFileChooser fc = new JFileChooser(CSVEditor.csvproperties.getCurrentDir());
        JCheckBox cb = new JCheckBox(
                java.util.ResourceBundle.getBundle("csvlang").getString("use_formatfile"), false);
        fc.setAccessory(cb);

        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION)
        {
            resetCSVEditor();
            File selectedFile = fc.getSelectedFile();
            String filename = selectedFile.getAbsoluteFile().toString();
            csvreader = new CSVReader(filename);
            String main = java.util.ResourceBundle.getBundle("csvlang").getString("complete_table");
            csvtable = new CSVTable(main, csvreader);

            //falls das Optionsfeld use format file aktiviert wurde
            if (cb.isSelected())
            {
                if (csvreader.isFormatFileAvailable())
                {
                    useFormatFile();
                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            java.util.ResourceBundle.getBundle("csvlang")
                                                    .getString(
                                                            "Message_FormatFile_Not_Available") + csvreader
                                    .getFormatFilename());
                }
            }

            // set reader
            csvreader.readFile();

            // set window name
            this.setTitle(
                    selectedFile.getName() + " - " + java.util.ResourceBundle.getBundle("csvlang")
                                                                             .getString(
                                                                                     "CSVEditor"));

            csvtable.setDataByReader();
            seltable = csvtable;

            root = new DefaultMutableTreeNode();
            root.setUserObject(csvtable); //assigns the opened table to the root in the tree
            selnode = root;

            //now set the new model, because we have create a new object
            jTree.setModel(new DefaultTreeModel(root));
            redraw(); // show the name of the root
            //selektiere ersten Eintrag(=Wurzel) im Baum
            TreePath rootPath = jTree.getPathForRow(0);
            TreeSelectionEvent ev = new TreeSelectionEvent(jTree, rootPath, false, null, rootPath);
            jTreeValueChanged(ev);

            changeActiveComponents();

            //store selected path in properties
            CSVEditor.csvproperties.setCurrentDir(fc.getSelectedFile().getAbsolutePath());
        }
    }

    // zur Behandlung des Befehls speichern
    private void save()
    {
        JFileChooser fc = new JFileChooser(CSVEditor.csvproperties.getCurrentDir());
        JCheckBox cb = new JCheckBox(
                java.util.ResourceBundle.getBundle("csvlang").getString("with_header"), true);
        fc.setAccessory(cb);

        int retval = fc.showSaveDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION)
        {
            String target = fc.getSelectedFile().getAbsoluteFile().toString();
            csvreader.saveFile(target, cb.isSelected());

            //store selected path in properties
            CSVEditor.csvproperties.setCurrentDir(fc.getSelectedFile().getAbsolutePath());
        }
    }

    // zur Behandlung des Befehls insertrow
    private void insertrow()
    {
        // now create an dialog and insert the CSVLine into the CSVTable
        CSVTable defaults = (CSVTable) selnode.getUserObject();
        CSVLine line = new CSVLine();
        CSVLineInput linp = new CSVLineInput(this, csvtable, line, defaults.getHiddenColEntries(),
                csvreader.isFormatFileUsed(), false);
        linp.setVisible(true);

        // Konsolidiere alle Tabellen f|r alle Knoten im gesamten Baum
        if (linp.exitStateOK)
        {
            consolidateJTreeObjectStructureByAddingRow(line);
        }
    }

    // zur Behandlung des Befehls changerow
    private void changerow()
    {
        // falls mehrere Zeilen ausgewdhlt wurden, editiere nur die erste
        focusTableRow(jTable.getSelectedRow());

        // delete selected row
        CSVLine defaults = seltable.getRow(jTable.getSelectedRow());

        // now create an dialog and insert the CSVLine into the CSVTable
        CSVLine line = new CSVLine();
        CSVLineInput linp = new CSVLineInput(this, csvtable, line, defaults,
                csvreader.isFormatFileUsed(), true);
        linp.setVisible(true);

        // Konsolidiere alle Tabellen f|r alle Knoten im gesamten Baum
        if (linp.exitStateOK)
        {
            delrow();
            consolidateJTreeObjectStructureByAddingRow(line);
        }
    }

    // zur Behandlung des Befehls Gruppe loeschen
    private void ungroup()
    {
        if (!selectableJTreeNode(selnode))
        {
            LOGGER.debug("please select a selectable node first");
            return;
        }
        /**
         * Der Teilbaum mit dem selektierten Knoten als Wurzel wird gel&ouml;scht.
         * Als aktiver Knoten wird automatisch der Vater des Teilbaumes bestimmt.
         */
        DefaultMutableTreeNode node = selnode;
        try
        {
            selnode = (DefaultMutableTreeNode) node.getParent().getParent();
            ((DefaultMutableTreeNode) (node.getParent())).removeFromParent();
        } catch (NullPointerException e)
        {
            LOGGER.debug("kein Vaterknoten zu ermitteln: Root?");
        }

        redraw();
        //selektiere aktuellen Eintrag (=Vaterknoten) im Baum
        TreePath selPath = new TreePath(selnode.getPath());
        TreePath oldLeadPath = jTree.getLeadSelectionPath();
        TreeSelectionEvent ev = new TreeSelectionEvent(jTree, selPath, false, oldLeadPath, selPath);
        jTreeValueChanged(ev);
    }

    /**
     * Markiert die angegebene Zeile der aktuellen Tabelle.
     *
     * @param row die zu markierende Zeile
     **/
    private void focusTableRow(int row)
    {
        jTable.setRowSelectionInterval(row, row);
    }

    /**
     * konsolidiert die JTree Komponente
     */
    private void consolidateJTreeObjectStructureByAddingRow(CSVLine line)
    {
        /**
         * aendert die Tabellen DER Knoten, in deren Tabellen die CSVLine line hinzugefuegt wird
         */
        for (Enumeration el = root.preorderEnumeration(); el.hasMoreElements(); )
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) el.nextElement();
            // fuege nur ein, falls hinter dem Knoten eine Tabelle steht
            if (selectableJTreeNode(node))
            {
                /**
                 * falls alle versteckten Eintraege der Tabelle des aktuellen Knotens
                 * mit denen in der line uebereinstimmen, dann muss die line
                 * mit in die Tabelle aufgenommen werden
                 */
                CSVTable table = (CSVTable) node.getUserObject();
                Iterator itLine = line.iterator();
                Iterator itTable = table.getHiddenColEntries().iterator();
                Object tableEntry, lineEntry;

                boolean hiddencolsMatch = true;
                while (itTable.hasNext())
                {
                    tableEntry = itTable.next();
                    try
                    {
                        lineEntry = itLine.next();
                    } catch (NoSuchElementException e)
                    {
                        lineEntry = null;
                    }
                    // falls Spalte nicht verdeckt, darf dort in der neuen Zeile alles stehen
                    if (tableEntry == null)
                    {
                        continue;
                    }
                    // falls Spalte verdeckt, muss etwas dort in der neuen Zeile stehen
                    if (lineEntry == null)
                    {
                        hiddencolsMatch = false;
                        break;
                    }
                    // und zwar dasselbe wie sonst in allen anderen Zeilen dieser Tabelle
                    if (tableEntry.toString().equals(lineEntry.toString()))
                    {
                        continue;
                    }
                    // sonst sind die Anforderungen, um in die Tabelle aufgenommen zu werden
                    // nicht erfuellt
                    hiddencolsMatch = false;
                    break;
                }
                if (hiddencolsMatch)
                {
                    table.addRow(line);
                    line.addTableListener(table);
                    table.fireTableDataChanged();
                }
            }
        }
        /**
         * Es wird nun nach Knoten gesucht, die neu eingefuegt werden muessen:
         * Falls die line in einer Tabelle eines Knotens aufgenommen wurde und
         * dieser Knoten ein Vater ist, so muss die line auch in einem der Soehne
         * in jeder der Gruppierungen des Vaters auftauchen, falls nicht
         * wird ein neuer Sohn erstellt. Dessen Name ist der Eintrag der in der letzten
         * versteckten Spalte seiner Brueder steht. Also steht er genau in der Spalte, die die
         * Brueder vom Vater unterscheiden.
         */
        for (Enumeration el = root.preorderEnumeration(); el.hasMoreElements(); )
        {
            DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (el.nextElement()));
            if (!selectableJTreeNode(node))
            {
                continue;
            }
            // falls der Knoten kein Vater ist, braucht er nicht betrachtet zu werden
            if (node.isLeaf())
            {
                continue;
            }
            // wenn die line nicht in die Tabelle des Knotens aufgenommen wurde, braucht er nicht
            // betrachtet zu werden
            if (!((CSVTable) (node.getUserObject())).getData().contains(line))
            {
                continue;
            }
            // es wird geprueft, ob die line in jeder Gruppierung in einem der Soehne steht
            for (Enumeration eg = node.children(); eg.hasMoreElements(); )
            {
                DefaultMutableTreeNode groupnode = ((DefaultMutableTreeNode) (eg.nextElement()));
                boolean isInExistingChild = false;
                for (Enumeration ec = groupnode.children(); ec.hasMoreElements(); )
                {
                    DefaultMutableTreeNode child = ((DefaultMutableTreeNode) (ec.nextElement()));
                    // falls die line in einem der Soehne dieser Gruppierung steht,
                    // kann die nachste Gruppe betrachtet werden
                    if (((CSVTable) (child.getUserObject())).getData().contains(line))
                    {
                        isInExistingChild = true;
                        break;
                    }
                }
                // sonst fuege zu dieser Gruppe einen neues Blatt hinzu mit der
                // line als einzigsten Eintrag in der zugehoerigen Tabelle
                if (!isInExistingChild)
                {
                    Vector solo =
                            (Vector) (((CSVTable) (((DefaultMutableTreeNode) (groupnode.getFirstChild()))
                            .getUserObject())).getHiddenCol().clone());
                    boolean a = solo.removeAll(((CSVTable) (node.getUserObject())).getHiddenCol());
                    int col = ((Integer) (solo.get(0))).intValue();
                    solo = null;
                    String tmp = line.get(col).toString();
                    // create a new table with no data
                    CSVTable newtab = (CSVTable) ((CSVTable) (node.getUserObject())).clone();
                    newtab.setData(new Vector());
                    newtab.setName(tmp);
                    newtab.addHiddenCol(col, tmp);
                    newtab.addRow(line);
                    line.addTableListener(newtab);
                    int i = 0;
                    while ((i < groupnode.getChildCount()) && (0 > groupnode.getChildAt(i)
                                                                            .toString()
                                                                            .compareTo(tmp)))
                    {
                        i++;
                    }
                    groupnode.insert(new DefaultMutableTreeNode(newtab), i);
                }
            }
        }
        redraw();
    }

    /**
     * aendert die Tabellen DER Knoten, in deren Tabellen die CSVLine line geloescht wird
     */
    private void delrow()
    {
        int[] selrows = jTable.getSelectedRows();
        for (int selrowscount = jTable.getSelectedRowCount(); selrowscount > 0; selrowscount--)
        {
            int selrow = selrows[selrowscount - 1];
            if (selrow >= 0)
            {

                // betreffende CSVLine loeschen, alle Tabellen in ihrer Ansicht aktualisieren
                ((CSVTable) (selnode.getUserObject())).deleteRow(selrow);
                for (Enumeration el = root.preorderEnumeration(); el.hasMoreElements(); )
                {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) el.nextElement();
                    if (selectableJTreeNode(node))
                    {
                        ((CSVTable) (node.getUserObject())).fireTableStructureChanged();
                    }
                }

                /**
                 * es bestehen u.U. Knoten mit Tabellen ohne Eintraege
                 * diese werden nun geloescht
                 */
                for (Enumeration el = root.preorderEnumeration(); el.hasMoreElements(); )
                {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) el.nextElement();
                    if (selectableJTreeNode(node))
                    {
                        int rows = (((CSVTable) (node.getUserObject())).getRowCount());
                        if ((rows == 0) && (node != root))
                        {
                            if (node.getSiblingCount() == 1)
                            {
                                if (node == selnode)
                                {
                                    selnode = root; // eigentlich node.getParent().getParent(),
                                }
                                // dann aber selnode manchmal null (?!)
                                ((DefaultMutableTreeNode) node.getParent()).removeFromParent();
                            }
                            else
                            {
                                if (node == selnode)
                                {
                                    selnode =
                                            (DefaultMutableTreeNode) ((selnode.getNextSibling() != null) ? selnode
                                            .getNextSibling() : selnode.getPreviousSibling());
                                }
                                node.removeFromParent();
                            }
                        }
                    }
                }
                if (selrow >= jTable.getRowCount())
                {
                    selrow--;
                }
                if (selrow >= 0)
                {
                    focusTableRow(selrow);
                }
                redraw();
            }

            // FALLS NUN SELBST LEERE TABELLE angezeigt => ANSICHT AUF ANDERE TABELLE (selnode)
            // WECHSELN
            if (jTable.getRowCount() == 0)
            {
                try
                {
                    TreePath selPath = new TreePath(selnode.getPath());
                    TreePath oldLeadPath = jTree.getLeadSelectionPath();
                    TreeSelectionEvent ev = new TreeSelectionEvent(jTree, selPath, false,
                            oldLeadPath, selPath);
                    jTreeValueChanged(ev);
                    // hilfsweises Abfangen von unerwarteten selnodes
                } catch (IllegalArgumentException e)
                {
                    LOGGER.debug(
                            "Knoten " + selnode.toString() + " unerwartet ausgewdhlt, setze " +
                                    "aktuellen Knoten auf root");
                    selnode = root;
                    TreePath selPath = new TreePath(selnode.getPath());
                    TreePath oldLeadPath = jTree.getLeadSelectionPath();
                    TreeSelectionEvent ev = new TreeSelectionEvent(jTree, selPath, false,
                            oldLeadPath, selPath);
                    jTreeValueChanged(ev);
                }
            }
        }
    }

    /**
     * ist true, falls eine Tabelle hinter dem Knoten selnode steht
     */
    private boolean selectableJTreeNode(DefaultMutableTreeNode selnode)
    {
        return !(selnode == null || selnode.getUserObject().getClass() == java.lang.String.class);
    }

    private void redraw()
    {
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }

    // zur Behandlung beim Befehl Gruppieren
    private void groupby()
    {
        // falls eine Spalte in der Tabelle selektiert wurde,
        // erstelle den passenden Eintrag im Baum
        // in case of no selection do nothing
        if (jTable.getSelectedColumn() < 0)
        {
            LOGGER.debug("Please select column before grouping");
            return;
        }
        /* this complicated action groups the entries of a column,
         * therefore we sort the table, and create for every group
         * an entry at the tree */

        CSVTable currentTable = (CSVTable) selnode.getUserObject();
        int col = currentTable.getRealCol(jTable.getSelectedColumn());

        // create an group-node with empty user object
        DefaultMutableTreeNode groupnode = new DefaultMutableTreeNode(
                new String(csvtable.getColumnName(col)));
        selnode.add(groupnode);

        // from now we need the real column
        currentTable.sort(col);

        String tmp = new String("unexpected data: -=-");
        String tmpvgl = null;
        CSVLine ltmp = null;
        CSVTable tab = null;
        DefaultMutableTreeNode node = null;
        int line = 0;
        while ((ltmp = currentTable.getRow(line)) != null)
        {
            Object objvgl = ltmp.get(col);
            if (objvgl == null)
            {
                tmpvgl = "";
            }
            else
            {
                tmpvgl = objvgl.toString();
            }
            if (tmp.compareTo(tmpvgl) != 0)
            {
                // a new group is reached
                tmp = tmpvgl;
                // create a new table with no data
                tab = (CSVTable) currentTable.clone();
                tab.setData(new Vector());
                tab.setName(tmp);
                tab.addHiddenCol(col, tmp);
                // create a new Treenode
                node = new DefaultMutableTreeNode(tab);
                groupnode.add(node);
            }
            /* now add the data to the TreeNode and add the TableListener
             * this informs the CSVTable if the CSVLine is removed
             */
            if (node != null)
            {
                tab.addRow(ltmp);
                ltmp.addTableListener(tab);
            }
            line++;
        }
        redraw();
    }

    /**
     * Lese die Formatdatei. Ausserdem wird gleich die Ansicht angepasst, wie
     * sie in der Format Datei angegeben ist.
     */
    private void useFormatFile()
    {
        csvtable.getReader().readFormatFile();
        setTableHeaders();
    }

    /**
     * setze die Tabellenueberschriften neu
     */
    private void setTableHeaders()
    {
        Enumeration ecolNames = csvtable.getReader().getHeader().elements();
        Enumeration ecol = jTable.getTableHeader().getColumnModel().getColumns();
        while (ecol.hasMoreElements())
        {
            ((TableColumn) ecol.nextElement()).setHeaderValue(
                    ecolNames.hasMoreElements() ? ecolNames.nextElement() : "");
        }
        jTable.getTableHeader().repaint();
    }
    // End of variables declaration//GEN-END:variables

}
