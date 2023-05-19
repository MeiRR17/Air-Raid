import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Target {
    private int START_X, START_Y;
    private int SIZE;
    private int END_Y;
    private  Color color;
    private BufferedImage image;

    public Target(int START_X, int START_Y, int SIZE,Color color) {
        this.START_X = START_X;
        this.START_Y = START_Y;
        this.END_Y = START_Y;
        this.SIZE = SIZE;
        this.color = color;

    }
    public void draw(Graphics g){
        if (this.START_Y < this.END_Y){
            this.START_Y +=2;
        }
        g.fillRect(this.START_X,this.START_Y,this.SIZE,this.SIZE);
        g.setColor(this.color);
//        g.drawImage(image, START_X - radius, START_Y - radius, radius*2, radius * 2, null);
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, radius));

//        int stringWidth = g.getFontMetrics().stringWidth(Integer.toString(number));
//        g.drawString(Integer.toString(number), START_X - stringWidth / 2, START_Y + radius / 2);
    }
    public void fallDown(){
        if (this.START_Y == this.END_Y){
            this.END_Y += 20;
        }
    }
    public Rectangle calculateRectangle(){
       return new Rectangle(this.START_X,this.START_Y,this.SIZE,this.SIZE);
    }
}
