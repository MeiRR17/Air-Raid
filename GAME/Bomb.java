import java.awt.*;

public class Bomb {
    private int START_X;
    private int START_Y;
    private final int speed;
    private final int WIDTH;
    private final int HEIGHT;
    private final Color color;

    public Bomb(int START_X, int START_Y, int speed, int width, int height, Color color){
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.speed = speed;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.color = color;
    }

    public void move(){
        START_Y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(START_X, START_Y, WIDTH, HEIGHT);
    }
}
