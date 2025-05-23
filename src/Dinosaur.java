import java.awt.*;

public class Dinosaur {
    private int x;
    private int y;
    private int width;
    private int height;
    private int jumpVelocity;
    private int initialY;
    private boolean isJumping;
    private boolean isDucking;
    private int runAnimationFrame;
    private int animationCounter;

    private static final int normalHeight = 60;
    private static final int duckingHeight = 30;
    private static final int normalWidth = 40;
    private static final int duckingWidth = 60;


    public Dinosaur(int x, int y) {
        this.x = x;
        this.initialY = y;
        this.y = y;
        this.width = normalWidth;
        this.height = normalHeight;
        this.jumpVelocity = 0;
        this.isJumping = false;
        this.isDucking = false;
        this.runAnimationFrame = 0;
        this.animationCounter = 0;
    }

    public void update() {

        // how jumping works?
        if (isJumping) {
            System.out.println("Dino y = " + y + " jumpVelocity = " + jumpVelocity);
            y += jumpVelocity;
            jumpVelocity += 1; // gravity

            if (y >= initialY) {
                y = initialY;
                isJumping = false;
                jumpVelocity = -1000; // MAY NEED TO BE CHANGED
            }
        }

        // update running animation
        animationCounter++;

        if (animationCounter >= 5) {
            runAnimationFrame = (runAnimationFrame + 1) % 2;
            animationCounter = 0;
        }
    }

    public void jump() {

        // can't jump while ducking
        if (!isJumping && !isDucking) {
            isJumping = true;
            // negative value means it is not going up
            jumpVelocity = -20;
        }
    }

    public void duck() {
        if (!isJumping && !isDucking) {
            isDucking = true;
            height = duckingHeight;
            width = duckingWidth;
            y = initialY + (normalHeight - duckingHeight);
        }
    }

    public void stand() {
        if (isDucking) {
            isDucking = false;
            y = initialY;
            width = normalWidth;
            height = normalHeight;
        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isDucking() {
        return isDucking;
    }

    public boolean collidesWith(Obstacle obstacle) {

        int collisionYOffset = 40;

        // simple rectangle collision
        return x < obstacle.getX() + obstacle.getWidth() - 10 &&
                x + width - 10 > obstacle.getX() &&
                (y - collisionYOffset) < obstacle.getY() + obstacle.getHeight() - 10 &&
                (y - collisionYOffset) + height - 10 > obstacle.getY();
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);

        // TESTING
//        g2d.setColor(Color.RED);
        int collisionYOffset = 40; // using the same value as in your collision
//        g2d.drawRect(x, y - collisionYOffset, width, height);

        g2d.setColor(Color.LIGHT_GRAY);
        if (isDucking) {
            g2d.fillRect(x, y - height + 20, width - 19, height - 5); // body
            g2d.fillRect(x + width - 25, y - height + 5, 25, 20); // head
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x + width - 10, y - height + 12, 5, 5); // eye
        } else { // normal pose
            g2d.fillRect(x, y - height + 30, width - 10, height - 10);
            g2d.fillRect(x + width - 20, y - height + 10, 20, 30);
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x + width, y - height + 20, 5, 5);

            g2d.setColor(Color.LIGHT_GRAY);
            if (isJumping) {
                g2d.fillRect(x, y - 5, 20, 5);
                g2d.fillRect(x + 15, y - 10, 20, 5);
            } else {
                if (runAnimationFrame == 0) {
                    g2d.fillRect(x, y, 10, 30);
                    g2d.fillRect(x + 20, y + 15, 10, 15);
                } else {
                    g2d.fillRect(x + 20, y, 10, 30);
                    g2d.fillRect(x, y + 15, 10, 15);
                }
            }
        }
    }
}













