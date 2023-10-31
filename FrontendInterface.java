import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is an interface for the frontend of the Car Organizer app
 */
public interface FrontendInterface<T> {

    /*
    Constructor:
    public FrontEndInterface(BackEnd backEndReference, Scanner userInput) {}
     */

    /**
     * This method start the command loop by presenting the main menu and any relevant submenus
     * based on the user input.
     */
    void startCommandLoop();

    /**
     * Method to display the main menu options, e.g:
     * a- Load Data File -> (specify data file)
     * b- List of Vehicles with the lowest mileage
     * c- List vehicles at or above a threshold -> (specify threshold)
     * d- Exit
     */
    void displayMainMenu();

    /**
     * This method loads data of a file specified by the user.
     *
     * @param path to the file to upload using backend
     */
    void loadDataFile(String path) throws FileNotFoundException;
    // Calls a method in the backend that extracts data from the specified file

    /**
     * This method lists vehicles with the lowest mileage
     */
    void displayVehiclesWithLowestMileage();
    // Calls the method in the backend that lists the vehicles

    /**
     * This method lists vehicles at or above a mileage threshold.
     *
     * e.g, if the user specifies 115,000 as the threshold; this method displays a list of all
     * the cars that have a mileage of 115,000 miles or more.
     *
     * @param threshold the minimum mileage of a car
     */
    void displayCarsAtAndAboveMileageThreshold(float threshold);
    // Call a method from the backend that returns data

    /**
     * This method simply exits the app
     */
    void exitApp();
    // Terminates the command loop


}
