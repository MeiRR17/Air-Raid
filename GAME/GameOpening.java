import javax.swing.*;
import java.awt.*;

public class GameOpening {
    private ImageIcon background;
    private final JLabel backgroundsLabel;
    private final JPanel titlePanel;
    private final JButton startButton;


    public GameOpening(JFrame jFrame, int GAME_OPENING_WIDTH, int GAME_OPENING_HEIGHT) {
        backgroundsLabel = new JLabel(new ImageIcon("Resource/General/Background.png"));
        backgroundsLabel.setSize(800, 800);


        titlePanel = new JPanel();
        titlePanel.setBounds(GAME_OPENING_WIDTH / 8, GAME_OPENING_HEIGHT / 6, GAME_OPENING_WIDTH / 4 * 3, GAME_OPENING_HEIGHT / 4);
        titlePanel.setBackground(Color.BLUE.brighter());

        JLabel titleNameLabel = new JLabel("AIR-RAID");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 90));
        titlePanel.add(titleNameLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(GAME_OPENING_WIDTH / 8 * 3, GAME_OPENING_HEIGHT / 6 * 4, GAME_OPENING_WIDTH / 4, GAME_OPENING_HEIGHT / 8);
        startButtonPanel.setBackground(Color.BLUE.brighter());

        startButton = new JButton("START");
        startButton.setBackground(Color.GRAY.brighter());

        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Times New Roman", Font.PLAIN, 55));
        startButtonPanel.add(startButton);
        titlePanel.add(startButtonPanel);

        jFrame.add(titlePanel);
    }
    public JPanel getPanel() {
        return titlePanel;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
