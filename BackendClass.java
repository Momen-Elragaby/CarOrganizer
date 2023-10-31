import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * This class implements our backend interface and works out the functionality
 * of the classes defined within those interfaces
 */
public class BackendClass<T extends BackendSingleCarInterface<Car>> implements BackendInterface<Car> {

    public IterableMultiKeyRBT<Car> tree;

    public BackendClass () {
        tree = new IterableMultiKeyRBT<Car>();
    }

    /**
     * This method compares one value of a car to another value of a car
     *
     * @return compareTo from the comparable interface
     */
    @Override
    public int compareTo(BackendSingleCarInterface toCompare) {

        return this.compareTo(toCompare);
    }

    /**
     * This method gets a file from the frontend and uses a scanner to
     * scan and add elements of the csv into car objects that hold each
     * cars data
     *
     * @param File the name of the csv
     */
    public void readFileData(File file) {
        try {
            int price;
            String brand;
            String model;   // Variables that we will store to use
            int year;
            float mileage;

            Scanner scnr = new Scanner(file);  // creates a scanner

            scnr.nextLine();
            scnr.useDelimiter(",");    // Sets scanner to the next line and breaks by ,

            while(scnr.hasNext()) {
                price = Integer.parseInt(scnr.next());  // first scan is int
                brand = scnr.next();  // next is brand
                model = scnr.next();  // next is model
                year = Integer.parseInt(scnr.next());   // next is year
                scnr.next();  // next is not needed
                mileage = Float.parseFloat(scnr.next());  // next is mileage

                Car car = new Car(brand, model, year, price, mileage);
                tree.insertSingleKey(car);   // creates and adds a car
                scnr.nextLine();
            }

            scnr.close();
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * This method gets a list of cars starting from the lowest mileage to the highest
     *
     * @return List of cars starting at the lowest mileage
     */
    public List<Car> minimumGasMileage() {
        List<Car> list = new ArrayList<Car>();  // List to be returned
        List<Car> toSort = new ArrayList<Car>();  // List of Cars in tree

        Iterator<Car> itr = tree.iterator();  // creates iterator

        while(itr.hasNext()) {
            toSort.add(itr.next());  // iterates through tree adding values
        }

        int i = toSort.size();
        Car minimum;

        while(i != 0) {
            minimum = toSort.get(0);
            for (int j = 0; j < i; j++) {
                if (minimum.carMileage() > toSort.get(j).carMileage()) {
                    minimum = toSort.get(j);
                }
            }
            list.add(minimum);
            toSort.remove(minimum); // Adds lowest to list, decrements copied array
            i--;
        }

        return list;
    }

    /**
     * This method gets a list of cars starting at a given threshold and going
     * to the highest mileage
     *
     * @param threshold the mileage threshold for the list
     * @return List of cars at or above the threshold
     */
    public List<Car> mileageAboveValue (float threshold) {
        List<Car> list = new ArrayList<Car>();
        List<Car> toSort = new ArrayList<Car>();

        Iterator<Car> itr = tree.iterator();

        while(itr.hasNext()) {
            toSort.add(itr.next());
        }

        int size = toSort.size();

        for (int i = 0; i < size; i++) {
            if (toSort.get(i).carMileage() < threshold) {
                toSort.remove(i);
                i--;
                size--;
            }
        }

        while (size != 0) {
            Car minimum = toSort.get(0);
            for (int k = 0; k < size; k++) {
                if (minimum.carMileage() > toSort.get(k).carMileage()) {
                    minimum = toSort.get(k);
                }
            }
            list.add(minimum);
            toSort.remove(minimum);
            size--;
        }

        return list;
    }
}
