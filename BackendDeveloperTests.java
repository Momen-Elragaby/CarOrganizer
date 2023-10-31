import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.util.List;

/**
 * This class tests the Backend work for the group project.
 * It includes 5 tests methods using Junit, which should test
 * for potential bugs while implementing the project
 *
 */
public class BackendDeveloperTests {

    /**
     * This method tests the Single Car interface which
     * should hold the information for each car object that
     * has been imported by the csv
     */
    @Test
    public void testCarObjectsData() {
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
    public void testReadDataFile() {
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
     * This method tests that the backend can properly get a
     * list of cars with the minimum mileage
     */
    @Test
    public void testMinimumGasMileage() {
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

        // Test whether the list returned is in correct order
        Assertions.assertEquals("39590.0, 190552.0, 274117.0", mileageString);
    }

    /**
     * This tests the accuracy of the mileage above a given value method
     * which should return a list of mileages greater than or equal to a value
     */
    @Test
    public void testMileageAboveValue() {
        BackendClass carObjects = new BackendClass();
        String filePath = "/home/melragaby/p1/carstest.csv"; // Filepath from the frontend
        File file = new File(filePath);
        carObjects.readFileData(file);

        List<Car> returnedList = carObjects.mileageAboveValue(100000.0f);
        String mileageString = "";
        for (int i = 0; i < returnedList.size(); i++) {
            mileageString = mileageString + returnedList.get(i).carMileage();
            if (i != returnedList.size()-1) {
                mileageString = mileageString + ", ";
            }
        }

        // Test mileage above a certain value
        Assertions.assertEquals("190552.0, 274117.0", mileageString);
    }

    /**
     * This method tests that the values are all still returned when
     * there is a duplicate value.
     */
    @Test
    public void testMileageAboveMultipleValues() {
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

    /**
     * This method tests that a file can be created and passed into the backend
     * so that the csv file can be read
     */
        /*@Test
        public void testIntegrationReadFile() {
                BackendClass backend = new BackendClass();
		IterableMultiKeyRBT<Car> tree = backend.tree;
		Scanner scnr = new Scanner();

		Frontend frontend = new Frontend(backend, tree, scnr);

                Assertions.assertEquals("toyota", tree.root.carBrand()); // Not sure how to test with no return type
        }*/

    /**
     * This method tests that the frontend can display cars in order
     * of minimum mileage to highest
     */
        /*@Test
        public void testIntegrationLowestMileage() {
                BackendClass back = new BackendClass();
                String filePath = "/home/podevels/week06/p1/carstest.csv";
                File file = new File(filePath);
                back.readFileData(filePath);

                FrontendInterface front = new FrontendInterface(back);

                Assertions.assertEquals("39590.0, 190552.0, 274117.0", front.displayVehiclesWithLowestMileage());
	}*/
}
