package config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Reads the config file and allows access to the properties.
 */

public class Config {
    
    /**
     * Java properties class.
     */
    private Properties properties;
    /**
     * Chunk size for BWT + RLE encodin.
     */
    private int bwtChunkSize;
    /**
     * Not yet implemented max run length for RLE.
     */
    private int rleMaxRunLength;
    /**
     * Size of sliding buffer for LZSS.
     */
    private int lzssBufferSize;
    /**
     * Size of LZSS token.
     */
    private int lzssTokenSize;
    /**
     * Max time of iterations that LZSS looks for a better token.
     */
    private int lzssMaxIterations;
    
    /**
     * Constructor method, reads config.properties.
     */
    public Config() {
        this.properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            this.properties.load(new InputStreamReader(fis, Charset.forName("UTF-8")));          
            this.bwtChunkSize = Integer.valueOf(this.properties.getProperty("bwtChunkSize"));
            this.rleMaxRunLength = Integer.valueOf(this.properties.getProperty("rleMaxRunLength"));
            this.lzssBufferSize = Integer.valueOf(this.properties.getProperty("lzssBufferSize"));
            this.lzssTokenSize = Integer.valueOf(this.properties.getProperty("lzssTokenSize"));
            this.lzssMaxIterations = Integer.valueOf(this.properties.getProperty("lzssMaxIterations"));

        } catch (Exception e) {
            System.out.println("Read from config fails.");
        }
        
    }

    /**
     * @return BWT chunk size
     */
    public int getBwtChunkSize() {
        return this.bwtChunkSize;
    }

    /**
     * @return RLE max run length
     */
    public int getRleMaxRunLength() {
        return this.rleMaxRunLength;
    } 

    /**
     * @return LZSS buffer size
     */
    public int getLzssBufferSize() {
        return this.lzssBufferSize;
    }

    /**
     * @return LZSS token size
     */
    public int getLzssTokenSize() {
        return this.lzssTokenSize;
    }

    /**
     * @return max number of iterations for LZSS
     */
    public int getLzssMaxIterations() {
        return this.lzssMaxIterations;
    }
    
}