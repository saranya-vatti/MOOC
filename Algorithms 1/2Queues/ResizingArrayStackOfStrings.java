public class ResizingArrayStackOfStrings() {
    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }
    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
            s[N++) = item;
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
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
