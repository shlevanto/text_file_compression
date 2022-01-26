import java.nio.file.Files;
import java.nio.file.Path;

public class FileIO {
    public String readFile(String path) {
        Path fileName = Path.of(path);
        String content = "";
        try {
            content= Files.readString(fileName);
        } catch (Exception e) {

        }

        return content;
    }

    public boolean compareFiles(String pathA, String pathB) {
        String contentA = readFile(pathA);
        String contentB = readFile(pathB);
        
        return contentA.equals(contentB);
    }

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
