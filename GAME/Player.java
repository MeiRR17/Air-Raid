import java.awt.*;

public class Player {
        /*In this class we're going to make player1's plane*/
        private static final int PLANE_HEIGHT = 500;
        private static final int PLANE_WIDTH = 500;
    /*ADDED PLANE'S HEIGHT AND WIDTH*/
        private int positionX; //To get his place
        private int positionY;

        public Player (int x, int y) {
            this.positionX =x;
            this.positionY=y;
        }
        public static void paint(Graphics graphics){
            graphics.fillRect(30,30,60,60);
            graphics.setColor(Color.RED);

        }
}
