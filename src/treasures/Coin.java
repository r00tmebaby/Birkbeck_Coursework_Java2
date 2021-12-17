package treasures;

import core.HasName;

import java.util.Objects;

/**
 * A Coin is a SimpleTreasure with a name for the specific coin.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class Coin extends SimpleTreasure implements HasName {

    /** name private class variable keeps the name of the coin */
    private final String name;

    /**
     * Constructs a new Coin with specified value and name.
     *
     * @param value
     *            The value of the Coin.
     * @param name
     *            The name of the Coin. Must not be null.
     */
    public Coin(long value, String name) {
        super(value);
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
    public String toString() {
        return String.format("Coin(%s) named %s", this.getValue(), this.name);
    }
}
