package cuccovillo.alessio.jtail;

import cuccovillo.alessio.jtaillib.controller.TailController;
import cuccovillo.alessio.jtail.gui.JTailGUI;
import cuccovillo.alessio.jtaillib.Settings;
import java.util.Locale;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author alessio
 */
public class JTail {

    private final static String VERSION = "1.0";

    public static void main(String[] args) throws ParseException {
        Settings settings = new Settings(args);
        if (settings.isGUI()) {
            if (System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).contains("mac")) {
                // take the menu bar off the jframe
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                // set the name of the application menu item
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
            }
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignore) {
            }
            SwingUtilities.invokeLater(() -> {
                new JTailGUI().setVisible(true);
            });
        } else {
            new TailController(settings, (c) -> {
                System.out.print(c);
            }).startTail();
        }
    }

    public static String getVersion() {
        return VERSION;
    }

}
