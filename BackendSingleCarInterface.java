import java.util.List;
import java.io.File;

/**
 * This interface defines a single car and exposes its properties required.
 * These properties are brand, model, year, price and mileage
 */
public interface BackendSingleCarInterface<Car> extends Comparable<Car> {

    /**
     * Gets the brand of the given car
     *
     * @param car the object we are getting information on
     * @return the car brand
     */
    public String carBrand();

    /**
     * Gets the model of the given car
     *
     * @param car the object we are getting information on
     * @return the car model
     */
    public String carModel();

    /**
     * Gets the year of the given car
     *
     * @param car the object we are getting information on
     * @return the car year
     */
    public int carYear();

    /**
     * Gets the price of the given car
     *
     * @param car the object we are getting information on
     * @return the car price
     */
    public int carPrice();

    /**
     * Gets the mileage of the given car
     *
     * @param car the object we are getting information on
     * @return the car mileage
     */
    public float carMileage();

    /**
     * Compare to method from comparable interface
     *
     * @return comparison to car
     */
    public int compareTo(Car car);

}
