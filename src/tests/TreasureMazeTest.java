package tests;
import static org.junit.jupiter.api.Assertions.*;
import core.Chamber;
import core.TreasureMaze;
import main.Coursework2Main;
import org.junit.jupiter.api.Test;
import treasures.*;
import visitors.TreasureSeeker;


/**
 * JUnit 5 test cases for TreasureMaze.
 *
 * @author Carsten Fuhs
 * @author Zdravko Georgiev
 */
public class TreasureMazeTest {

    @Test
    public void test1() {
        TreasureMaze maze = Coursework2Main.mkMaze();

        assertEquals(8, maze.getReachableSize(), "Size should be 8");
        TreasureSeeker Indy =new TreasureSeeker("Indy", 100);
        
        // check if we can assign null as a visitor name, we expect to go in catch
        try{
            TreasureSeeker nullNameTest = new TreasureSeeker(null, 544);
            fail();
        }
        catch (IllegalArgumentException error) {
            assertTrue(true);
        }

        maze.visit(Indy);
        assertEquals(1200, maze.getValue(), "Current treasure value should be 1200");
        
        TreasureSeeker Karshapana =new TreasureSeeker("Karshapana", 120);
        maze.visit(Karshapana);
        assertEquals(-20, Karshapana.getDesiredValue());
    }


    @Test
    public void test2() {
        TreasureMaze maze = Coursework2Main.mkMaze();
        // assert statements
        assertEquals(8, maze.getReachableSize(), "Size should be 8");
        assertEquals(1370, maze.getValue(), "All treasure value should be 1370");

        TreasureSeeker lara =new TreasureSeeker("Lara", 150);
        maze.visit(lara);

        assertEquals(-20, lara.getDesiredValue());

        try {
            maze.visit(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal null argument: visitor", e.getMessage());
        }
    }

    @Test
    public void test3() {
        TreasureMaze maze = differentMaze();
        // assert statements
        assertEquals(1, maze.getReachableSize(), "Size should be 1");
        assertEquals(500, maze.getValue(), "All treasure value should be 500");

        TreasureSeeker lara =new TreasureSeeker("Lara", 150);
        maze.visit(lara);

        assertEquals(-350, lara.getDesiredValue(), "All treasure value should be -350");

        try {
            maze.visit(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal null argument: visitor", e.getMessage());
        }
    }

    @Test
    public void test4() {
        TreasureMaze maze = Coursework2Main.mkMaze();

        // assert statements

        assertEquals(1370, maze.getValue(), "All treasure value should be 1370");

        TreasureSeeker Guybrush =new TreasureSeeker("Guybrush", 200);
        maze.visit(Guybrush);

        TreasureSeeker Lara =new TreasureSeeker("Lara", 190);
        maze.visit(Lara);

        TreasureSeeker Zdravko =new TreasureSeeker("Zdravko", 140);
        maze.visit(Zdravko);

        try {
            maze.visit(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal null argument: visitor", e.getMessage());
        }

        assertEquals("Guybrush", Guybrush.getName(), "Visitor name should be Guybrush and it is " + Guybrush.getName());
        assertEquals(-10, Guybrush.getDesiredValue(), "Desired value should be -10");
        assertFalse(Guybrush.wantsMoreVisits(), "Value should be False");

        assertEquals("Lara", Lara.getName(), "Visitor name should be Lara");
        assertEquals(-910, Lara.getDesiredValue(), "Desired value should be -910");
        assertFalse(Lara.wantsMoreVisits(), "Value should be False");

        assertEquals("Zdravko", Zdravko.getName(), "Visitor name  should be Zdravko");
        assertEquals(80, Zdravko.getDesiredValue(), "Desired values should be 80");
        assertTrue(Zdravko.wantsMoreVisits(),"Value should be True as I want to find more treasures :)" );
    }

    @Test
    public void testAll() {
        TreasureMaze maze = Coursework2Main.mkMaze();
        // assert statements
        assertEquals(8, maze.getReachableSize(), "Size should be 8");
        assertEquals(1370, maze.getValue(), "All treasure value should be 1370");

        TreasureSeeker lara =new TreasureSeeker("Lara", 150);
        maze.visit(lara);
        assertEquals(1200, maze.getValue(), "Current treasure value should be 1200");

        assertEquals(-20, lara.getDesiredValue());

        try {
            maze.visit(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal null argument: visitor", e.getMessage());
        }

        TreasureSeeker indy =new TreasureSeeker("Indy", 120);
        maze.visit(indy);
        assertEquals(-20, indy.getDesiredValue());
        assertEquals(1060, maze.getValue(), "Current treasure value should be 1060");

        TreasureSeeker Guybrush = new TreasureSeeker("Guybrush", 300);
        maze.visit(Guybrush);
        assertEquals(-700, Guybrush.getDesiredValue());
        assertEquals(60, maze.getValue(), "Current treasure value should be 60");

        assertEquals("Maze with entrance in Chamber A, size 8, and these designated chambers:\n" +
                "Chamber A: exits [B] with Lara was here!\n" +
                "Chamber B: exits [B, C, H] with Lara was here!\n" +
                "Chamber C: exits [B, D] with Indy was here!\n" +
                "Chamber D: exits [E, G] with Indy was here!\n" +
                "Chamber E: exits [F] with SimpleTreasure(0)\n" +
                "Chamber F: exits [] with Indy was here!\n" +
                "Chamber G: exits [H] with Guybrush was here!\n" +
                "Chamber H: exits [A] with Coin(60) named Gold-pressed latinum\n", maze.toString());
    }

    /**
     * Method to create different maze sizes
     * @return maze has only one chamber with value of 500
     */
    private TreasureMaze differentMaze() {
        // first create all the Chambers with the Treasures in them ...
        Chamber[] chambers = {new Chamber(new SimpleTreasure(500))};

        // Create the exits from one Chamber to the next
        chambers[0].addExit(chambers[0]);

        // The TreasureMaze is complete!
        return new TreasureMaze(0, chambers);
    }
}
