import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    private final Player blue;
    private final Player orange;
    private final Bomb blueBomb;
    private final Bomb orangeBomb;
    private final Target[][] target;

    private final boolean[] pressedKeys;
    private static final int ENTER =0;
    private static final int SPACE =1;
    private static final int ROWS=7;
    private static final int COLUMN=37;
    private static int count1=0;
    private static int count2=0;
    private static boolean canFall=true;



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
                        "Resource/Player/ORANGE_TEAM/option1.png",
                        "Resource/Player/ORANGE_TEAM/option2.png",
                        "Resource/Player/ORANGE_TEAM/option3.png",
                        "Resource/Player/ORANGE_TEAM/option4.png",
                        "Resource/Player/ORANGE_TEAM/option5.png"
                };



        Image bluePlane =
                new ImageIcon(styleOptionsBlue[new Random().nextInt(styleOptionsBlue.length)]).getImage();
        Image orangePlane =
                new ImageIcon(styleOptionsOrange[new Random().nextInt(styleOptionsOrange.length)]).getImage();

        this.blue =
                new Player(WIDTH / 2, WIDTH / 8, HEIGHT / 4 , bluePlane.getWidth(null) / 10, bluePlane.getHeight(null) / 10, bluePlane);
        this.orange =
                new Player(WIDTH / 2, WIDTH / 8 * 7, HEIGHT / 4, orangePlane.getWidth(null) / 10, orangePlane.getHeight(null) / 10, orangePlane);

        this.blueBomb =
                new Bomb(blue.getX(), blue.getY(), bluePlane.getWidth(null) / 30, bluePlane.getWidth(null) / 30, 10);
        this.orangeBomb =
                new Bomb(orange.getX(), orange.getY(), bluePlane.getWidth(null) / 30, bluePlane.getWidth(null) / 30, 10);


        this.blue.start();
        this.orange.start();
        this.blueBomb.start();
        this.orangeBomb.start();

        boolean isRunning = true;

        this.target = new Target[ROWS][COLUMN];
        Target target = new Target(3, 480, 18, 18, 5, 7, new ImageIcon("Resource/Target/1.png").getImage());

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
                if (this.blue.getX() > GAMEPLAY_WIDTH && (this.blueBomb.getX() >= GAMEPLAY_WIDTH && this.blueBomb.getY() == this.blue.getY())){// Makes the player loop
                    this.blue.setX(-30);
                    this.blueBomb.setX(-30);
                }
                if (this.orange.getX()<-40 && (this.orangeBomb.getX() <-40 && this.orangeBomb.getY() == this.orange.getY())){
                    this.orange.setX(GAMEPLAY_WIDTH+20);
                    this.orangeBomb.setX(GAMEPLAY_WIDTH+20);
                }

                if (this.pressedKeys[ENTER]){ // When pressed enter it drops the bomb
                    this.blueBomb.moveRight();
                    this.blueBomb.setSLEEP(40);
                }
                if (this.pressedKeys[SPACE]) {
                    this.orangeBomb.moveLeft();
                    this.orangeBomb.setSLEEP(40);
                }
                if (this.blueBomb.getY() == GAMEPLAY_HEIGHT ){ // Makes the bomb return after hitting something
                    this.blueBomb.reload(blue.getX(), blue.getY());
                    this.blueBomb.setSLEEP(10);
                    this.pressedKeys[ENTER] =false;
                    if (this.blueBomb.getX() == GAMEPLAY_WIDTH){
                        this.blueBomb.setX(this.blue.getX());
                    }
                }
                if (this.orangeBomb.getY() == GAMEPLAY_HEIGHT ){
                    this.orangeBomb.reload(this.orange.getX(),this.orange.getY());
                    this.orangeBomb.setSLEEP(10);
                    this.pressedKeys[SPACE] = false;
                    if (this.orangeBomb.getX() == -10){
                        this.orangeBomb.setX(this.orangeBomb.getX());
                    }
                }
                Rectangle bombRect = this.blueBomb.calculateRectangle();
                Rectangle bomb2Rect = this.orangeBomb.calculateRectangle();
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMN; j++) {
                        Rectangle targetRect= this.target[i][j].calculateRectangle();
                        if (Utils.checkCollision(bombRect,targetRect)){
                            this.target[i][j] = new Target(0,0,0,0,0,0, new ImageIcon("Resource/Target/1.png").getImage());// That may cause the problem
                            count1++;
                        }
                        if (Utils.checkCollision(bomb2Rect,targetRect)){
                            this.target[i][j] = new Target(0,0,0,0,0,0, new ImageIcon("Resource/Target/1.png").getImage());// That may cause the problem
                            count2++;
                        }
                        if (count2==4){
                            count2 =0;
                            this.orangeBomb.reload(this.orange.getX(),this.orange.getY());
                            this.orangeBomb.setSLEEP(10);
                            this.pressedKeys[SPACE] = false;
                        }
                        if (count1==4){
                            count1 =0;
                            this.blueBomb.reload(this.blue.getX(),this.blue.getY());
                            this.blueBomb.setSLEEP(10);
                            this.pressedKeys[ENTER] =false;
                        }
                        if (i!=0 && this.target[i-1][j].getY()!=0 && this.target[i][j].getY()==0 && canFall){
                            this.target[i-1][j].fallDown();
                            if (this.target[i-1][j].getY() == this.target[i-1][j-1].getY() && this.target[i][j].getX()!=0 && j!=0){
                                this.target[i-1][j].setEND_Y(this.target[i-1][j-1].getY());
                                canFall = false;
                            }
//                            if (this.target[i-1][j].getY() == this.target[7][j].getY() && this.target[7][j].getX()==0){
//                                this.target[i-1][j].setEND_Y(this.target[7][j].getY());
//                                canFall = false;
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
        this.blue.paint(graphics);
        this.orange.paint(graphics);
        this.blueBomb.draw(graphics);
        this.orangeBomb.draw(graphics);
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
        if (this.blueBomb.getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                toRelease = ENTER;
            }
        } else if (this.orangeBomb.getY() == HEIGHT) {
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