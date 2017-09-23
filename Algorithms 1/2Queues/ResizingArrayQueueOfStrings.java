public class ResizingArrayQueueOfStrings() {
    private int head, tail;
    public ResizingArrayQueueOfStrings() {
        q = new String[1];
        head = tail = 0;
    }
    public void enqueue(String item) {
        if (tail == q.length) {
            resize(2 * q.length);
            q[tail++] = item;
        }
    }
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < tail; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }
    public String dequeue() {
        String item = q[head];
        q[head++] = null;
        if (tail > 0 && tail == q.length/4) {
            resize(q.length/2);
        }
        return item;
    }
}
