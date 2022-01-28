import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

public class FileIOTest {
    private FileIO io;
    
    public FileIOTest() {
        this.io = new FileIO();
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
        File toDelTwo = new File("testfile2.txt");
        try {
            toDelOne.delete();
            toDelTwo.delete();
        } catch (Exception e) {
            System.out.println("No test files found.");
        }
    }

    @Test
    public void readFileTest() {
        String s = "";
        try {
            s = this.io.readFile("testfile.txt");
        } catch (Exception e) {

        }
        System.out.println(s);
        assertTrue(s.equals("aababa"));
    }
    
    @Test
    public void compareFilesTest() {
        try {
            FileWriter myWriter = new FileWriter("testfile2.txt");
            myWriter.write("aababa");
            myWriter.close();
        } catch (Exception e) {
        }
        assertTrue(this.io.compareFiles("testfile.txt", "testfile2.txt"));
    }

    @Test
    public void compressionRatioTest() {
        String result = this.io.compressionRatio("testfile.txt", "testfile.txt");
        assertTrue(result.contains("original"));
        assertTrue(result.contains("compression ratio: 1.00"));
    }
        
}

