import java.awt.*;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    // 0 = small cactus, 1 = large cactus, 2 = bird
    private final int type;
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
            this.height = 30;
        }
        // large cactus
        else if (type == 1) {
            this.width = 30;
            this.height = 80;
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

    public int getType() {
        return type;
    }


    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);

        // small cactus
        if (type == 0) {
            drawCactus(g2d, 1);
        }
        // large cactus
        else if (type == 1) {
            drawCactus(g2d, 2);
        }
        // bird
        else {
            drawBird(g2d);
        }

        // TESTING
//        if (type == 2) { // Only for birds
//            g2d.setColor(Color.BLUE);
//            g2d.drawRect(x, y, width, height);
    }

    private void drawCactus(Graphics2D g2d, int size) {
        g2d.setColor(new Color(83, 121, 78)); // color of chrome dino cacti

        int mainX = x + width / 2 - 6;
        int mainY = y - height + 30;
        int mainWidth = 12;
        int mainHeight = height;

//         main body
        g2d.fillRect(mainX, mainY, mainWidth, mainHeight);

        // branches
        if (size == 1) {
            g2d.fillRect(mainX - 8, mainY + mainHeight / 3, 6, 16);
            g2d.fillRect(mainX + mainWidth + 2, mainY + mainHeight / 2, 6, 16);
        }
        else {
            g2d.fillRect(mainX - 10, mainY + 20, 8, mainHeight - 20);
            g2d.fillRect(mainX + mainWidth + 2, mainY + 10, 8, mainHeight - 10);

            g2d.fillRect(mainX - 12, mainY + 30, 6, 14);
            g2d.fillRect(mainX + mainWidth + 6, mainY + 30, 6, 14);
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
        }
        else {
            // wings down
            g2d.fillRect(x, y + 10, 40, 10);
        }
    }
}
