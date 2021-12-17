package treasures;

import core.HasName;

import java.util.Objects;

/**
 * A FancyTrove is a Trove that has a name. Much of the value of a FancyTrove
 * comes from its name (it is presumably a famous treasure box). Thus, the
 * value of a FancyTrove is the sum of the values of the component Treasures
 * plus a specific factor (currently 100) multiplied by the number of
 * characters of the FancyTrove's name.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class FancyTrove extends Trove implements HasName {

    /** Keeps the name of the fancy trove */
    private final String name;

    /** Internal constant used for determining the value of a FancyTrove. */
    private static final long VALUE_FACTOR = 100L; 

    /**
     * Constructs a new Trove with the Treasure objects specified by
     * contents.
     *
     * @param contents
     *            The Treasures to store in this FancyTrove. Must not be null
     *            nor contain null. The caller of the constructor is allowed
     *            to make changes to the parameter array after the constructor
     *            call, and this FancyTrove object (and its internal
     *            representation) will not be affected by this.
     * @param name
     *            The name of the FancyTrove. Must not be null.
     */
    public FancyTrove(Treasure[] contents, String name) {

        super(contents);
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Illegal null argument: name");
        }
        this.name = name;

    }

    @Override
    public String getName() {

        return this.name;

    }

    @Override
    public long getValue() {

        long fancyTroveValue = super.getValue();
        fancyTroveValue += Math.multiplyExact(this.name.length(), VALUE_FACTOR);

        return fancyTroveValue;

    }

    /**
     * Overriding the toString method to display simple treasure value
     * @return String
     */
    @Override
    public String toString() {

        StringBuilder treasures = new StringBuilder();

        for (Treasure t : contents) {
            treasures.append(t.toString());
        }

        return String.format("FancyTrove(%d) of [%s] named %s", this.getValue(), treasures, this.getName());
    }
}
