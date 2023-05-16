import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


    public Window(){
        this.setResizable(false);
        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLUE);
    }
    public void showWindow(){
        this.setVisible(true);
    }
}
