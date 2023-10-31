import java.util.List;
import java.io.File;

/**
 * This interface is the backend interface that gets data from a file,
 * gets a list of cars with the lowest mileage, and gets a list of cars with
 * mileage at or above a specific threshold
 */
public interface BackendInterface<T> extends Comparable<BackendSingleCarInterface> {

    /**
     * Reads the data from a csv file with cars information
     */
    public void readFileData(File file);

    /**
     * Gets the sorted list of cars with the minimum mileage
     *
     * @return a list of cars sorted from lowest mileage to highest
     */
    public List<T> minimumGasMileage();

    /**
     * Gets a list of cars at or above a specific threshold
     *
     * @param threshold the value we use as the minimum
     * @return A list of cars at or above the threshold
     */
    public List<T> mileageAboveValue(float threshold);

}
