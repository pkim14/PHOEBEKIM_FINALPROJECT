import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class DinoGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new GameFrame("Dino Game");
            frame.setVisible(true);
        });
    }
}

class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();

    }
}
class GamePanel extends JPanel implements ActionListener {

