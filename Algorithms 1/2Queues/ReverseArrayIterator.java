public class Stack<Item> implements Iterable<Item> {
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() {
            return i > 0;
        }
        public void remove() {
        }
        public Item next() {
            return s[--i];
        }
    }
    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
            s[N++] = item;
        }
    }
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) {
            resize(s.length/2);
        }
        return item;
    }
}
