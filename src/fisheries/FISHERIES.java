package fisheries;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Application entry point for the Fisheries Duration Calculator. */
public final class FISHERIES {
    private FISHERIES() { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setSystemLookAndFeel();
            new BEGIN().setVisible(true);
        });
    }

    private static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // The application remains usable with the default Swing look and feel.
        }
    }
}
