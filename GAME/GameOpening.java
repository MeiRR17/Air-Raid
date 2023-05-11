import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class GameOpening {
    private JPanel panel;
    private JButton startButton;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public GameOpening(JFrame parent) {
        panel = new JPanel();
        panel.setBounds(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 6, WINDOW_WIDTH / 4 * 3, WINDOW_HEIGHT / 4);
        panel.setBackground(Color.BLUE.brighter());

        JLabel titleNameLabel = new JLabel("AIR-RAID");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 90));
        panel.add(titleNameLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(WINDOW_WIDTH / 8 * 3, WINDOW_HEIGHT / 6 * 4, WINDOW_WIDTH / 4, WINDOW_HEIGHT / 8);
        startButtonPanel.setBackground(Color.BLUE.brighter());

        startButton = new JButton("START");
        startButton.setBackground(Color.BLUE.brighter());
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        startButtonPanel.add(startButton);

        panel.add(startButtonPanel);

        parent.add(panel);
    }
    public JPanel getPanel() {
        return panel;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
