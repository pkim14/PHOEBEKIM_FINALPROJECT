import java.util.ArrayList;
import java.util.Random;

public class pet {
    // current state of the pet
    // enum: defines a collection of constants
    private enum PetState {
        IDLE, TALKING, BEING_PETTED, EATING,
    }
    private PetState currentState = PetState.IDLE;

    private ArrayList<String> responses = new ArrayList<>();
    private Random random = new Random();

    public pet() {
        loadResponses();
    }

    private void loadResponses() {
        responses.add("Hello, nice to meet you!");
        responses.add("That's interesting. Tell me more!");
        responses.add("I'm just a simple talking cat");
        responses.add("I'm feeling playful today!");
        responses.add("I'm hungry, do you have any treats?");
        responses.add("I didn't quite get that, but I like you anyway!");
    }

    public PetState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(PetState state) {
        this.currentState = state;
    }

    public String getRandomResponse() {
        return responses.get(random.nextInt(responses.size()));
    }

    public void addResponse(String response) {
        if (response != null && !response.trim().isEmpty()) {
            responses.add(response);
        }
    }
}
