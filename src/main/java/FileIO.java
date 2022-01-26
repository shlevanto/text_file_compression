import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for generic file IO functionalities such as reading a file to a string, 
 * comparing two files and generating report for compression ratio.
 */

public class FileIO {
    
     /**
     * Reads given file to a string.
     * @param path Path of file to be opened.
     * @return Content of file as a single string.
     */
    public String readFile(String path) {
        Path fileName = Path.of(path);
        String content = "";
        try {
            content= Files.readString(fileName);
        } catch (Exception e) {

        }

        return content;
    }

    /**
     * 
     * @param pathA path of first file.
     * @param pathB path of second file.
     * @return boolean showing if the content of the files is equal.
     */
    public boolean compareFiles(String pathA, String pathB) {
        String contentA = readFile(pathA);
        String contentB = readFile(pathB);
        
        return contentA.equals(contentB);
    }

    /**
     * Prints a comparision report of the compression showing
     * the size of the original and compressed files, and the compression ratio.
     * @param original path of original file.
     * @param compressed path of compressed file.
     */
    public void compressionRatio(String original, String compressed) {
        try {
            double originalBytes = Files.size(Path.of(original));
            double compressedBytes = Files.size(Path.of(compressed));
            System.out.println("original size: " + originalBytes);
            System.out.println("compressed size: " + compressedBytes);
            double ratio = compressedBytes / originalBytes;
            System.out.println("compression ratio: " + String.format("%,.2f", ratio));
        } catch (Exception e) {

        }
    }

}
