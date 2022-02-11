/**
 * A data structure with two generic constructors.
 * @param <T> any java object
 * @param <P> any java object
 */
public class Pair<T, P> {
    /**
     * The first object of the Pair.
     */
    private T first;

    /**
     * The second object of the Pair.
     */
    private P second;
    
    /**
     * Creates a new instance with any two objects.
     * @param firstObject the first object.
     * @param secondObject the second object.
     */
    public Pair(T firstObject, P secondObject) {
        this.first = firstObject;
        this.second = secondObject;
    }
    
    /**
     * @return first object.
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * @return seconf object.
     */
    public P getSecond() {
        return this.second;
    }

}
