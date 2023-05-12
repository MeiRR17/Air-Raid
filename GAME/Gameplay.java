import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

class Gameplay extends JPanel implements KeyListener {
    /*In this class we're going to combine the whole game*/
    private final Player player;
    private final Bomb bomb;
    private boolean[] pressedKeys;
    public static final int ENTER =0;
    public static final int SPACE =0;

    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT){
        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE.brighter());
        this.pressedKeys = new boolean[2];


        Image planeImage = Im
        this.player = new Player(1000, 180, 180 ,150 ,150 , image);
        this.player.fly();
        this.player.start();
        this.bomb = null;
        boolean isRunning = true;
        addKeyListener(this);
        setFocusable(true);


//        Target target = new Target(100,100,50,50, new BufferedImage());


    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.player.paint(graphics);
        if(bomb != null){
            bomb.draw(graphics);
        }
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer toPress =null;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            toPress = ENTER;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toPress = SPACE;
        }
        if (toPress != null){
            this.pressedKeys[toPress] =true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer toRelease =null;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            toRelease = ENTER;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toRelease = SPACE;
        }
        if (toRelease != null){
            this.pressedKeys[toRelease] =false;
        }
    }
}
