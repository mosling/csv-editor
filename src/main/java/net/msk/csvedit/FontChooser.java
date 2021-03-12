package net.msk.csvedit;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class FontChooser {

    // Put this in other two combo-boxes if you want to make these selectable by user
    public static int default_size = 16;
    public static int default_style = Font.PLAIN;

    public static void main(String[] args) {

        UIManager.put("defaultFont", new FontUIResource(Font.SERIF, Font.BOLD, 20));
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[] {400};
        layout.rowHeights = new int[] {100,300};

        JFrame container = new JFrame();
        container.setLayout(layout);
        container.setBounds(150,150,400,400);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> comboFontNames = new JComboBox<String>(fonts);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);

        GridBagConstraints comboContraints = new GridBagConstraints();
        comboContraints.gridx = 0;
        comboContraints.gridy = 0;
        // This only set font to display on combo
        comboFontNames.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        container.add(comboFontNames, comboContraints);

        GridBagConstraints scrollerContraints = new GridBagConstraints();
        scrollerContraints.gridx = 0;
        scrollerContraints.gridy = 1;
        scrollerContraints.gridwidth = 400;
        scrollerContraints.fill = GridBagConstraints.BOTH;
        container.add(scrollPane, scrollerContraints);

        // Variant of action listener with lambda (since java 8)
        comboFontNames.addActionListener((e) -> {

            String selectedFamilyName = (String)comboFontNames.getSelectedItem();
            Font selectedFont = new Font(selectedFamilyName, default_style, default_size);

            textArea.setFont(selectedFont);
            textArea.repaint();
        });

        container.setVisible(true);
    }
}
