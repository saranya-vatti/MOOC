public class OrderedST<Key extends Comparable<Key>, Value> {
     public ST() {
        //create a symbol table
    }
    public void put(Key key, Value val) {
        //put key-value pair into the table
        //remove key from table if value if null
        //overwrites if key already present
        //binary search implementation needs max N moves of the array elements to add an element
    }
    public Value get(Key key) {
        //value paired with key
        //null if key is absent
    }
    public void delete (Key key) {
        //remove key and its value form table
        put(key, null);
    }
    public boolean contains(Key key) {
        //is there a value paired with key?
        return get(key) != null;
    }
    public boolean isEmpty() {
        //is the table empty?
    }
    public int size() {
        //number of key-value pairs in the tabl;e
    }
    public Key min() {
        //smallest key
    }
    public Key max() {
        //largest key
    }
    public Key floor(Key key) {
        //largest key less than or equal to key
    }
    pblic Key ceiling(Key key) {
        //smallest key greter than or equal to key
    }
    public int rank(Key key) {
        //number of keys less than key
    }
    public Key select(int k) {
        //key of rank k
    }
    public void deleteMin() {
        //delete smallest key
    }
    public void deleteMax() {
        delete largest key
    }
    public int size(Key lo, Key hi) {
        //number of keys in [lo...i]
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        //keys in [lo...hi] in sorted order
    }
    public Iterable<Key> keys() {
        //all keys in the table in sorted order
    }
}