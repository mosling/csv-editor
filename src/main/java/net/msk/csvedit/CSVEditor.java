package net.msk.csvedit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Der Editor dient zum Bearbeiten von vorhandnen CSV-Dateien. Dabei wurde
 * bei der Erstellung des Programmes auf maximale Flexibilit"auml;t geachtet.
 * Jetzt einige Punkte, die kennzeichnend sind
 * <ul>
 * <li>sehr flexibler Lese- und Schreibmechanismus</li>
 * <li>automatische Typerkennung</li>
 * <li>L&ouml;schen und Einf&uuml;gen von Spalten und Zeilen
 * <li>Sortierung nach einer Spalte</li>
 * <li>Gruppierung nach einer Spalte, d.h. in der Gruppe erscheinen
 * nur die Zeilen mit der gew&auml;hlten Gruppeneigenschaft (Spalte)</li>
 * <li>Ersetzung mit regul&auml;ren Ausdr&uuml;cken in den Zeichenketten</li>
 * <li>einfache Formeln zur Neuberechnung von numerischen Werten</li>
 * </ul>
 * Trotz all dieser Vorz&uuml;ge handelt es sich nicht um eine Tabellenkalkulation,
 * so da&szlig; der Bezug auf andere Spalten als der aktuellen bei Berechnungen
 * entf&auml;llt. Ebenso werden neuen Zeilen und Spalten nicht auf die einzelnen
 * Gruppen aufgeteilt.
 */

public class CSVEditor
{

    // globale Einstellungen für die Anwendung
    static public CSVProperties csvproperties;
    private static Logger LOGGER = LogManager.getLogger(CSVEditor.class);

    public static void main(String args[])
    {

        try (InputStream configStream = CSVEditor.class.getResourceAsStream("/config/default.json"))
        {
            if (null != configStream)
            {
                ObjectMapper mapper = new ObjectMapper();
                csvproperties = mapper.readValue(configStream, CSVProperties.class);
            }
        } catch (IOException e)
        {
            LOGGER.debug(e);
            LOGGER.warn(e.getMessage());

            csvproperties = new CSVProperties();
        }

        CSVEditorGUI gui = new CSVEditorGUI();
        gui.setVisible(true);
    }
}
