public class FixedCapaciyStack<Item>() {
    private Item[] s;
    private int N = 0;
    public FixedCapacityStackOfStrings(int capacity) {
        //s = new Item[capacity]; //generic array creation not allowed in java
        s = (Item[]) new Object[capacity]; //casting - also bad
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public void push(Item item) {
        //resize array if N>capacity
        s[N++] = item;
    }
    public Item pop() {
        //throw exception if empty stack
        Item item = s[--N];
        s[N] = null;
        return s[--N];
    }
}