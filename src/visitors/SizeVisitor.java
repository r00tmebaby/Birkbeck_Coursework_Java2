package visitors;
import treasures.Treasure;

/**
 * Counts how many times the visit method has been called. Useful for
 * determining the number of entries in a container data structure.
 *
 */
public class SizeVisitor implements Visitor {

    /** Total visitors counter. Starts from 0*/
    private int totalVisitors = 0;

    @Override
    public Treasure visit(Treasure t) {

         this.totalVisitors ++;
         return t;

    }

    /**
     * Returns the size of the visited data structure according to the
     * number of times the visit method has been called.
     *
     * @return the size of the visited data structure according to the
     *  number of times the visit method has been called.
     */
    public int getSize() {
        return this.totalVisitors;
    }
}
