import java.awt.*;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    // 0 = small cactus, 1 = large cactus, 2 = bird
    private int type;
    private int animationFrame;
    private int animationCounter;

    public Obstacle(int x, int y, int type) {

    }

    public void update(int speed) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics2D g2d) {

    }
}
