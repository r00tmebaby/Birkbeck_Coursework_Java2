package visitors;
import core.HasName;
import treasures.MysteriousNote;
import treasures.Treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A TreasureSeeker is an adventurous person with a name who collects treasures
 * of positive value as long as a specified desired value has not been reached.
 * Leaves a mysterious note whenever they take a treasure in a visit. Implemented
 * as a Visitor to ensure independence from the concrete data structure(s) in
 * which the Treasures can be found.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class TreasureSeeker implements Visitor, HasName {


    /** The name of each visitor. Can not be null*/
    private final String name;


    /** The desired value of each visitor. Can not be null*/
    private long desiredValue;


    /** The treasure that have been visited */
    List<Treasure> visited;


    /**
     * Constructs a new TreasureSeeker object with specified name and
     * desiredValue.
     *
     * @param name
     *            The name of the TreasureSeeker. Must not be null.
     * @param desiredValue
     *            The value that this TreasureSeeker currently still desires.
     */
    public TreasureSeeker(String name, long desiredValue) {
        this.visited = new ArrayList<>();

        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Illegal null argument: name");
        }

        this.desiredValue = desiredValue;
        this.name = name;
    }

    @Override
    public Treasure visit(Treasure t) throws NullPointerException{
        if (Objects.isNull(t)){
            throw new NullPointerException("Treasure can not be null");
        }
        if(t.getValue() > 0){
                this.visited.add(t);
                this.desiredValue -= t.getValue();
                return new MysteriousNote(this.getName());
        }
        return t;
    }

    @Override
    public String getName() {

        return this.name;
    }

    /**
     * Returns the value that this TreasureSeeker currently desires.
     *
     * @return the value that this TreasureSeeker currently desires
     */
    public long getDesiredValue() {

        return this.desiredValue;

    }



    @Override
    public boolean wantsMoreVisits() {

        return this.desiredValue >= 0;

    }


    @Override
    public String toString() {

        StringBuilder treasures = new StringBuilder();

        for(int i=0; i < visited.size(); i++){
            if (i > 0){
                treasures.append(", ");
            }
            treasures.append(String.format("%s", visited.get(i)));
        }

        return String.format
                (
                    "Treasure seeker %s has [%s] and currently seeks a value of %d",
                     this.getName(), treasures.toString(), this.getDesiredValue()
                );
    }

}
