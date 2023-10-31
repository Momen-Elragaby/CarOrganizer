import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Frontend<T> implements FrontendInterface<T> {

    private BackendClass<Car> Backend;
    private Scanner userInput;

    public Frontend(BackendClass<Car> backend, Scanner userInput) {
        this.Backend = backend;
        this.userInput = userInput;
    }

    public static void main(String[] args) {
        BackendClass<Car> backend = new BackendClass<>();

        Scanner scanner = new Scanner(System.in);


        // Initialize Frontend with the backend and scanner
        Frontend<Car> frontend = new Frontend<>(backend, scanner);

        // Start command loop
        frontend.startCommandLoop();

        // Close scanner
        scanner.close();
    }




    @Override
    public void startCommandLoop() {
        boolean exitRequested = false;
        while (!exitRequested) {
            displayMainMenu();
            String userChoice = userInput.nextLine();
            switch (userChoice) {
                case "a":
                    System.out.print("Enter the path to the data file: ");
                    String filePath = userInput.nextLine();
                    try {
                        loadDataFile(filePath);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    break;
                case "b":
                    displayVehiclesWithLowestMileage();
                    break;
                case "c":
                    System.out.print("Enter the mileage threshold: ");
                    float threshold = Float.parseFloat(userInput.nextLine());
                    displayCarsAtAndAboveMileageThreshold(threshold);
                    break;
                case "d":
                    exitApp();
                    exitRequested = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (a, b, c, or d).");
                    break;
            }
        }
    }

    @Override
    public void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("a- Load Data File");
        System.out.println("b- List of Vehicles with the lowest mileage");
        System.out.println("c- List vehicles at or above a threshold");
        System.out.println("d- Exit");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void loadDataFile(String path) throws FileNotFoundException {

        File file = new File(path);
        if (file.exists()) {
            Backend.readFileData(file);
            System.out.println("Data file loaded successfully");
        } else {
            throw new FileNotFoundException("File not found: " + path);
        }
    }

    @Override
    public void displayVehiclesWithLowestMileage() {
        List<Car> vehicles = Backend.minimumGasMileage();
        System.out.println("List of Vehicles with the lowest mileage");
        for (Car vehicle : vehicles) {
            // Use 'vehicle' instead of 'Car'
            String brand = vehicle.carBrand();
            String model = vehicle.carModel();
            int year = vehicle.carYear();
            int price = vehicle.carPrice();
            float mileage = vehicle.carMileage();

            // Display car details
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Year: " + year);
            System.out.println("Price: " + price);
            System.out.println("Mileage: " + mileage);
        }
    }

    @Override
    public void displayCarsAtAndAboveMileageThreshold(float threshold) {
        List<Car> vehicles = Backend.mileageAboveValue(threshold);
        System.out.println("Vehicles with mileage at or above " + threshold + ":");
        for (Car vehicle : vehicles) {
            // Use 'vehicle' instead of 'Car'
            String brand = vehicle.carBrand();
            String model = vehicle.carModel();
            int year = vehicle.carYear();
            float mileage = vehicle.carMileage();
            float price = vehicle.carPrice();

            // Display car details
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Year: " + year);
            System.out.println("Price: " + price);
            System.out.println("Mileage: " + mileage);
        }
    }

    @Override
    public void exitApp() {
        System.out.println("Exiting the app. Goodbye!");
    }
}
