import java.awt.*;

public class Player extends Thread {
        /*In this class we're going to make player's plane*/
    private int START_X;
    private int START_Y;
    private int WIDTH = 30;
    private int HEIGHT = 30;
    private int speed = 5;


    private Color color = Color.WHITE;

    public Player (int START_X, int START_Y) {
        /*ADDED PLANE'S HEIGHT AND WIDTH*/
        this.START_X = START_X;
        this.START_Y = START_Y;
        //To get his place
    }

    public void moveRight(){
        START_X += speed;
    }

    public void paint(Graphics graphics){
        graphics.setColor(color);
        graphics.fillRect(this.START_X, START_Y, WIDTH, HEIGHT);
    }
    public int getX(){
        return this.START_X;
    }
    public void fly(){
        Thread fling =new Thread();
        while (true){
            START_X++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
