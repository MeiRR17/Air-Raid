import java.awt.*;

public class Player extends Thread {
        /*In this class we're going to make player's plane*/
    private int START_X;
    private int START_Y;
    private final int WIDTH;
    private final int HEIGHT;
    private final int speed;
    private Image image;

    private Color color = Color.WHITE;

    public Player (int speed, int START_X, int START_Y, int width, int height, String imagePath) {
        /*ADDED PLANE'S HEIGHT AND WIDTH*/
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.image = image;
        this.speed = speed;
        ImageIcon icon = new ImageIcon(imagePath);

    }

    public void moveRight(){
        START_X += speed;
    }

    public void paint(Graphics graphics){
        graphics.drawImage(image, START_X, START_Y, START_X + WIDTH, START_Y + HEIGHT, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }
    public int getX(){
        return this.START_X;
    }
    public void fly(){
        new Thread(() -> {
            while (true){
                START_X++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
