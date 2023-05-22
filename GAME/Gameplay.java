import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

class Gameplay extends JPanel implements KeyListener {
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
    private final Target[][] target;
    private final Bomb bomb;
    private final Bomb bomb2;
    private final boolean[] pressedKeys;
    private static final int ENTER =0;
    private static final int SPACE =1;
    private static final int ROWS=7;
    private static final int COLUMN=37;
    private static int count1=0;
    private static int count2=0;

    private final Image background = new ImageIcon("Resource/General/Background.png").getImage();

    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT){

        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE);

        this.pressedKeys = new boolean[2];

        InputStream is = getClass().getResourceAsStream("Resource/Font/Pixel.ttf");
        try {
            if (is != null) {
                /*In this class we're going to combine the whole game*/
                Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
            }
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }


        String [] styleOptionsBlue =
                {
                        "Resource/Player/BLUE_TEAM/option1.png",
                        "Resource/Player/BLUE_TEAM/option2.png",
                        "Resource/Player/BLUE_TEAM/option3.png",
                        "Resource/Player/BLUE_TEAM/option4.png",
                        "Resource/Player/BLUE_TEAM/option5.png"
                };
        String [] styleOptionsOrange =
                {
                        "Resource/Player/BLUE_TEAM/option1.png",
                        "Resource/Player/BLUE_TEAM/option2.png",
                        "Resource/Player/BLUE_TEAM/option3.png",
                        "Resource/Player/BLUE_TEAM/option4.png",
                        "Resource/Player/BLUE_TEAM/option5.png"
                };
        String blueRandom = (styleOptionsBlue[new Random().nextInt(styleOptionsBlue.length)]);
        String orangeRandom = (styleOptionsOrange[new Random().nextInt(styleOptionsOrange.length)]);

        Image bluePlane = new ImageIcon(blueRandom).getImage();
        Image orangePlane = new ImageIcon(orangeRandom).getImage();
        this.player = new Player(WIDTH /8, 18, 180 ,150 ,150 , new ImageIcon("Resource/Window/gameIcon.jpg").getImage());
        this.player.start();
        this.player2 = new Player(WIDTH /8,600,140,150,150,new ImageIcon("Resource/Window/gameIcon.jpg").getImage());
        this.player2.start();
        this.bomb = new Bomb(player.getX(),player.getY(),50,50,10);
        this.bomb.start();
        this.bomb2 = new Bomb(player2.getX(),player2.getY(),50,50,10);
        this.bomb2.start();
        boolean isRunning = true;
        this.target = new Target[ROWS][COLUMN];
        Target target = new Target(3,480,18,18,5,7, new ImageIcon("Resource/Target/1.png").getImage());
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMN; j++) {
                Target target2 = new Target(j*(target.getX()+target.getHEIGHT()),target.getY()+(i*target.getWIDTH()),20,20,5,7, new ImageIcon("Resource/Target/1.png").getImage());
                this.target[i][j]= target2;
                this.target[i][j].start();
            }
        }
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();


        new Thread(() -> {
            while (true) {
                repaint();
                if (this.player.getX() > GAMEPLAY_WIDTH && (this.bomb.getX() >= GAMEPLAY_WIDTH && this.bomb.getY() == this.player.getY())){// Makes the player loop
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
                if (this.bomb.getY() == GAMEPLAY_HEIGHT ){ // Makes the bomb return after hitting something
                    this.bomb.reload(player.getX(),player.getY());
                    this.bomb.setSLEEP(10);
                    this.pressedKeys[ENTER] =false;
                    if (this.bomb.getX() == GAMEPLAY_WIDTH){
                        this.bomb.setX(this.player.getX());
                    }
                }
                if (this.bomb2.getY() == GAMEPLAY_HEIGHT ){
                    this.bomb2.reload(this.player2.getX(),this.player2.getY());
                    this.bomb2.setSLEEP(10);
                    this.pressedKeys[SPACE] = false;
                    if (this.bomb2.getX() == -10){
                        this.bomb2.setX(this.bomb2.getX());
                    }
                }
                Rectangle bombRect = this.bomb.calculateRectangle();
                Rectangle bomb2Rect = this.bomb2.calculateRectangle();
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMN; j++) {
                        Rectangle targetRect= this.target[i][j].calculateRectangle();
                        if (Utils.checkCollision(bombRect,targetRect) || Utils.checkCollision(bomb2Rect,targetRect)){
                            this.target[i][j] = new Target(0,0,0,0,0,0, new ImageIcon("Resource/Target/1.png").getImage());// That may cause the problem
                            count1++;
                            count2++;
                        }
                        if (count1==4){
                            count1 =0;
                            this.bomb2.reload(this.player2.getX(),this.player2.getY());
                            this.bomb2.setSLEEP(10);
                            this.pressedKeys[SPACE] = false;
                        }
                        if (count2==4){
                            count2=0;
                            this.bomb.reload(player.getX(),player.getY());
                            this.bomb.setSLEEP(10);
                            this.pressedKeys[ENTER] =false;
                        }
                        if (i!=0 && this.target[i-1][j].getY()!=0 && this.target[i][j].getY()==0){
                            this.target[i-1][j].fallDown();
                            if (this.target[i-1][j].getY() == this.target[i][j].getY() && this.target[i][j].getX()!=0){
                                this.target[i-1][j].set_Y(this.target[i][j].getY());
                            }
//                            if (this.target[i-1][j].getY() == this.target[7][j].getY() && this.target[7][j].getX()==0){
//                                this.target[i-1][j].setEND_Y(this.target[7][j].getY());
//                            }
                        }
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();



    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Paint the background first
        graphics.drawImage(background, 0, 0, null);

        // Paint other components
        this.player.paint(graphics);
        this.player2.paint(graphics);
        this.bomb.draw(graphics);
        this.bomb2.draw(graphics);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMN; j++) {
                this.target[i][j].draw(graphics);
            }
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
        Integer toRelease = null;
        if (this.bomb.getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                toRelease = ENTER;
            }
        } else if (this.bomb2.getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                toRelease = SPACE;
            }
        }
        if (toRelease != null) {
            this.pressedKeys[toRelease] = false;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}