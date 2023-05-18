import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class GameOpening {
    private final JPanel panel;
    private final JButton startButton;

    public GameOpening(JFrame jFrame, int GAME_OPENING_WIDTH, int GAME_OPENING_HEIGHT) {
        panel = new JPanel();
        panel.setBounds(GAME_OPENING_WIDTH / 8, GAME_OPENING_HEIGHT / 6, GAME_OPENING_WIDTH / 4 * 3, GAME_OPENING_HEIGHT / 4);
        panel.setBackground(Color.BLUE.brighter());

        JLabel titleNameLabel = new JLabel("AIR-RAID");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 90));
        panel.add(titleNameLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(GAME_OPENING_WIDTH / 8 * 3, GAME_OPENING_HEIGHT / 6 * 4, GAME_OPENING_WIDTH / 4, GAME_OPENING_HEIGHT / 8);
        startButtonPanel.setBackground(Color.BLUE.brighter());

        startButton = new JButton("START");
        startButton.setBackground(Color.BLUE.brighter());
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        startButtonPanel.add(startButton);

        panel.add(startButtonPanel);

        jFrame.add(panel);
    }
    public JPanel getPanel() {
        return panel;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
