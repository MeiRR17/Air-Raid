import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Gameplay extends JPanel implements KeyListener {
    /*In this class we're going to combine the whole game*/
    private final Player player;
    private final Bomb bomb;


    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT){
        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE.brighter());
        this.player = new Player(80, 80);
        this.player.start();
        this.bomb = null;
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.player.paint(graphics);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
