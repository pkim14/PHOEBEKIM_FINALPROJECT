import java.io.*;

public class HighScoreTracker {
    private static final String fileName = "highScore.txt";
    private int highScore;

    public static int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highScore"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            }
        }
        catch (IOException | NumberFormatException e) {
        }
        return 0;
    }

    public static void saveHighScore(int highScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highScore"))) {
            writer.write(Integer.toString(highScore));
        }
        catch (IOException e) {
        }
    }
}
