import java.awt.*;

public class Bomb extends Thread{
    private int START_X;
    private static int SLEEP =10;
    private int START_Y;
    private final int WIDTH;
    private final int HEIGHT;
    private boolean canDrop;


    public Bomb(int START_X, int START_Y, int width, int height){
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.canDrop = true;
    }

    public void moveRight(){
        START_Y++;
        START_X++;
    }
    public int getX(){
        return START_X;
    }
    public void setX(int START_X){
        this.START_X = START_X;
    }
    public int getY(){
        return START_Y;
    }

    public boolean isCanDrop() {
         return this.canDrop;
    }
    public void setCanDrop(boolean canDrop){
        this.canDrop = canDrop;
    }
    public void setSLEEP(int sleep){
        SLEEP = sleep;
    }



    public void reload(int playersX, int playersY){
        this.START_X = playersX;
        this.START_Y = playersY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(START_X, START_Y, WIDTH, HEIGHT);
    }
    public void run(){
        while (true){
            this.START_X++;
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
