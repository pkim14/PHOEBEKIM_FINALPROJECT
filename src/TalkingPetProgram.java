import javax.swing.*;

/**
 * Main class that launches the Talking Pet application
 */
public class TalkingPetApp {
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing applications
        SwingUtilities.invokeLater(() -> {
            pet model = new pet();
            PetView view = new PetView();
            PetController controller = new PetController(model, view);

            // Display the application
            view.setVisible(true);
        });
    }
}
