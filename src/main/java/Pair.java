import java.util.Arrays;

/**
 * A data structure with two generic constructors.
 * @param <T> any java object
 * @param <P> any java object
 */
public class Pair<T, P> {
    private T first;
    private P second;
    
    /**
     * Creates a new instance with any two objects.
     * @param first the first object.
     * @param second the second object.
     */
    public Pair(T first, P second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() {
        return this.first;
    }

    public P getSecond() {
        return this.second;
    }

    public void setFirst(T newFirst) {
        this.first = newFirst;
    }

    public void setCounts(P newSecond) {
        this.second = newSecond;
    }

}
