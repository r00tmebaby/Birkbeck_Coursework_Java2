package treasures;

/**
 * A SimpleTreasure is a Treasure with a fixed value.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class SimpleTreasure implements Treasure {

    /** The  value private class variable holds the simple treasure value */
    private final long value;

    /**
     * Constructs a new SimpleTreasure with the specified value.
     *
     * @param value
     *            The value to assign to the SimpleTreasure.
     */

    public SimpleTreasure(long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return String.format("SimpleTreasure(%d)", this.value);
    }
}
