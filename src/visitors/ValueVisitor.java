package visitors;
import treasures.Treasure;

import java.util.Objects;

/**
 * Visitor keeping a running total of the values of all Treasure objects
 * passed to the visit method.
 *
 * @author Carsten Fuhs
 */
public class ValueVisitor implements Visitor {

    /** The total value of all treasures */
    private long totalValue = 0;

    /** No arguments constructor */
    public ValueVisitor(){

    }

    @Override
    public Treasure visit(Treasure t) {

        if (Objects.isNull(t)){
            throw new NullPointerException("Treasure can not be null");
        }

        this.totalValue += t.getValue();
        return t;
    }

    /**
     * Returns the total value of all Treasure objects that have been arguments
     * to the visit method.
     *
     * @return the total value of all Treasure objects that have been arguments
     *  to the visit method
     */
    public long getValue() {

        return this.totalValue;

    }
}
