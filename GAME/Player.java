import java.awt.*;

public class Player extends Thread {
        /*In this class we're going to make player's plane*/
    private int START_X;
    private int moveX;
    private int START_Y;
    private int moveY;
    private final int speed;
    private Image image;

    private Color color = Color.WHITE;

    public Player (int speed, int START_X, Image image) {
        /*ADDED PLANE'S HEIGHT AND WIDTH*/
        this.START_X = START_X;
        this.moveX=START_X;
        this.START_Y = START_Y;
        this.moveY = START_Y;
        this.image = image;
        this.speed = speed;
    }

    public void moveRight(){
        START_X += speed;
    }
    public void moveLeft(){
        START_X -= speed;
    }

    public void paint(Graphics graphics){
        graphics.fillRect(this.START_X,this.START_Y, 10, 10);
        graphics.setColor(Color.black);
        graphics.drawImage(image, START_X, START_Y, null);

    }
    public int getX(){
        return this.START_X;
    }
    public int getY(){
        return this.START_Y;
    }
    public void setX(int START_X){
        this.START_X = START_X;
    }
    public void run(){
        while (true){
            this.START_X++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
