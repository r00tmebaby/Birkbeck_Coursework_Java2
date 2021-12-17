package treasures;
import java.util.Objects;

/**
 * A Trove is a Treasure that is composed of zero or more Treasures.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class Trove implements Treasure {

    /** treasure array can not be or contain null */
    public final Treasure[] contents;

    /**
     * Constructs a new Trove with the Treasure objects specified by
     * contents.
     *
     * @param contents
     *            The Treasures to store in this Trove. Must not be null
     *            nor contain null. The caller of the constructor is allowed
     *            to make changes to the parameter array after the constructor
     *            call, and this Trove object (and its internal representation)
     *            will not be affected by this.
     */
    public Trove(Treasure[] contents) {
        if(Objects.isNull(contents)){
            throw new IllegalArgumentException("Illegal null argument: contents");
        }
        this.contents = contents.clone();
    }


    @Override
    public long getValue() {

        long sumOfValues = 0;

        for (Treasure treasure : this.contents) {
            if (Objects.isNull(treasure) || treasure.getValue() == 0){
                continue;
            }
            sumOfValues += treasure.getValue();
        }
        return  sumOfValues;
    }

    @Override
    public String toString() {

        StringBuilder treasures = new StringBuilder();

        for(int i=0; i < contents.length; i++){
            if (i > 0){
                treasures.append(", ");
            }
            treasures.append(String.format("%s", contents[i]));
        }
        return String.format("Trove(%d) of [%s]", this.getValue(), treasures.toString());
    }
}
