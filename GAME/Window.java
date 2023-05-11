import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    ImageIcon icon = new ImageIcon("Resource/gameIcon.jpg");


    public Window(){

        this.setTitle("Air Raid");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);/*Set the size of the JFrame*/
        this.setLocationRelativeTo(null);/*Make the Window pop-up in the middle of the screen (width, height)*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*Make the program end when closing the window with code 0*/
        this.setLayout(null);/*Make the ability to control how the components added to the container are arranged and sized*/
        this.setResizable(false);/*Disable window's resize*/





        JPanel titleNamePanel = new JPanel();
        titleNamePanel.setBounds(WINDOW_WIDTH/8, WINDOW_HEIGHT/8, WINDOW_WIDTH/4*3, WINDOW_HEIGHT/4);
        titleNamePanel.setBackground(Color.BLUE.brighter());
        JLabel titleNameLabel = new JLabel("Air Raid");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setBounds(WINDOW_WIDTH/8, WINDOW_HEIGHT / 8, WINDOW_WIDTH/4*3, WINDOW_HEIGHT/16*3);
        titleNameLabel.setFont(new Font("Monospaced", Font.ITALIC, 30));

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(WINDOW_WIDTH / 8 * 3, WINDOW_HEIGHT/2, WIDTH / 4, HEIGHT / 8);
        startButtonPanel.setBackground(Color.BLUE.brighter());

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLUE.brighter());
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Monospaced", Font.ITALIC, 30));

        this.add(titleNamePanel);
        this.add(startButtonPanel);

        setIconImage(icon.getImage());
//        GridLayout gridLayout = new GridLayout(1,1);




//        Gameplay gameplay = new Gameplay(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
//        /*Create new Gameplay Object and make it the same size as the window*/
//
//
//
//        JButton button = new JButton("button");
//        this.add(button);
//        button.setBounds(400,300,100,100);
//        button.addActionListener((e -> {
//            button.setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
//            button.setVisible(false);
//            this.add(gameplay);/*Add the gameplay object*/
//        }));
        //we need to add a new button on the scene (Label), that is why I think we need a new class here...The
    }
    public void showWindow(){//This method will make the JFrame visible
        this.setVisible(true);/*JFrame's build-in method to set it to be visible*/
    }

}
