public class FixedCapaciyStackOfStrings() {
    private String[] s;
    private int N = 0;
    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public void push(String item) {
        //resize array if N>capacity
        s[N++] = item;
    }
    public String pop() {
        //throw exception if empty stack
        String item = s[--N];
        s[N] = null;
        return s[--N];
    }
}