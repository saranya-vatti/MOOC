public class ST<Key, Value> {
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
        if(isEmpty()) return null;
        int i = rank(key);
        if(i<N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }
    private int rank(Key key) {
        int lo = 0, hi = N-1;
        while(lo<=hi) {
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp<0) hi = mid-1;
            else if(cmp>0) lo = mid+1;
            else return mid;
        }
        return lo;
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
    public Iterable<Key> keys() {
        //all keys in the table
    }
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for(int i=0;!StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys()) {
            StdOut.println(s+" " + st.get(s));
        }
    }
}