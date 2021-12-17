package visitors;
import treasures.Treasure;

/**
 * A Visitor for data structures that store Treasure objects. It can receive
 * a Treasure object and return a replacement (or the same) Treasure object.
 * It can also be queried whether it still wants more visits. 
 *
 */
public interface Visitor {

    /**
     * Performs a visit on a Treasure object, possibly calling its methods.
     * Returns the replacement Treasure for t (or t itself) to be stored in
     *  the visited data structure
     *
     * @param t
     *            The Treasure to visit. Must not be null.
     * @return the replacement Treasure for t (or t itself) to be stored in
     *  the visited data structure
     */
    Treasure visit(Treasure t);

    /**
     * Returns whether this Visitor wants to visit more Chambers.
     * The default implementation always returns true.
     *
     * @return whether this Visitor wants to visit more Chambers
     */
    default boolean wantsMoreVisits() {

        return true;
    }
}
