import javax.swing.*;
import java.awt.*;

public class RulesWindow extends JFrame {



    public RulesWindow(){
        this.setSize(800,800);
        this.setBackground(Color.black);
        this.setTitle("Rules Before The Gaming / RBTG");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        JLabel rules = new JLabel("Game Mechanics:\n The game has 2 players who control planes that automatically fly.\n" +
                "The players can only launch bombs to destroy objects on the canyon.\nThe objects are placed in specific locations:\n" +
                "4 points, 3 points, 2 points and 1 point, can be placed according to their rarity.\n"+
                "Each player can only launch one bomb at a time.\nOnce an object is destroyed, it does not reappear.\n"
                );
        rules.setBounds(2,0,800,800);
        rules.setForeground(Color.BLACK);
        this.add(rules);
    }
    public void showWindow(){//This method will make the JFrame visible
        this.setVisible(true);/*JFrame's build-in method to set it to be visible*/
    }
}
