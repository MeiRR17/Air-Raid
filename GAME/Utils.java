import java.awt.*;

public class Utils {

    public static boolean checkCollision (Rectangle rect1, Rectangle rect2) {
        boolean collision = false;
        if (rect1.intersects(rect2)) {
            collision = true;
        }
        return collision;
    }

}
