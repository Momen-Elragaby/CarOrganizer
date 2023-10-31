import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class FrontendCarOrganizer<T> implements FrontendInterface<T> {

    private Scanner userInput;

    public FrontendCarOrganizer(Scanner userInput) {
        this.userInput = userInput;
    }

    @Override
    public void startCommandLoop() {
        System.out.println("Placeholder: Starting command loop");
    }

    @Override
    public void displayMainMenu() {
        System.out.println("Placeholder: Displaying main menu");
    }

    @Override
    public void loadDataFile(String path) throws FileNotFoundException {
        System.out.println("Placeholder: Loading data from file: " + path);
    }

    @Override
    public void displayVehiclesWithLowestMileage() {
        System.out.println("Placeholder: Displaying vehicles with the lowest mileage");
    }

    @Override
    public void displayCarsAtAndAboveMileageThreshold(float threshold) {
        System.out.println("Placeholder: Displaying cars at or above mileage threshold: " + threshold);
    }

    @Override
    public void exitApp() {
        System.out.println("Placeholder: Exiting the application");
    }
}
