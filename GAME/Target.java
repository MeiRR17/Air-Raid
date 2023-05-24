import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Target extends Thread {
    private int START_X, START_Y;
    private int WIDTH, HEIGHT;
    private int END_Y;
    private int number;//number shown on the target
    private Gameplay gameplay;
    private Image image;


    public Target(int START_X, int START_Y, int WIDTH, int HEIGHT, int number, Image image) {
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.END_Y = START_Y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.number = number;
        this.image = image;
    }
    public void draw(Graphics g){
        g.drawImage(image, START_X, START_Y, START_X + WIDTH, START_Y + HEIGHT, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }

    public Rectangle calculateRectangle(){
        return new Rectangle(this.START_X,this.START_Y,this.WIDTH,this.HEIGHT);
    }
    public void fallDown(){
        this.START_Y++;
    }
    public void setY(int START_Y){
        this.START_Y=START_Y;
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
        return this.START_Y != this.END_Y;
    }

    public void set_Y(int y) {
        this.START_Y = y;
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

    public Image getImage() {
        return image;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    public int getWidth() {
        return this.WIDTH;
    }
}
