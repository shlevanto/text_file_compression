package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for generic file IO functionalities such as 
 * reading a file to a string,
 * reading and writing files to byte arratys, 
 * comparing two files and generating report for compression ratio.
 */

public class FileIO {
    
     /**
     * Reads given file to a string.
     * @param path Path of file to be opened.
     * @return Content of file as a single string.
     */
    public String readFile(final String path) throws IOException {
        Path fileName = Path.of(path);
        String content = "";
        try {
            content = Files.readString(fileName);
        } catch (Exception e) {

        }
        return content;
    }

    /**
     * Writes string to file on one line.
     * @param content String to be writtn to file.
     * @param filepath Path of the file to be written to.
     * @throws IOException
     */
    public void writeFile(String content, String filepath) throws IOException {
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write(content);
            writer.close(); 
            
        } catch (Exception e) {

        }
    }

    /**
     * Writes byte array to file. Overwrites excisting files of the same name.
     * @param bytes the array to write
     * @param filepath the path to writ the file
     */
    public void writeByteArray(byte[] bytes, String filepath) throws IOException {
        FileOutputStream fout = new FileOutputStream(filepath);
            
        try {
            fout.write(bytes);
            fout.close();
        } catch (IOException e) {

        }
        
    }
    
    /**
     * Reads a file from specified path into an byte array.
     * @param filepath Path of file to read.
     * @return Content of file as byte array.
     * @throws IOException
     */
    public byte[] readByteArray(String filepath) throws IOException {
        File file = new File(filepath);
        byte[] content = new byte[(int) file.length()];
        
        try {
            FileInputStream fin = new FileInputStream(file);
            fin.read(content);
            fin.close();
        } catch (IOException e) {

        }

        return content;
    }

    /**
     * Compares the content of two files.
     * 
     * @param pathA path of first file.
     * @param pathB path of second file.
     * @return boolean showing if the content of the files is equal.
     */
    public boolean compareFiles(String pathA, String pathB) {
        String contentA = "A";
        String contentB = "B";
        
        try {
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
     * @return output of compression rate as string.
     */
    public String compressionRatio(String original, String compressed) {
        
        
        StringBuilder sb = new StringBuilder();
        try {
            double originalBytes = Files.size(Path.of(original));
            double compressedBytes = Files.size(Path.of(compressed));
            double ratio = compressedBytes / originalBytes;

            sb.append("original size: " + originalBytes + "\n");
            sb.append("compressed size: " + compressedBytes + "\n");
            sb.append("compression ratio: " + String.format("%,.2f", ratio) + " of original size.");
        } catch (Exception e) {
            System.out.println("Compression ratio could not be calculated.");
        }

        return sb.toString();
    }

}
