public class Pair {
    private char[] chars;
    private int[] counts;
    
    public Pair(char[] chars, int[] counts) {
        this.chars = chars;
        this.counts = counts;
    }

    public char[] getChars() {
        return this.chars;
    }

    public int[] getCounts() {
        return this.counts;
    }

    public void setChars(char[] newChars) {
        this.chars = newChars;
    }

    public void setCounts(int[] newCounts) {
        this.counts = newCounts;
    }
}
