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
        this.x = x;
        this.y = y;
        this.type = type;
        this.animationFrame = 0;
        this.animationCounter = 0;

        // small cactus
        if (type == 0) {
            this.width = 20;
            this.height = 40;
        }
        // large cactus
        else  if (type == 1) {
            this.width = 30;
            this.height = 60;
        }
        // bird
        else {
            this.width = 50;
            this.height = 30;
        }
    }

    public void update(int speed) {
        x -= speed;

        // update the bird speed
        if (type == 2) {
            animationCounter++;
            if (animationCounter >= 10) {
                animationFrame = (animationFrame + 1) % 2;
                animationCounter = 0;
            }
        }
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
        g2d.setColor(Color.LIGHT_GRAY);

        // small cactus
        if (type == 0) {
            drawCactus(g2d, 1);
        }
        // large cactus
        else if (type == 1) {
            drawCactus(g2d,2);
        }
        // bird
        else {
            drawBird(g2d);
        }
    }

    private void drawCactus(Graphics2D g2d, int size) {

        // body
        g2d.fillRect(x + width / 3, y - height + 30, width / 3, height);

        // branches
        if (size == 1) {
            g2d.fillRect(x, y - height + 40, width, 10);
            g2d.fillRect(x, y - height + 50, width / 3, 20);
        }
        else {
            g2d.fillRect(x, y - height + 40, width, 10);
            g2d.fillRect(x, y - height + 50, width / 3, 30);
            g2d.fillRect(x + 2 * width / 3, y - height + 60, width / 3, 20);
        }
    }

    private void drawBird(Graphics2D g2d) {

        // body
        g2d.fillOval(x + 10, y, 30, 20);

        // head & beak
        g2d.fillOval(x + 35, y - 5, 15, 15);
        g2d.fillRect(x + 45, y, 10,5);

        // wings
        if (animationFrame == 0) {
            // wings up
            g2d.fillRect(x, y - 20, 40, 10);
//            g2d.fillPolygon(
        }
        else {
            // wings down
            g2d.fillRect(x, y + 10, 40, 10);
        }
    }
}
