import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import io.FileIO;

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
        File toDelThree = new File("testbytes.txt");
        File toDelFour = new File("testboho.txt");
        try {
            toDelOne.delete();
            toDelTwo.delete();
            toDelThree.delete();
            toDelFour.delete();
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
        assertTrue(s.equals("aababa"));
    }
    
    @Test
    public void writeFileTest() {
        String s = "Caught in a landslide. No escape from reality.";
        String k = new String();
        try{
            this.io.writeFile(s, "testboho.txt");
            k += this.io.readFile("testboho.txt");
        } catch (Exception e) {

        }

        assertTrue(s.equals(k));

    }

    @Test
    public void byteArrayReadWriteTest() {
        String s = "Open your eyes, look up to the skies and see.";
        byte[] bytes = null;
        try {
            bytes = s.getBytes("UTF-8");    
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        byte[] readBytes = null;
        
        try {
            this.io.writeByteArray(bytes, "testbytes.txt");    
            readBytes = this.io.readByteArray("testbytes.txt");
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        assertArrayEquals(bytes, readBytes);
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