/**
 * Make the letter H as described in the Readme.md
 * IMPORTANT:  You can only use:
 *      hop()
 *      plant()
 *      turn(LEFT)
 *  Use left and right arrow keys to see it when running main()
 */
public class Main implements Directions {

    /**
     * Do NOT edit this.  Put your code inside the runJerooCode method below.
     */
    public static void main(String[] args) {
        new JerooGUI();
        runJerooCode();
        Map.getInstance().printMap();
    }

    /**
     * Put your main Jeroo code here.
     */
    public static void runJerooCode() {
       // Write code here to make a letter here
       Jeroo kim = new Jeroo(0, 0, EAST, 100);
    
    kim.hop();
    kim.turn(RIGHT);
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.turn(LEFT);
    kim.turn(LEFT);
    kim.hop();
    kim.hop();
    kim.turn(RIGHT);
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.turn(RIGHT);
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.turn(LEFT);
    kim.turn(LEFT);
    kim.hop();
    kim.hop();
    kim.hop();
    kim.plant();
    kim.hop();
    kim.plant();
    kim.hop();
    

    }

}
