package fisheries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Welcome screen for the application. */
public class BEGIN extends JFrame {
    private static final Color INK = new Color(238, 246, 248);
    private static final Color MUTED = new Color(185, 211, 219);
    private static final Color ACCENT = new Color(83, 220, 196);

    public BEGIN() {
        setTitle("Fisheries Duration Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        GradientPanel background = new GradientPanel();
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(48, 58, 48, 58));

        JPanel copy = new JPanel();
        copy.setOpaque(false);
        copy.setLayout(new javax.swing.BoxLayout(copy, javax.swing.BoxLayout.Y_AXIS));

        JLabel eyebrow = label("FISHERIES OPERATIONS TOOL", new Font("SansSerif", Font.BOLD, 12), ACCENT);
        JLabel heading = label("Trip duration,\ncalculated clearly.", new Font("SansSerif", Font.BOLD, 48), INK);
        JLabel description = label("Enter departure and arrival times to calculate vessel-trip duration, including journeys that continue past midnight.", new Font("SansSerif", Font.PLAIN, 17), MUTED);
        description.setMaximumSize(new java.awt.Dimension(520, 62));
        description.setAlignmentX(LEFT_ALIGNMENT);

        JButton start = new JButton("Open calculator  →");
        start.setAlignmentX(LEFT_ALIGNMENT);
        start.setFont(new Font("SansSerif", Font.BOLD, 15));
        start.setForeground(new Color(7, 41, 53));
        start.setBackground(ACCENT);
        start.setBorder(BorderFactory.createEmptyBorder(14, 19, 14, 19));
        start.setFocusPainted(false);
        start.addActionListener(event -> {
            new MAINFRAME().setVisible(true);
            dispose();
        });

        copy.add(eyebrow);
        copy.add(javax.swing.Box.createVerticalStrut(18));
        copy.add(heading);
        copy.add(javax.swing.Box.createVerticalStrut(18));
        copy.add(description);
        copy.add(javax.swing.Box.createVerticalStrut(32));
        copy.add(start);
        background.add(copy, BorderLayout.CENTER);
        setContentPane(background);
    }

    private JLabel label(String text, Font font, Color color) {
        JLabel label = new JLabel("<html>" + text.replace("\n", "<br>") + "</html>");
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setPaint(new GradientPaint(0, 0, new Color(7, 35, 49), getWidth(), getHeight(), new Color(11, 75, 91)));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(new Color(83, 220, 196, 35));
            g2.fillOval(getWidth() - 320, -100, 420, 420);
            g2.setColor(new Color(255, 255, 255, 15));
            g2.fillOval(getWidth() - 520, getHeight() - 220, 360, 360);
            g2.dispose();
        }
    }
}
