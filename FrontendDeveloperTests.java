import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloperTests {



    @Test
    public void testLoadDataFile() {
        // Simulated user input: select Load Data File option, enter file path, exit application
        String simulatedUserInput = "a\n/home/melragaby/p1/carstest.csv\nd\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        // Create the backend and frontend instances
        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        // Run the frontend with the simulated input
        frontend.startCommandLoop();

        // Check the output
        String output = tester.checkOutput();
        assertTrue(output.contains("Data file loaded successfully"), "Expected output to contain 'Data file loaded successfully'");
    }

    @Test
    public void testDisplayVehiclesWithLowestMileage() {
        String simulatedUserInput = "a\n/home/melragaby/p1/carstest.csv\nb\nd\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        assertTrue(output.contains("Enter the path to the data file:"));
        assertTrue(output.contains("Data file loaded successfully"));
        assertTrue(output.contains("List of Vehicles with the lowest mileage"));
        assertTrue(output.contains("Brand: dodge"));
        assertTrue(output.contains("Model: mpv"));
        assertTrue(output.contains("Year: 2018"));
        assertTrue(output.contains("Price: 5350"));
        assertTrue(output.contains("Mileage: 39590.0"));
    }


    @Test
    public void testDisplayCarsAtAndAboveMileageThreshold() {
        String simulatedUserInput = "a\n/home/melragaby/p1/carstest.csv\nc\n150000\nd\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        assertTrue(output.contains("Enter the mileage threshold:"));
        // assertTrue(output.contains("Vehicles with mileage at or above 150000:"));
    }


    @Test
    public void testExitApp() {
        String simulatedUserInput = "d\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        assertTrue(output.contains("Exiting the app. Goodbye!"));
    }


    @Test
    public void testInvalidInput() {
        String simulatedUserInput = "invalid_option\nd\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        assertTrue(output.contains("Exiting the app. Goodbye!"));
    }


    @Test
    public void backendTest1() {
        String simulatedUserInput = "a\n/home/melragaby/p1/carstest.csv\nd\n";
        TextUITester tester = new TextUITester(simulatedUserInput);

        BackendClass<Car> backend = new BackendClass<>();
        Frontend<Car> frontend = new Frontend<>(backend, new Scanner(System.in));

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        assertTrue(output.contains("Data file loaded successfully"));
    }


    /**
     * This method tests that the values are all still returned when
     * there is a duplicate value.
     */
    @Test
    public void backendTest2() {
        Car car = new Car("toyota", "cruiser", 2008, 6300, 274117.0f);
        // creates a car and assigns necessary data

        Assertions.assertEquals(6300, car.carPrice());
        Assertions.assertEquals("toyota", car.carBrand());
        Assertions.assertEquals("cruiser", car.carModel());
        Assertions.assertEquals(2008, car.carYear());
        Assertions.assertEquals(274117.0, car.carMileage());
    }

    /**
     * This method tests that the backend can effectively
     * read a csv file of data and assign the values in the
     * csv to variables.
     */
    @Test
    public void integrationTest1() {
        BackendClass carObjects = new BackendClass();
        String filePath = "/home/melragaby/p1/carstest.csv"; // Filepath from the frontend
        File file = new File(filePath);

        carObjects.readFileData(file);

        List<Car> returnedList = carObjects.minimumGasMileage();
        String mileageString = "";
        for (int i = 0; i < returnedList.size(); i++) {
            mileageString = mileageString + returnedList.get(i).carMileage();
            if (i != returnedList.size()-1) {
                mileageString = mileageString + ", ";
            }
        }

        System.out.println(mileageString);

        Assertions.assertEquals("39590.0, 190552.0, 274117.0", mileageString); // Should make it return true if the data gets loaded
    }

    /**
     * This method tests that the values are all still returned when
     * there is a duplicate value.
     */
    @Test
    public void integrationTest2() {
        BackendClass carObject = new BackendClass();
        String filePath = "/home/melragaby/p1/carstest.csv"; // Filepath from the frontend
        File file = new File(filePath);
        carObject.readFileData(file);

        List<Car> returnedList = carObject.mileageAboveValue(100000.0f);
        String mileageString = "";
        for (int i = 0; i < returnedList.size(); i++) {
            mileageString = mileageString + returnedList.get(i).carMileage();
            if (i != returnedList.size()-1) {
                mileageString = mileageString + ", ";
            }
        }

        // Tests that all values get read
        Assertions.assertEquals("190552.0, 274117.0", mileageString);
    }



}

