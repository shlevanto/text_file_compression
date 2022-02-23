package config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Reads the config file and allows access to the properties.
 */

public class Config {
    
    private Properties properties;
    private int bwtChunkSize;
    private int rleMaxRunLength;
    private int lzssBufferSize;
    private int lzssTokenSize;
    
    

    public Config() {
        this.properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            this.properties.load(new InputStreamReader(fis, Charset.forName("UTF-8")));          
            this.bwtChunkSize = Integer.valueOf(this.properties.getProperty("bwtChunkSize"));
            this.rleMaxRunLength = Integer.valueOf(this.properties.getProperty("rleMaxRunLength"));
            this.lzssBufferSize = Integer.valueOf(this.properties.getProperty("lzssBufferSize"));
            this.lzssTokenSize = Integer.valueOf(this.properties.getProperty("lzssTokenSize"));

        } catch (Exception e) {
            System.out.println("Read from config fails.");
        }
        
    }

    public int getBwtChunkSize() {
        return this.bwtChunkSize;
    }

    public int getRleMaxRunLength() {
        return this.rleMaxRunLength;
    } 
    
    public int getLzssBufferSize() {
        return this.lzssBufferSize;
    }

    public int getLzssTokenSize() {
        return this.lzssTokenSize;
    }
    
}