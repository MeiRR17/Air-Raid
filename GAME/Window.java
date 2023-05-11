import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


    public Window(){
        this.setSize(800,800);
        this.setLayout(null);
        this.setResizable(false);
        Gameplay gameplay = new Gameplay(0, 0, 800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLUE);
        this.add(gameplay);
    }
    public void showWindow(){
        this.setVisible(true);
    }
}
