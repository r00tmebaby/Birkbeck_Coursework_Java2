package treasures;

/**
 * Interface for treasures. Each treasure has a value.
 * 
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public interface Treasure {

    /**
     * Returns the value of the Treasure. Note that the value of a Treasure
     * can be positive, zero, or negative. 
     *
     * @return the value of the Treasure
     */
    long getValue();
}
