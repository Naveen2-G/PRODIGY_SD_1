import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdvancedTempConverter extends JFrame {
    private JTextField celsiusField, fahrenheitField, kelvinField;
    private JTextArea historyArea;
    private TemperatureDial dial;
    private JLabel suggestionLabel;
    private ArrayList<String> history = new ArrayList<>();
    private JComboBox<String> inputType;

    public AdvancedTempConverter() {
        setTitle("\uD83C\uDF21️ Advanced Temperature Converter");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createInputPanel(), BorderLayout.WEST);
        mainPanel.add(createVisualPanel(), BorderLayout.CENTER);
        mainPanel.add(createHistoryPanel(), BorderLayout.EAST);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        celsiusField = new JTextField();
        fahrenheitField = new JTextField();
        kelvinField = new JTextField();

        panel.add(new JLabel("Celsius (°C):"));
        panel.add(celsiusField);
        panel.add(new JLabel("Fahrenheit (°F):"));
        panel.add(fahrenheitField);
        panel.add(new JLabel("Kelvin (K):"));
        panel.add(kelvinField);

        panel.add(new JLabel("Input Type:"));
        inputType = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        panel.add(inputType);

        suggestionLabel = new JLabel(" ");
        panel.add(suggestionLabel);
        panel.add(new JLabel(" "));

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> convertTemperature());

        JButton clearButton = new JButton("Clear");
        JButton exportButton = new JButton("Export History");

        clearButton.addActionListener(e -> clearFields());
        exportButton.addActionListener(e -> exportHistory());

        panel.add(convertButton);
        panel.add(clearButton);
        panel.add(exportButton);

        return panel;
    }

    private JPanel createVisualPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        dial = new TemperatureDial();
        panel.add(dial, BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane createHistoryPanel() {
        historyArea = new JTextArea(20, 25);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Conversion History"));
        return scrollPane;
    }

    private void convertTemperature() {
        try {
            double celsius = 0;
            String input = (String) inputType.getSelectedItem();

            switch (input) {
                case "Celsius":
                    celsius = Double.parseDouble(celsiusField.getText());
                    fahrenheitField.setText(String.format("%.2f", (celsius * 9 / 5) + 32));
                    kelvinField.setText(String.format("%.2f", celsius + 273.15));
                    break;
                case "Fahrenheit":
                    double f = Double.parseDouble(fahrenheitField.getText());
                    celsius = (f - 32) * 5 / 9;
                    celsiusField.setText(String.format("%.2f", celsius));
                    kelvinField.setText(String.format("%.2f", celsius + 273.15));
                    break;
                case "Kelvin":
                    double k = Double.parseDouble(kelvinField.getText());
                    celsius = k - 273.15;
                    celsiusField.setText(String.format("%.2f", celsius));
                    fahrenheitField.setText(String.format("%.2f", (celsius * 9 / 5) + 32));
                    break;
            }

            dial.setTemperature(celsius);
            dial.setFillFraction((celsius + 30) / 100.0); // Adjust for -30°C to 70°C range
            updateHistory(celsius);
            updateSuggestion(celsius);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void updateHistory(double celsius) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String entry = String.format("[%s] %.2f°C = %.2f°F, %.2fK", timestamp, celsius, (celsius * 9 / 5) + 32, celsius + 273.15);
        history.add(entry);
        historyArea.append(entry + "\n");
    }

    private void updateSuggestion(double c) {
        String suggestion;
        if (c <= 0) suggestion = "\uD83E\uDD76 Freezing! Stay indoors.";
        else if (c <= 15) suggestion = "\uD83E\uDDF5 Cold: Wear a jacket.";
        else if (c <= 25) suggestion = "\uD83C\uDF24️ Pleasant: Enjoy the weather.";
        else if (c <= 35) suggestion = "\uD83D\uDE0E Warm: Stay hydrated.";
        else if (c <= 45) suggestion = "\uD83D\uDD25 Hot: Use sunscreen.";
        else suggestion = "\uD83E\uDD75 Extreme Heat! Stay cool.";
        suggestionLabel.setText(suggestion);
    }

    private void clearFields() {
        celsiusField.setText("");
        fahrenheitField.setText("");
        kelvinField.setText("");
        suggestionLabel.setText(" ");
        dial.setTemperature(0);
        dial.setFillFraction(0);
    }

    private void exportHistory() {
        try (FileWriter writer = new FileWriter("temperature_history.txt")) {
            for (String entry : history) {
                writer.write(entry + "\n");
            }
            JOptionPane.showMessageDialog(this, "History exported to temperature_history.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting history.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdvancedTempConverter::new);
    }
}

class TemperatureDial extends JPanel {
    private double temperature = 0;
    private double fillFraction = 0;

    public void setTemperature(double temp) {
        this.temperature = temp;
        repaint();
    }

    public void setFillFraction(double fillFraction) {
        this.fillFraction = Math.max(0.0, Math.min(1.0, fillFraction));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        int fillRadius = (int) (radius * fillFraction);
        g2.setColor(new Color(255, 100, 100));
        g2.fillOval(centerX - fillRadius, centerY - fillRadius, fillRadius * 2, fillRadius * 2);

        // Draw tick marks and labels
        for (int i = -30; i <= 70; i += 10) {
            double angle = Math.toRadians(180 * ((i + 30) / 100.0));
            int outerX = centerX - (int) (radius * Math.cos(angle));
            int outerY = centerY + (int) (radius * Math.sin(angle));
            int innerX = centerX - (int) ((radius - 10) * Math.cos(angle));
            int innerY = centerY + (int) ((radius - 10) * Math.sin(angle));
            g2.setColor(Color.BLACK);
            g2.drawLine(outerX, outerY, innerX, innerY);
            g2.drawString(i + "°C", outerX - 10, outerY - 5);
        }

        g2.setColor(Color.BLACK);
        g2.drawString(String.format("%.1f°C", temperature), centerX - 20, centerY - radius - 10);

        double angle = Math.toRadians(180 * ((temperature + 30) / 100.0));
        int needleLength = radius - 20;
        int x2 = centerX - (int) (needleLength * Math.cos(angle));
        int y2 = centerY + (int) (needleLength * Math.sin(angle));

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLUE);
        g2.drawLine(centerX, centerY, x2, y2);
    }
}