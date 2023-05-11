import java.awt.*;

public class Bomb {
    private int START_X;
    private int START_Y;
    private final int speed;
    private final int WIDTH = 15;
    private final int HEIGHT = 15;
    private final Color color = Color.BLACK;

    public Bomb(int START_X, int START_Y, int speed){
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.speed = speed;
    }

    public void move(){
        START_Y += speed;
        START_X -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(START_X + 12, START_Y + 30, WIDTH, HEIGHT);
    }
}
