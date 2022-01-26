public abstract class Compressor {
    public abstract Object encode();
    public abstract Object decode();

    public String readFile(String path) {
        return "";
    }

    public void writeFile(String path) {

    }
}
