import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

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
    public String readFile(String path) throws IOException {
        Path fileName = Path.of(path);
        String content = "";
        try {
            content = Files.readString(fileName);
        } catch (Exception e) {

        }
        return content;
    }

    public void writeFile(String content, String outputPath) throws IOException {
        try {
            FileWriter writer = new FileWriter(outputPath);
            writer.write(content);
            writer.close(); 
            
        } catch (Exception e) {

        }
    }

    public void writeByteArray(ArrayList<byte[]> content, String outputPath) throws IOException{
        FileOutputStream fout = new FileOutputStream(outputPath);
            
        for (byte [] bytes : content) {
            try {
                fout.write(bytes);
            } catch (IOException e) {

            }   
        } 
        fout.close();
    }
    /**
     * Writes an RLE encoded string as bytes
     * @param pair The char[] int[] pair from RLE.encode()
     * @param outputPath Path to write to.
     * @throws IOException
     */
    public void writeRLEBytes(Pair<char[], int[]> pair, String outputPath) throws IOException {
        String content = pair.getFirst().toString();
        int[] counts = pair.getSecond();
        
        byte[] contentBytes = null;
        byte[] countsBytes = new byte[counts.length];

        try {
            contentBytes = content.getBytes("UTF-8");
        } catch(Exception e) {

        }
        for (int i = 0; i < countsBytes.length; i++) {
            countsBytes[i] = (byte) counts[i];
        }
        
        // construct a 4 byte header that tells us where the counts stop
        // and the chracters begin

        byte[] header = BigInteger.valueOf(countsBytes.length).toByteArray();

        if (header.length < 4) {
            byte[] temp = new byte[4];

            int i = header.length - 1;
            int j = temp.length - 1;

            while (i >= 0) {
                temp[j] = header[i];
                i--;
                j--;
            }

            header = temp;
        }

        try {
            FileOutputStream fout = new FileOutputStream(outputPath);
            fout.write(header);
            fout.write(countsBytes);
            fout.write(contentBytes);
            fout.close();
        } catch (IOException e) {

        }
    }

    public Pair<char[], int[]> readRLEBytes(String filepath) throws IOException {
        File file = new File(filepath);
        System.out.println(filepath);
        byte[] content = new byte[(int) file.length()];

        try {
            FileInputStream fin = new FileInputStream(file);
            fin.read(content);
            fin.close();
        } catch (IOException e) {

        }
        
        // the header is 4 bytes
        byte[] header = new byte[4];

        for (int i = 0; i < header.length; i++) {
            header[i] = content[i];
        }

        BigInteger big = new BigInteger(header);
        int n = big.intValue();

        System.out.println("File IO says there are " + n + " counts");

        // so counts start at i = 4 and chars at i = 4 + header
        int[] counts = new int[n];
        
        int countsIndex = 0;
        for (int i = 4; i < 4 + n; i++) {
            counts[countsIndex] = (int) content[i];
            countsIndex++;
        }
        
        int charsLength = content.length - n - 4;
        byte[] bytesToString = new byte[charsLength];

        int charsIndex = 0;
        for (int i = 4 + n; i < content.length; i++) {
            bytesToString[charsIndex] = content[i];
            charsIndex++;
        }
        String s = new String(bytesToString, StandardCharsets.UTF_8);
        System.out.println(s);
        char[] chars = (new String(bytesToString, StandardCharsets.UTF_8)).toCharArray();
        System.out.println(chars.toString());
        Pair<char[], int[]> result = new Pair<>(chars, counts);
        
        return result;
        
    }

    /**
     * 
     * @param pathA path of first file.
     * @param pathB path of second file.
     * @return boolean showing if the content of the files is equal.
     */
    public boolean compareFiles(String pathA, String pathB) {
        String contentA = "A";
        String contentB = "B";
        
        try{
            contentA = readFile(pathA);
            contentB = readFile(pathB);
        } catch (Exception e) {

        }
        
        return contentA.equals(contentB);
    }

    /**
     * Prints a comparision report of the compression showing
     * the size of the original and compressed files, and the compression ratio.
     * @param original path of original file.
     * @param compressed path of compressed file.
     */
    public String compressionRatio(String original, String compressed) {
        StringBuilder sb = new StringBuilder();
        try {
            double originalBytes = Files.size(Path.of(original));
            double compressedBytes = Files.size(Path.of(compressed));
            double ratio = compressedBytes / originalBytes;

            sb.append("original size: " + originalBytes + "\n");
            sb.append("compressed size: " + compressedBytes + "\n");
            sb.append("compression ratio: " + String.format("%,.2f", ratio) );
        } catch (Exception e) {

        }

        return sb.toString();
    }

}
