import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private final Player player2;
    private Target target;
    private Bomb bomb;
    private final Bomb bomb2;
    private boolean[] pressedKeys;
    public static final int ENTER =0;
    public static final int SPACE =1;

    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT){
        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE.brighter());
        this.pressedKeys = new boolean[2];


        this.player = new Player(18, 180, new ImageIcon("Resource/Player/BLUE_TEAM/Option1.png").getImage());
        this.player.start();
        this.player2 = new Player(WIDTH /8,600, new ImageIcon("Resource/Window/gameIcon.jpg").getImage());
        this.player2.start();
        this.bomb = new Bomb(player.getX(),player.getY(),50,50,10);
        this.bomb.start();
        this.bomb2 = new Bomb(player2.getX(),player2.getY(),50,50,10);
        this.bomb2.start();
        boolean isRunning = true;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        new Thread(() -> {
            while (true) {
                repaint();
                if (this.player.getX() >= GAMEPLAY_WIDTH && (this.bomb.getX() == GAMEPLAY_WIDTH && this.bomb.getY() == this.player.getY())){// Makes the player loop
                    this.player.setX(-30);
                    this.bomb.setX(-30);
                }
                if (this.player2.getX()<-40 && (this.bomb2.getX() < -40 && this.bomb2.getY() == this.player2.getY())){
                    this.player2.setX(GAMEPLAY_WIDTH+30);
                    this.bomb2.setX(GAMEPLAY_WIDTH+30);
                }

                if (this.pressedKeys[ENTER]){ // When pressed enter it drops the bomb
                    this.bomb.moveRight();
                    this.bomb.setSLEEP(40);
                }
                if (this.pressedKeys[SPACE]) {
                    this.bomb2.moveLeft();
                    this.bomb2.setSLEEP(40);
                }
                if (this.bomb.getY() == GAMEPLAY_HEIGHT){ // Makes the bomb return after hitting something
                    this.bomb.reload(player.getX(),player.getY());
                    this.bomb.setSLEEP(10);
                    this.pressedKeys[ENTER] =false;
                    if (this.bomb.getX() == GAMEPLAY_WIDTH){
                        this.bomb.setX(this.player.getX());
                    }
                }
                if (this.bomb2.getY() == GAMEPLAY_HEIGHT){
                    this.bomb2.reload(this.player2.getX(),this.player2.getY());
                    this.bomb2.setSLEEP(10);
                    this.pressedKeys[SPACE] = false;
                    if (this.bomb2.getX() == -10){
                        this.bomb2.setX(this.bomb2.getX());
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
        this.player2.paint(graphics);
        this.bomb.draw(graphics);
        this.bomb2.draw(graphics);
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
        Integer toRelease = null;
        if (this.bomb.getY() == HEIGHT){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                toRelease = ENTER;
            }
        }
         else if (this.bomb2.getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                toRelease = SPACE;
            }
        }
        if (toRelease != null){
            this.pressedKeys[toRelease] =false;
        }
    }






//    public void spawnTargets() {
//        Random random = new Random();
//        int chance = random.nextInt(100);
//        if (chance < 5) {
//            int x = random.nextInt(WIDTH - 100);
//            int y = 0;
//            int radius = 50;
//            int number = random.nextInt(10) + 1;
//            BufferedImage image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = image.createGraphics();
//            g.setColor(Color.YELLOW);
//            g.fillOval(0, 0, radius * 2, radius * 2);
//            g.setColor(Color.BLACK);
//            g.setFont(new Font("Arial", Font.BOLD, radius));
//            int stringWidth = g.getFontMetrics().stringWidth(Integer.toString(number));
//            g.drawString(Integer.toString(number), radius - stringWidth / 2, radius + radius / 2);
//            g.dispose();
//
//        }
//    }
}