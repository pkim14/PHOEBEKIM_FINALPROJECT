import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Dinosaur dino;
    private ArrayList<Obstacle> obstacles;
    private Random random;
    private int score;
    private boolean isGameOver;
    private int groundY;
    private int speed;
    private int obstacleInterval;
    private int ticksSinceLastObstacle;
    private int backgroundCycle;
    private int scoreTickCounter;
    private int highScore;
    private Font pixelFont;
    private Rectangle restartButtonRect = new Rectangle(360, 100, 50, 50);
    private boolean restartButtonHover = false;

    public GamePanel() {
        setBackground(Color.WHITE);
        setFocusable(true);

        groundY = 220;
        dino = new Dinosaur(50, groundY);
        obstacles = new ArrayList<>();
        random = new Random();
        score = 0;
        speed = 5;
        obstacleInterval = 50;
        ticksSinceLastObstacle = 0;
        isGameOver = false;
        backgroundCycle = 0;
        highScore = HighScoreTracker.loadHighScore();



        timer = new Timer(20, this);
        timer.start();

        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/PressStart2P-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        }
        catch (IOException | FontFormatException e) {
            pixelFont = new Font("Monospaced", Font.BOLD, 28);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isGameOver && restartButtonRect.contains(e.getPoint())) {
                    restartGame();
                }
            }
        });
        }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
            if (isGameOver) {
                restartGame();
            } else if (!dino.isJumping()) {
                dino.jump();
            }
        }
        else if (key == KeyEvent.VK_DOWN) {
            dino.duck();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            dino.stand();
        }
    }

    private void restartGame() {
        if (score > highScore) {
            highScore = score;
            HighScoreTracker.saveHighScore(highScore);
        }
        dino = new Dinosaur(50, groundY);
        obstacles.clear();
        score = 0;
        speed = 5;
        obstacleInterval = 60;
        ticksSinceLastObstacle = 0;
        isGameOver = false;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background color
        setBackground(Color.WHITE);

        Graphics2D g2d = (Graphics2D) g;

        // ground
        g2d.setColor(Color.GRAY);
        g2d.drawLine(0, groundY + 30, getWidth(), groundY + 30);

        // score
//        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setFont(pixelFont);
        g2d.drawString("Score: " + score, getWidth() - 200, 30);
        g2d.drawString("High Score: " + highScore, getWidth() - 500, 30);

        // draw dinosaur
        dino.draw(g2d);

        // draw obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2d);
        }

        // game over message
        if (isGameOver) {
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(Color.DARK_GRAY);
            String gameOverText = "Game Over";
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(gameOverText)) / 2;
            int y = getHeight() / 2;
            g2d.drawString(gameOverText, x, getHeight() / 2);

            int buttonY = y + 40;
            restartButtonRect.y = buttonY;

//            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRoundRect(restartButtonRect.x, restartButtonRect.y, restartButtonRect.width, restartButtonRect.height, 12, 12);

            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.WHITE);

            int arcStartAngle = 45;
            int arcExtent = 270;
            int arcEndAngle = arcStartAngle + arcExtent;

            int cx = restartButtonRect.x + restartButtonRect.width / 2;
            int cy = restartButtonRect.y + restartButtonRect.height / 2;
            int r = 15;

            g2d.drawArc(cx - r, cy - r, 2 * r, 2 * r, arcStartAngle, arcExtent);

            double theta = Math.toRadians(arcEndAngle);

            int tipX = (int) (cx + r * Math.cos(theta));
            int tipY = (int) (cy - r * Math.sin(theta));

            double tangentAngle = theta + Math.PI / 2;

            int arrowLen = 10;
            int arrowWidth = 6;


            int baseX = (int) (tipX - arrowLen * Math.cos(theta));
            int baseY = (int) (tipY + arrowLen * Math.sin(theta));

            int side1X = (int) (baseX + (arrowWidth / 2.0) * Math.cos(tangentAngle));
            int side1Y = (int) (baseY + (arrowWidth / 2.0) * Math.sin(tangentAngle));
            int side2X = (int) (baseX - (arrowWidth / 2.0) * Math.cos(tangentAngle));
            int side2Y = (int) (baseY - (arrowWidth / 2.0) * Math.sin(tangentAngle));


            g2d.fillPolygon(new int[]{tipX, side1X, side2X}, new int[]{tipY, side1Y, side2Y}, 3);
        }
    }

    private void gameOver() {
        isGameOver = true;
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            // update score
            scoreTickCounter++;

            if (scoreTickCounter >= 5) {
                score++;
                scoreTickCounter = 0;
            }


            // changing speed depending on score
            if (score > 200 && (score - 200) % 50 == 0) {
                speed++;
            }

            backgroundCycle++;
            if (backgroundCycle >= 2000) {
                backgroundCycle = 0;
            }

            dino.update();

            // create new obstacles
            ticksSinceLastObstacle++;
            if (ticksSinceLastObstacle >= obstacleInterval) {
                // 0 = small cactus, 1 = large cactus, 2 = bird
                int obstacleType = random.nextInt(3);
                int obstacleY = groundY;

                if (obstacleType == 2) {
                    int[] heights = {groundY - 30, groundY, groundY - 100};
//                    int[] heights = {groundY - 10, groundY - 30, groundY};
                    obstacleY = heights[random.nextInt(heights.length)];
                }

//                if (obstacleType == 2) {
//                    obstacleY = groundY; // TESTING
//                }

                obstacles.add(new Obstacle(getWidth(), obstacleY, obstacleType));
                ticksSinceLastObstacle = 0;
                obstacleInterval = 30 + random.nextInt(50);
            }

            // update obstacles
            for (int i = obstacles.size() - 1; i >= 0; i--) {
                Obstacle obstacle = obstacles.get(i);
                obstacle.update(speed);

                // remove obstacles that are off-screen
                if (obstacle.getX() < -50) {
                    obstacles.remove(i);
                }
            }

            // check for collision between dino and any obstacles
            for (Obstacle obstacle : obstacles) {
                if (dino.collidesWith(obstacle)) {
                    if (obstacle.getType() == 2) {
                            System.out.println("Dino collided with a bird");
                    } else {
                        System.out.println("Dino collided with a cactus");
                        }
                    gameOver();
                    break; // Only break if a collision happens
                }
            }

                dino.update();
                repaint();
            }
        }

}


