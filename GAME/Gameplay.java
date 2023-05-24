import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener
{
    /*In this class we're going to combine the whole game*/

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;


//
private final Target[][] target;
    private final Player[] planes;
    private final Bomb[] bombs;

    private final boolean[] pressedKeys;
    private static final int ENTER = 0;
    private static final int SPACE = 1;
    private static final int ROWS = 8;
    private static final int COLUMN = 38;
    private static int blueChecker = 0;
    private static int orangeChecker = 0;
    private static int orangeScore = 0;
    private static int blueScore = 0;
    private static boolean canFall = true;
    private final JLabel blueLabel;
    private final JLabel orangeLabel;
    private final JLabel blueHeart;
    private final JLabel orangeHeart;

    private final Image background = new ImageIcon("Resource/General/Background.png").getImage();


    public Gameplay(int START_X, int START_Y, int GAMEPLAY_WIDTH, int GAMEPLAY_HEIGHT)
    {

        this.setBounds(START_X, START_Y, GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setBackground(Color.BLUE);

        this.pressedKeys = new boolean[2];

        InputStream is = getClass().getResourceAsStream("Resource/Font/Pixel.ttf");

        blueLabel = new JLabel("SCORE: 0");
        orangeLabel = new JLabel("SCORE: 0");
        blueHeart = new JLabel();
        orangeHeart = new JLabel();
        try
        {
            File fontFile = new File("Resource/Font/Pixel.ttf");
            Font word = Font.createFont(Font.TRUETYPE_FONT,fontFile);
            Font customFontSized = word.deriveFont(12f);
            orangeLabel.setFont(customFontSized);
            blueLabel.setFont(customFontSized);
        }
        catch (FontFormatException | IOException e)
        {
            e.printStackTrace();
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

        Image[] planeImages = {
                new ImageIcon(styleOptionsBlue[new Random().nextInt(styleOptionsBlue.length)]).getImage(),
                new ImageIcon(styleOptionsOrange[new Random().nextInt(styleOptionsOrange.length)]).getImage()
        };

        Image[] bombImages = {
                new ImageIcon("Resource/Bomb/Blue.png").getImage(),
                new ImageIcon("Resource/Bomb/Orange.png").getImage()
        };

        planes = new Player[]{
                new Player(
                        WIDTH / 2,
                        WIDTH / 8,
                        HEIGHT / 4,
                        planeImages[0].getWidth(null) / 10,
                        planeImages[0].getHeight(null) / 10,
                        planeImages[0]
                ), new Player(
                        WIDTH / 2,
                        WIDTH / 8 * 7,
                        HEIGHT / 4,
                        planeImages[1]
                                .getWidth(null) / 10,
                        planeImages[1]
                                .getHeight(null) / 10,
                        planeImages[1]
                )
        };

        bombs = new Bomb[]{
                new Bomb(
                        planes[0].getX(),
                        planes[0].getY(),
                        planeImages[0].getWidth(null) / 30,
                        planeImages[0].getWidth(null) / 30,
                        10,
                        bombImages[0]
                ),
                new Bomb(
                        planes[1].getX(),
                        planes[1].getY(),
                        planeImages[1].getWidth(null) / 30,
                        planeImages[1].getWidth(null) / 30,
                        10,
                        bombImages[1]
                )
        };


        this.planes[0].start();this.planes[1].start();
        this.bombs[0].start();this.bombs[1].start();

        boolean isRunning = true;

        int blue = 0;
        int orange = 1;






        this.target = new Target[ROWS][COLUMN];


        initializeTargets();


        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();


        new Thread(() ->
        {
            while (true)
            {
                repaint();

                handlePlayerLoop(blue, -20);
                handlePlayerLoop(orange, WIDTH + 20);

                dropBomb(blue);
                dropBomb(orange);

                handleBombReturn(blue);
                handleBombReturn(orange);



                Rectangle bombRect = this.bombs[0].calculateRectangle();
                Rectangle bomb2Rect = this.bombs[1].calculateRectangle();

                checkCollisions();


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
        this.planes[0].paint(graphics);
        this.planes[1].paint(graphics);
        this.bombs[0].paint(graphics);
        this.bombs[1].paint(graphics);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMN; j++) {
                this.target[i][j].draw(graphics);
            }
        }
    }
    private void miniLoop(){

    }






    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    private void initializeTargets() {
        Target baseTarget = new Target(3, 550, 18, 18, 1, new ImageIcon("Resource/Target/1.png").getImage());

        int numberType = 1;

        for (int i = 0; i < ROWS; i++) {
            if(i % 2 == 0 && i != 0){
                numberType++;
            }
            for (int j = 0; j < COLUMN; j++) {
                Target target = new Target(
                        j * (baseTarget.getX() + baseTarget.getHEIGHT()),
                        baseTarget.getY() + (i * baseTarget.getWIDTH()),
                        20,
                        20,
                        numberType,
                        new ImageIcon("Resource/Target/"+ numberType +".png").getImage()
                );
                this.target[i][j] = target;
                this.target[i][j].start();
            }
        }
    }

    private void handlePlayerLoop(int color, int startingPoint) {
        if (this.planes[color].getX() > getWidth() &&
                (this.bombs[color].getX() >= getWidth() && this.bombs[color].getY() == this.planes[color].getY())) {
            // Makes the player loop
            this.planes[color].setX(startingPoint);
            this.bombs[color].setX(startingPoint);
        } else if (this.planes[color].getX() < -20 &&
                (this.bombs[color].getX() <= -20 && this.bombs[color].getY() == this.planes[color].getY())) {
            this.planes[color].setX(startingPoint);
            this.bombs[color].setX(startingPoint);
        }
    }
    private void dropBomb(int bombIndex) {
        if (this.pressedKeys[bombIndex]) {
            if (bombIndex == 0) {
                this.bombs[bombIndex].moveRight();
            } else if (bombIndex == 1) {
                this.bombs[bombIndex].moveLeft();
            }
            this.bombs[bombIndex].setSLEEP(40);
        }
    }
    private void handleBombReturn(int bombIndex) {
        if (this.bombs[bombIndex].getY() == getHeight()) {
            this.bombs[bombIndex].reload(planes[bombIndex].getX(), planes[bombIndex].getY());
            this.bombs[bombIndex].setSLEEP(10);
            if (bombIndex == 0) {
                this.pressedKeys[ENTER] = false;
                if (this.bombs[bombIndex].getX() == getWidth()) {
                    this.bombs[bombIndex].setX(this.planes[bombIndex].getX());
                }
            } else if (bombIndex == 1) {
                this.pressedKeys[SPACE] = false;
                if (this.bombs[bombIndex].getX() == -10) {
                    this.bombs[bombIndex].setX(this.bombs[bombIndex].getX());
                }
            }
        }
    }
    private void checkCollisions() {
        Rectangle bombRect = this.bombs[0].calculateRectangle();
        Rectangle bomb2Rect = this.bombs[1].calculateRectangle();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMN; j++) {
                Rectangle targetRect = this.target[i][j].calculateRectangle();

                handleCollision(0, bombRect, targetRect, i, j);
                handleCollision(1, bomb2Rect, targetRect, i, j);

                if (orangeChecker == 7) {
                    orangeChecker = 0;
                    this.bombs[1].reload(this.planes[1].getX(), this.planes[1].getY());
                    this.bombs[1].setSLEEP(10);
                    this.pressedKeys[SPACE] = false;
                }
                if (blueChecker == 7) {
                    blueChecker = 0;
                    this.bombs[0].reload(this.planes[0].getX(), this.planes[0].getY());
                    this.bombs[0].setSLEEP(10);
                    this.pressedKeys[ENTER] = false;
                }

            }
        }
    }


    private void handleCollision(int bombIndex, Rectangle bombRect, Rectangle targetRect, int i, int j) {
        if (Utils.checkCollision(bombRect, targetRect)) {
            this.target[i][j] = new Target(0, 0, 0, 0, 0, new ImageIcon("Resource/Target/1.png").getImage());

            if (bombIndex == 0) {
                blueChecker++;
                blueScore += this.target[i][j].getNumber();
                blueLabel.setText("SCORE: " + blueScore);
            } else if (bombIndex == 1) {
                orangeChecker++;
                orangeScore += this.target[i][j].getNumber();
                orangeLabel.setText("SCORE: " + orangeScore);
            }
        }
    }






    @Override
    public void keyTyped(KeyEvent e) {}

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
        if (this.bombs[0].getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                toRelease = ENTER;
            }
        } else if (this.bombs[1].getY() == HEIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                toRelease = SPACE;
            }
        }
        if (toRelease != null) {
            this.pressedKeys[toRelease] = false;
        }
    }
}