import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    /*In this class we're going to put the game with its window settings*/

    public Window(){
        this.setSize(800,800);
        this.setLayout(null);
        this.setResizable(false);
        Gameplay gameplay = new Gameplay(0, 0, 800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLUE);
    }
    public void showWindow(){
        this.setVisible(true);
    }
}
