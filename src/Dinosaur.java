import java.awt.*;

public class Dinosaur {
    private int x;
    private int y;
    private int width;
    private int height;
    private int jumpVelocity;
    private int initialY;
    private boolean isJumping;
    private int runAnimationFrame;
    private int animationCounter;

    public Dinosaur(int x, int y) {
        this.x = x;
        this.initialY = y;
        this.y = y;
        this.width = 40;
        this.height = 60;
        this.jumpVelocity = 0;
        this.isJumping = false;
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
                jumpVelocity = 0;
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
        if (!isJumping) {
            System.out.println("dino jumping");
            isJumping = true;
            // negative value means it is not going up
            jumpVelocity = -15;
        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean collidesWith(Obstacle obstacle) {

        // simple rectangle collision
        return x < obstacle.getX() + obstacle.getWidth() - 10 &&
                x + width - 10 > obstacle.getX() &&
                y  < obstacle.getY() + obstacle.getHeight() - 10 &&
                y + height - 10 > obstacle.getY();
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);

        // TESTING
        g2d.setColor(Color.RED);
        g2d.drawRect(x, y, width, height);

        // draw body
        g2d.fillRect(x, y - height + 30, width - 10, height - 10);

        // draw head
        g2d.fillRect(x + width - 20, y - height + 10, 20, 30);

        // draw eye
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + width, y - height + 20, 5, 5);

        // draw legs while running
        g2d.setColor(Color.LIGHT_GRAY);
        if (isJumping) {
            g2d.fillRect(x,y - 5, 20, 5);
            g2d.fillRect(x + 15, y - 10, 20, 5);
        }
        else {
            if (runAnimationFrame == 0) {
                g2d.fillRect(x, y, 10, 30 );
                g2d.fillRect(x + 20, y + 15, 10, 15);
            }
            else {
                g2d.fillRect(x + 20, y, 10, 30);
                g2d.fillRect(x, y + 15, 10, 15);
            }
        }
    }
}
