import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

public class RLETest {
    private FileIO io;
    private RLE rle;

    public RLETest() {
        this.io = new FileIO();
        this.rle = new RLE(io);
        try {
            FileWriter myWriter = new FileWriter("testfile.txt");
            myWriter.write("aababa");
            myWriter.close();
        } catch (Exception e) {
        }
    }
    
    @After
    public void tearDownClass() {
        File toDelOne = new File("testfile.txt");
        File toDelTwo = new File("output.txt");
        try {
            toDelOne.delete();
            toDelTwo.delete();
        } catch (Exception e) {
            System.out.println("No test files found.");
        }
    }

    @Test
    public void encodeDecodeTest() {
        rle.encode("testfile.txt", "output.txt");
        File file = new File("output.txt");
        assertTrue(file.exists());
        String decoded = rle.decode("output.txt");
        assertTrue(decoded.equals("aababa"));    
    }

}
