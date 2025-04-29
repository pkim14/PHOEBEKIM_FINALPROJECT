import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PetView extends JFrame {
    private JPanel mainPanel;
    private JLabel petImageLabel;
    private JTextField inputField;
    private JButton talkButton;
    private JButton petButton;
    private JButton feedButton;

    private ImageIcon idleImage;
    private ImageIcon talkingImage;
    private ImageIcon pettingImage;
    private ImageIcon eatingImage;

    public PetView() {
        super("Talking Tom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);

        initComponents();

        setLocationTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());

        loadImages();

        // pet image display
        petImageLabel = new JLabel(idleImage);
        petImageLabel.setHorizontalAlignment(JLabel.CENTER);

        // bottom panel for interactions
        JPanel controlPanel = new JPanel(new BorderLayout());

        // text input field
        inputField = new JTextField(20);

        // buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // talk button
        talkButton = new JButton("Talk");

        // pet button
        petButton = new JButton("Pet");

        // feed button
        feedButton = new JButton("Feed");

        buttonPanel.add(talkButton);
        buttonPanel.add(petButton);
        buttonPanel.add(feedButton);

        controlPanel.add(inputField, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(petImageLabel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(petImageLabel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void loadImages() {
        // placeholders
        idleImage = createColorIcon(Color.BLUE, 200, 200);
        talkingImage = createColorIcon(Color.RED, 200, 200);
        eatingImage = createColorIcon(Color.YELLOW, 200, 200);
        pettingImage = createColorIcon(Color.GREEN, 200, 200);
    }

    private ImageIcon createColorIcon(Color color, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return new ImageIcon(image);
    }



    private void setLocationTo() {}
}
