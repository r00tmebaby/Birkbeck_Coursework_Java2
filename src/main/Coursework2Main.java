package main;

import core.Chamber;
import core.TreasureMaze;
import treasures.Coin;
import treasures.FancyTrove;
import treasures.SimpleTreasure;
import treasures.Treasure;
import treasures.Trove;
import visitors.TreasureSeeker;

/**
 * "Driver class" for Coursework Assignment 2 of the SP2 module in 2020/21 at
 * Birkbeck, University of London.
 *
 * @author Carsten Fuhs
 */
public class Coursework2Main {

    /**
     * Constructs the example TreasureMaze used in the description of
     * Coursework Assignment 2.
     *
     * @return the TreasureMaze corresponding to the example in the description
     *  of Coursework Assignment 2
     */
    public static TreasureMaze mkMaze() {
        // first create all the Chambers with the Treasures in them ...
        Treasure[] t1 = { new SimpleTreasure(40), new Coin(50, "Doubloon") };
        Chamber[] chambers = {
            new Chamber(new Coin(80, "Karshapana")), // A
            new Chamber(new Trove(t1)), // B
            new Chamber(new SimpleTreasure(40)), // C
            new Chamber(new Coin(70, "Dinar")), // D
            new Chamber(new SimpleTreasure(0)), // E
            new Chamber(new SimpleTreasure(30)), // F
            new Chamber(new FancyTrove(new Treasure[0], "Big Whoop!")), // G
            new Chamber(new Coin(60, "Gold-pressed latinum")) // H
        };
        t1[1] = null; // this line should *not* affect the Trove in Chamber B!

        // ... now create the exits from one Chamber to the next ...
        chambers[0].addExit(chambers[1]); // A -> B
        chambers[1].addExit(chambers[1]); // B -> B
        chambers[1].addExit(chambers[2]); // B -> C
        chambers[1].addExit(chambers[7]); // B -> H
        chambers[2].addExit(chambers[1]); // C -> B
        chambers[2].addExit(chambers[3]); // C -> D
        chambers[3].addExit(chambers[4]); // D -> E
        chambers[3].addExit(chambers[6]); // D -> G
        chambers[4].addExit(chambers[5]); // E -> F
        chambers[6].addExit(chambers[7]); // G -> H
        chambers[7].addExit(chambers[0]); // H -> A

        // ... and the TreasureMaze is complete!
        return new TreasureMaze(0, chambers);
    }

    /**
     * Prints information about a TreasureMaze and a TreasureSeeker.
     *
     * @param maze
     *            the TreasureMaze to print information about, must not be null
     * @param seeker
     *            the TreasureSeeker to print
     */
    private static void printMazeAndSeeker(TreasureMaze maze, TreasureSeeker seeker) {
        System.out.println(maze);
        System.out.println("Value available: " + maze.getValue());
        System.out.println(seeker);
    }

    /**
     * Lets a TreasureSeeker visit a TreasureMaze as long as they want. Prints
     * information about the TreasureMaze and the TreasureSeeker before and
     * after the visit.
     *
     * @param maze
     *            the TreasureMaze to be visited, must not be null
     * @param seeker
     *            the TreasureSeeker who may visit the TreasureMaze,
     *            must not be null
     */
    private static void collectAndPrint(TreasureMaze maze, TreasureSeeker seeker) {
        System.out.println("===============");
        printMazeAndSeeker(maze, seeker);
        maze.visit(seeker);
        printMazeAndSeeker(maze, seeker);
    }

    /**
     * @param args
     *            Ignored.
     */
    public static void main(String[] args) {
        // Create and show the TreasureMaze from our description.
        TreasureMaze maze = mkMaze();
        System.out.println(maze);

        // These are the protagonists of our description ...
        TreasureSeeker s1 = new TreasureSeeker("Lara", 150);
        TreasureSeeker s2 = new TreasureSeeker("Indy", 120);
        TreasureSeeker s3 = new TreasureSeeker("Guybrush", 300);

        // ... and here is what happens in their visits to the TreasureMaze.
        collectAndPrint(maze, s1);
        collectAndPrint(maze, s2);
        collectAndPrint(maze, s3);
    }
/*
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Coin(80) named Karshapana
Chamber B: exits [B, C, H] with Trove(90) of [SimpleTreasure(40), Coin(50) named Doubloon]
Chamber C: exits [B, D] with SimpleTreasure(40)
Chamber D: exits [E, G] with Coin(70) named Dinar
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with SimpleTreasure(30)
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

===============
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Coin(80) named Karshapana
Chamber B: exits [B, C, H] with Trove(90) of [SimpleTreasure(40), Coin(50) named Doubloon]
Chamber C: exits [B, D] with SimpleTreasure(40)
Chamber D: exits [E, G] with Coin(70) named Dinar
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with SimpleTreasure(30)
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 1370
Treasure seeker Lara has [] and currently seeks a value of 150
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Lara was here!
Chamber B: exits [B, C, H] with Lara was here!
Chamber C: exits [B, D] with SimpleTreasure(40)
Chamber D: exits [E, G] with Coin(70) named Dinar
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with SimpleTreasure(30)
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 1200
Treasure seeker Lara has [Coin(80) named Karshapana, Trove(90) of [SimpleTreasure(40), Coin(50) named Doubloon]] and currently seeks a value of -20
===============
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Lara was here!
Chamber B: exits [B, C, H] with Lara was here!
Chamber C: exits [B, D] with SimpleTreasure(40)
Chamber D: exits [E, G] with Coin(70) named Dinar
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with SimpleTreasure(30)
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 1200
Treasure seeker Indy has [] and currently seeks a value of 120
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Lara was here!
Chamber B: exits [B, C, H] with Lara was here!
Chamber C: exits [B, D] with Indy was here!
Chamber D: exits [E, G] with Indy was here!
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with Indy was here!
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 1060
Treasure seeker Indy has [SimpleTreasure(40), Coin(70) named Dinar, SimpleTreasure(30)] and currently seeks a value of -20
===============
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Lara was here!
Chamber B: exits [B, C, H] with Lara was here!
Chamber C: exits [B, D] with Indy was here!
Chamber D: exits [E, G] with Indy was here!
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with Indy was here!
Chamber G: exits [H] with FancyTrove(1000) of [] named Big Whoop!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 1060
Treasure seeker Guybrush has [] and currently seeks a value of 300
Maze with entrance in Chamber A, size 8, and these designated chambers:
Chamber A: exits [B] with Lara was here!
Chamber B: exits [B, C, H] with Lara was here!
Chamber C: exits [B, D] with Indy was here!
Chamber D: exits [E, G] with Indy was here!
Chamber E: exits [F] with SimpleTreasure(0)
Chamber F: exits [] with Indy was here!
Chamber G: exits [H] with Guybrush was here!
Chamber H: exits [A] with Coin(60) named Gold-pressed latinum

Value available: 60
Treasure seeker Guybrush has [FancyTrove(1000) of [] named Big Whoop!] and currently seeks a value of -700
*/
}
