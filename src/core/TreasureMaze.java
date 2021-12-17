package core;

import visitors.SizeVisitor;
import visitors.ValueVisitor;
import visitors.Visitor;

/**
 * A TreasureMaze is a network of connected Chambers, each of which contains
 * a Treasure object. It has a dedicated entrance Chamber. It provides
 * functionality to query the number of Chambers and the total Treasure value
 * reachable from the entrance Chamber. It provides functionality to visit the
 * Treasures in all reachable Chambers in a depth-first pre-order fashion.
 * 
 * Formally, a TreasureMaze is a directed graph with unlimited out-degree.
 * It may contain loops and, more generally, cyclic paths.
 *
 * @author Carsten Fuhs
 */
public class TreasureMaze {

    /**
     * The entrance of this TreasureMaze. Must not be null.
     * Must be an element of this.chambers.
     */
    private final Chamber entrance;

    /**
     * Some designated Chambers that will be used for printout.
     * Must not be null. Must include this.entrance. 
     */
    private Chamber[] chambers;

    /**
     * Constructs a new TreasureMaze.
     *
     * @param entranceIndex
     *            The array index in designatedChambers at which the entrance
     *            Chamber of the TreasureMaze can be found. Must be at least
     *            0 and at most designatedChambers.length - 1.
     * @param designatedChambers
     *            An array of Chambers in the TreasureMaze that will be used
     *            for toString(). Must not be null, must not contain null. 
     */
    public TreasureMaze(int entranceIndex, Chamber[] designatedChambers) {
        sanityCheck(entranceIndex, designatedChambers);
        this.entrance = designatedChambers[entranceIndex];
        this.chambers = new Chamber[designatedChambers.length];
        System.arraycopy(designatedChambers, 0, this.chambers, 0,
                designatedChambers.length);
    }

    /**
     * Auxiliary method for sanity checks of the constructor parameters.
     * 
     * @param entranceIndex
     *            The array index in designatedChambers at which the entrance
     *            Chamber of the TreasureMaze can be found. Must be at least
     *            0 and at most chambers.length - 1.
     * @param chambers
     *            An array of Chambers in the TreasureMaze that will be used
     *            for toString(). Must not be null, must not contain null.
     * @throws IllegalArgumentException if the parameters do not meet these
     *  conditions 
     */
    private static void sanityCheck(int entranceIndex, Chamber[] chambers) {
        if (chambers == null) {
            throw new IllegalArgumentException("Illegal null argument: chambers");
        }
        for (int i = 0; i < chambers.length; i++) {
            if (chambers[i] == null) {
                throw new IllegalArgumentException("Illegal null argument: chambers["
                        + i + "]");
            }
        }
        if (entranceIndex < 0 || entranceIndex >= chambers.length) {
            throw new IllegalArgumentException("Illegal entranceIndex: expected 0..chambers.length-1, found: "
                    + entranceIndex);
        }
    }

    /**
     * Lets a Visitor visit the TreasureMaze starting from the entrance.
     * Takes the Visitor through all Chambers in a pre-order depth-first
     * graph traversal. Every Chamber is visited exactly once, as long as
     * the Visitor wants to continue the visit.
     *  
     * @param v
     *            the visitor for the TreasureMaze, must not be null
     */
    public void visit(Visitor v) {
        this.entrance.visit(v);
    }

    /**
     * Returns the total value of all the Treasure objects reachable from
     * the entrance Chamber of this TreasureMaze.
     *
     * @return the total value of all the Treasure objects reachable from
     *  the entrance Chamber of this TreasureMaze.
     */
    public long getValue() {
        ValueVisitor v = new ValueVisitor();
        this.visit(v);
        return v.getValue();
    }

    /**
     * Returns the number of different Chambers reachable from the entrance
     * Chamber of this TreasureMaze.
     *
     * @return the number of different Chambers reachable from the entrance
     *  Chamber of this TreasureMaze.
     */
    public int getReachableSize() {
        SizeVisitor v = new SizeVisitor();
        this.visit(v);
        return v.getSize();
    }

    @Override
    public String toString() {
        // Use a StringBuilder for efficient incremental String construction.
        StringBuilder sb = new StringBuilder();
        sb.append("Maze with entrance in Chamber ");
        sb.append(this.entrance.getLabel());
        sb.append(", size ");
        sb.append(this.getReachableSize());
        sb.append(", and these designated chambers:\n");
        for (Chamber c : this.chambers) {
            sb.append(c);
            sb.append('\n');
        }
        return sb.toString();
    }
}
