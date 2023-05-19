import Helper.Gameplay;

import javax.swing.*;
import java.awt.*;

public class Player extends Thread {
    /*In this class we're going to make player's plane*/
    private int START_X;
    private int moveX;
    private int START_Y;
    private int moveY;
    private final int WIDTH;
    private final int HEIGHT;
    private final int speed;
    private Gameplay gameplay;
    private Image image;

    private Color color = Color.WHITE;

    public Player (int speed, int START_X, int START_Y, int width, int height, Image image) {
        /*ADDED PLANE'S HEIGHT AND WIDTH*/
        this.START_X = START_X;
        this.moveX=START_X;
        this.START_Y = START_Y;
        this.moveY = START_Y;
        this.WIDTH = width;
        this.HEIGHT = height;
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
        graphics.drawImage(image, START_X, START_Y, START_X + WIDTH, START_Y + HEIGHT, 0, 0, image.getWidth(null), image.getHeight(null), null);

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
            if (this.moveX > 0 && this.moveX < 800/4){
                this.START_X++;
            }else {
                this.START_X--;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
