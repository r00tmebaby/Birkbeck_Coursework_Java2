package core;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import treasures.Treasure;
import visitors.Visitor;

/**
 * A Chamber has a Treasure as content and zero or more Chambers as "exits"
 * numbered from 0. It also has a label assigned on a running basis at
 * object construction. It provides functionality to visit the Treasures
 * in all Chambers reachable from this Chamber in a depth-first pre-order
 * fashion.
 *
 * Formally, a Chamber is a node in a directed graph with unlimited out-degree.
 * The graph may contain loops and, more generally, cyclic paths.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class Chamber {

    /** Class variable for the label to use for the next Chamber that is
     *  constructed. Incremented in each constructor call so that it is
     *  fresh for the first 2^16 constructor calls.
     */
    private static char nextLabel = 'A';

    /**
     * The label of the Chamber, currently a single character.
     */
    private final char label;

    /**
     * The Treasure that is the content of this Chamber. Must not be null.
     */
    private Treasure content;

    /**
     * The list of exits of this Chamber, i.e., the list of the Chambers one
     * can reach directly from this Chamber. Must not be null. Initially empty,
     * may be added to during the object lifetime.
     */
    private final List<Chamber> exits;

    /**
     * Constructs a new Chamber with a fresh label, a Treasure as initial content,
     * and initially zero exits.
     *
     * @param content
     *            The initial content to store in this Chamber.
     *            Must not be null.
     */
    public Chamber(Treasure content) {
        if (content == null) {
            throw new IllegalArgumentException("Illegal null argument: content");
        }
        this.content = content;
        this.exits = new ArrayList<>();
        this.label = nextLabel;
        nextLabel++; // set to next letter in alphabet
    }

    /**
     * Adds an exit to the end of the list of exits.
     * 
     * @param exit
     *            The new exit to add to the end of the list of exits.
     */
    public void addExit(Chamber exit) {
        if (exit == null) {
            throw new IllegalArgumentException("Illegal null argument: exit");
        }
        this.exits.add(exit);
    }

    /**
     * Returns the label of this Chamber.
     *
     * @return the label of this Chamber
     */
    public String getLabel() {
        return this.label + "";
    }

    /**
     * Returns an array with the labels of the exits of this Chamber,
     * in the order in which the exits are stored internally.
     *
     * @return an array with the labels of the exits of this Chamber,
     *  in the order in which the exits are stored internally
     */
    private String[] getExitLabels() {
        final int SIZE = this.exits.size();
        String[] result = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            result[i] = this.exits.get(i).label + "";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Chamber " + this.label + ": exits "
                + Arrays.toString(this.getExitLabels())
                + " with " + this.content;
    }

    /**
     * Lets a Visitor visit the Chamber network starting from this Chamber.
     * Takes the Visitor through the reachable Chambers in a pre-order
     * depth-first graph traversal. The Visitor gets to visit each of the
     * Chambers exactly once, as long as the Visitor wants to continue the
     * visit to the Chamber network. To ensure this, calls to
     * visitor.visit(Treasure) are made only if the call
     * visitor.wantsMoreVisits() returns true.
     *
     * @param visitor
     *            The visitor for the Chamber network, must not be null.
     * @throws IllegalArgumentException if visitor is null
     */
    public void visit(Visitor visitor) throws IllegalArgumentException{

        if(Objects.isNull(visitor)){
            throw new IllegalArgumentException("Illegal null argument: visitor");
        }

        this.checkVisits(visitor, new ArrayList<>());
    }


    /**
     * A new private method to do DFS recursively called from the public method /visit/
     *
     * @param visitor
     *            Keeps the Chamber visitor name, passed from the public method visit.
     *            It is already been checked for null pointer exception, so an additional error check is not required
     * @param visitedPlaces
     *            Keeps a list of all visited Chambers
     */
    private void checkVisits(Visitor visitor, List<Chamber> visitedPlaces) {

        if (visitedPlaces.contains(this) || !visitor.wantsMoreVisits())
            return;

        content = visitor.visit(content);
        visitedPlaces.add(this);

        for (Chamber chamber : exits) {
            chamber.checkVisits(visitor, visitedPlaces);
        }
    }
}
