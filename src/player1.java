import javax.swing.*;
import java.awt.*;
    public class player1 {
        private int x;
        private int y;
        public static final int SIZE =50 ;
        private ImageIcon photo;


        public player1 (int x, int y,ImageIcon photo) {
            this.x = x;
            this.y = y;
            this.photo = photo;

        }
        public void paint(Graphics graphics){
            ImageIcon imageIcon = new ImageIcon(this.photo.getImage());
            imageIcon.paintIcon(null, graphics, this.x, this.y);
            imageIcon.getIconWidth();
        }
}
