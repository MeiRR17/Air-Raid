import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Target extends Thread {
    private int START_X, START_Y;
    private int WIDTH, HEIGHT;
    private int radius;
    private int END_Y;
    private int number;//number shown on the target
    private BufferedImage image;
    private Gameplay gameplay;

    public Target(int START_X, int START_Y, int WIDTH, int HEIGHT, int radius, int number) {
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.END_Y = START_Y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.radius = radius;
        this.number = number;

    }
    public void draw(Graphics g){
        g.fillRect(this.START_X,this.START_Y,this.WIDTH,this.HEIGHT);
        g.setColor(Color.GREEN);
        g.drawImage(image, START_X - radius, START_Y - radius, radius*2, radius * 2, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, radius));

        int stringWidth = g.getFontMetrics().stringWidth(Integer.toString(number));
        g.drawString(Integer.toString(number), START_X - stringWidth / 2, START_Y + radius / 2);
    }

    public boolean hit(int START_X, int START_Y){
        return Math.pow((START_X - this.START_X), 2) + Math.pow((START_Y - this.START_Y), 2) <= Math.pow(radius, 2);
    }
    public Rectangle calculateRectangle(){
        return new Rectangle(this.START_X,this.START_Y,this.WIDTH,this.HEIGHT);
    }
    public void fallDown(){
        this.START_Y++;
        this.END_Y +=20;
    }

    public int getNumber(){
        return this.number;
    }

    public int getEND_Y() {
        return this.END_Y;
    }

    public int getHEIGHT(){
        return this.HEIGHT;
    }

    public int getX() {
        return this.START_X;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public int getY() {
        return this.START_Y;
    }

    public void setEND_Y(int END_Y) {
        this.END_Y = END_Y;
    }
    public boolean canFall(){
        boolean falls = false;
        if (this.START_Y != this.END_Y){
            falls = true;
        }
        return falls;
    }

    public void run(){
        while (true){
            if (this.START_Y != 610 && this.START_Y==this.END_Y ){
                fallDown();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
