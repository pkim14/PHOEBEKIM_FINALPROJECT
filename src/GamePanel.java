import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

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
    //    private boolean isNight;
    private int backgroundCycle;

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
//        isNight = false;
        backgroundCycle = 0;

        timer = new Timer(20, this);
        timer.start();
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
    }

    private void restartGame() {
        dino = new Dinosaur(50, groundY);
        obstacles.clear();
        score =0;
        speed =5;
        obstacleInterval =50;
        ticksSinceLastObstacle =0;
        isGameOver =false;
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


        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


