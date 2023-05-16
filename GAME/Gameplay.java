import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

class Gameplay extends JPanel implements KeyListener {
    /*In this class we're going to combine the whole game*/
    private static final int TARGET_RADIUS = 30;
    private static final int PLAYER_WIDTH = 60;
    private static final int PLAYER_HEIGHT = 70;

    private static final int MAX_BOMBS = 3;
    private static final int BOMB_SPEED = 5;
    private static final int TARGET_POINTS = 10;
    private static final int BOMB_POINTS = -5;
    private static final int WIDTH =800;
    private static final int HEIGHT =600;

    private final Player player;
    private Target target;
    private final Bomb bomb;
    private boolean[] pressedKeys;
    public static final int ENTER =0;
    public static final int SPACE =0;

    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT){
        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE.brighter());
        this.pressedKeys = new boolean[2];


        this.player = new Player(WIDTH /8, 18, 180 ,150 ,150 , new ImageIcon("Resource/Window/gameIcon.jpg").getImage());
        this.player.start();
        this.bomb = new Bomb(player.getX(),player.getY(),50,50);
        this.bomb.start();
        boolean isRunning = true;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        new Thread(() -> {
            while (true) {
                repaint();
                if (player.getX() == GAMEPLAY_WIDTH || player.getX() > GAMEPLAY_WIDTH){// Makes the player loop
                    player.setX(-30);
                }
                if (bomb.getY() == GAMEPLAY_WIDTH && bomb.getY() == player.getY()){
                    bomb.setX(-30);
                }

                if (this.pressedKeys[ENTER]){ // When pressed enter it drops the bomb
                    bomb.moveRight();
                    bomb.setSLEEP(40);
                    if (bomb.getX() == GAMEPLAY_WIDTH){ // Makes the bomb loop with the player
                        bomb.setX(bomb.getX());
                    }
                }
                if (bomb.getY() == GAMEPLAY_HEIGHT){ // Makes the bomb return after hitting something
                    bomb.reload(player.getX(),player.getY());
                    bomb.setSLEEP(10);
                    this.pressedKeys[ENTER] =false;
                    if (bomb.getX() == GAMEPLAY_WIDTH){
                        bomb.setX(player.getX());
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


//        Target target = new Target(100,100,50,50, new BufferedImage());


    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.player.paint(graphics);
        if(bomb != null){
            bomb.draw(graphics);
        }
    }
    private void miniLoop(){

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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toRelease = SPACE;
        }
        if (toRelease != null){
            this.pressedKeys[toRelease] =false;
        }
    }






    public void spawnTargets() {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 5) {
            int x = random.nextInt(WIDTH - 100);
            int y = 0;
            int radius = 50;
            int number = random.nextInt(10) + 1;
            BufferedImage image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.YELLOW);
            g.fillOval(0, 0, radius * 2, radius * 2);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, radius));
            int stringWidth = g.getFontMetrics().stringWidth(Integer.toString(number));
            g.drawString(Integer.toString(number), radius - stringWidth / 2, radius + radius / 2);
            g.dispose();

        }
    }
}