package treasures;

import core.HasName;

import java.util.Objects;

/**
 * A MysteriousNote is a Treasure that has zero value and is left by someone
 * with a name.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class MysteriousNote implements Treasure, HasName {


    /** The name of the author of the mysterious note */
    private final String name;

    /**
     * Constructs a MysteriousNote written by an author with the specified
     * name.
     *
     * @param name
     *            The name of the author of the mysterious note.
     *            Must not be null.
     */
    public MysteriousNote(String name) {
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Illegal null argument: name");
        }
        this.name = name;
    }

    @Override
    public long getValue() {

        return 0;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public String toString() {

        return String.format("%s was here!", this.name);

    }
}
