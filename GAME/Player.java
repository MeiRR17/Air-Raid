import java.awt.*;

public class Player extends Thread {
        /*In this class we're going to make player's plane*/
        private int START_X;
        private int START_Y;

    public Player (int START_X, int START_Y) {
        /*ADDED PLANE'S HEIGHT AND WIDTH*/
        this.START_X = START_X;
        this.START_Y = START_Y;
        //To get his place
    }
        public static void paint(Graphics graphics){
            graphics.setColor(Color.RED);
            graphics.fillRect(30,30,60,60);

        }
}
