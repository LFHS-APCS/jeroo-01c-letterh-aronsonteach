import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 *
 * @author  Aronson
 */
public class TestMain
{

    public static void main(String args[]) {
        TestMain test = new TestMain();
        test.testLetterH();
    }

    @Test
    public void testLetterH() {
       Main.runJerooCode();

       assertTrue("(1, 1)", Map.getInstance().isFlower(1,1));
       assertTrue("(2, 1)", Map.getInstance().isFlower(1,2));
       assertTrue("(3, 1)", Map.getInstance().isFlower(1,3));
       assertTrue("(4, 1)", Map.getInstance().isFlower(1,4));
       assertTrue("(5, 1)", Map.getInstance().isFlower(1,5));

       assertTrue("(3,2)", Map.getInstance().isFlower(2,3));
       assertTrue("(3,3)", Map.getInstance().isFlower(3,3));

       assertTrue("(1, 4)", Map.getInstance().isFlower(4,1));
       assertTrue("(2, 4)", Map.getInstance().isFlower(4,2));
       assertTrue("(3, 4)", Map.getInstance().isFlower(4,3));
       assertTrue("(4, 4)", Map.getInstance().isFlower(4,4));
       assertTrue("(5, 4)", Map.getInstance().isFlower(4,5));
    }


}
