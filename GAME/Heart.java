import java.awt.*;

public class Heart {
    private final int width = 50;
    private final int height = 50;
    private final int X;
    private final int Y = 10;
    private Image image;

    public Heart(int x) {
        X = x;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
