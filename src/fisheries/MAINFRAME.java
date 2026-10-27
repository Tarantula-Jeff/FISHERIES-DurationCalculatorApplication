package fisheries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Main calculator window. Times are entered in 24-hour HH:mm format. */
public class MAINFRAME extends JFrame {
    private static final Color INK = new Color(238, 246, 248);
    private static final Color MUTED = new Color(175, 199, 207);
    private static final Color ACCENT = new Color(83, 220, 196);
    private static final Color CARD = new Color(16, 56, 68);
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    private final JTextField departureField = new JTextField();
    private final JTextField arrivalField = new JTextField();
    private final JLabel resultLabel = new JLabel("Enter both times to calculate the trip duration.");

    public MAINFRAME() {
        setTitle("Fisheries Duration Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setMinimumSize(new Dimension(760, 500));
        setLocationRelativeTo(null);

        GradientPanel background = new GradientPanel();
        background.setLayout(new GridBagLayout());
        background.setBorder(BorderFactory.createEmptyBorder(34, 40, 34, 40));

        JPanel card = createCard();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        background.add(card, constraints);
        setContentPane(background);
    }

    private JPanel createCard() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 35)),
            BorderFactory.createEmptyBorder(34, 40, 34, 40)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 8, 0);

        JLabel eyebrow = createLabel("TRIP TIME CALCULATOR", new Font("SansSerif", Font.BOLD, 11), ACCENT);
        card.add(eyebrow, c);
        c.gridy = 1;
        JLabel title = createLabel("Calculate vessel-trip duration", new Font("SansSerif", Font.BOLD, 29), INK);
        card.add(title, c);
        c.gridy = 2;
        c.insets = new Insets(0, 0, 28, 0);
        JLabel helper = createLabel("Use 24-hour time in HH:mm format. If arrival is earlier than departure, the trip is treated as overnight.", new Font("SansSerif", Font.PLAIN, 14), MUTED);
        card.add(helper, c);

        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 7, 16);
        card.add(createLabel("Departure time", new Font("SansSerif", Font.BOLD, 12), INK), c);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 7, 0);
        card.add(createLabel("Arrival time", new Font("SansSerif", Font.BOLD, 12), INK), c);

        configureField(departureField, "e.g. 08:30");
        configureField(arrivalField, "e.g. 16:45");
        c.gridy = 4;
        c.gridx = 0;
        c.weightx = .5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 22, 16);
        card.add(departureField, c);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 22, 0);
        card.add(arrivalField, c);

        JButton calculate = createButton("Calculate duration", ACCENT, new Color(6, 39, 50));
        calculate.addActionListener(event -> calculateDuration());
        JButton clear = createButton("Clear", new Color(33, 82, 96), INK);
        clear.addActionListener(event -> clearCalculator());
        c.gridy = 5;
        c.gridx = 0;
        c.weightx = .5;
        c.insets = new Insets(0, 0, 24, 16);
        card.add(calculate, c);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 24, 0);
        card.add(clear, c);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(new Color(10, 43, 54));
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(83, 220, 196, 90)),
            BorderFactory.createEmptyBorder(18, 20, 18, 20)
        ));
        JLabel resultCaption = createLabel("CALCULATED DURATION", new Font("SansSerif", Font.BOLD, 10), ACCENT);
        resultLabel.setForeground(INK);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        resultPanel.add(resultCaption, BorderLayout.NORTH);
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        card.add(resultPanel, c);
        return card;
    }

    private void calculateDuration() {
        try {
            LocalTime departure = LocalTime.parse(departureField.getText().trim(), TIME_FORMAT);
            LocalTime arrival = LocalTime.parse(arrivalField.getText().trim(), TIME_FORMAT);
            Duration duration = Duration.between(departure, arrival);
            if (duration.isNegative()) duration = duration.plusDays(1);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            resultLabel.setForeground(INK);
            resultLabel.setText(String.format("%d hours %02d minutes", hours, minutes));
        } catch (DateTimeParseException exception) {
            resultLabel.setForeground(new Color(255, 169, 179));
            resultLabel.setText("Use a valid time, for example 08:30.");
        }
    }

    private void clearCalculator() {
        departureField.setText("");
        arrivalField.setText("");
        resultLabel.setForeground(INK);
        resultLabel.setText("Enter both times to calculate the trip duration.");
        departureField.requestFocusInWindow();
    }

    private void configureField(JTextField field, String tooltip) {
        field.setToolTipText(tooltip);
        field.setFont(new Font("SansSerif", Font.PLAIN, 17));
        field.setForeground(INK);
        field.setCaretColor(ACCENT);
        field.setBackground(new Color(9, 40, 51));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 45)),
            BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));
    }

    private JButton createButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(13, 16, 13, 16));
        return button;
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setPaint(new GradientPaint(0, 0, new Color(6, 32, 44), getWidth(), getHeight(), new Color(12, 91, 102)));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(new Color(83, 220, 196, 30));
            g2.fillOval(getWidth() - 250, -100, 350, 350);
            g2.dispose();
        }
    }
}
