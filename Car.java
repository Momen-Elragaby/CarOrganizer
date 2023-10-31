/**
 * This class makes a car object that stores the neccessary data types
 *
 * @author Lucas Podevels
 */
public class Car implements BackendSingleCarInterface<Car> {

    public String brand;
    public String model;
    public int year;
    public int price;
    public float mileage;

    /**
     * Constructor that builds a car with the necessary data
     */
    public Car (String brand, String model, int year, int price, float mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }

    /**
     * Gets the specific cars brand
     *
     * @return brand of the car
     */
    public String carBrand() {

        return this.brand;
    }

    /**
     * Gets the specific cars model
     *
     * @return model of the car
     */
    public String carModel() {

        return this.model;
    }

    /**
     * Gets the specific cars year
     *
     * @return year of the car
     */
    public int carYear() {

        return this.year;
    }

    /**
     * Gets the specific cars price
     *
     * @return price of the car
     */
    public int carPrice() {

        return this.price;
    }

    /**
     * Gets the specific cars mileage
     *
     * @return mileage of the car
     */
    public float carMileage() {

        return this.mileage;
    }

    /**
     * Compare to method from Comparable
     */
    @Override
    public int compareTo(Car car) {

        if (this.carMileage() == car.carMileage()) {
            return 0;
        }  else if (this.carMileage() < car.carMileage()) {
            return -1;
        }  else {
            return 1;
        }
    }
}
