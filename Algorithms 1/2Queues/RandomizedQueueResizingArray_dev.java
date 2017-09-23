import stdlib.*;
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import algs4.*;
//The order of two or more iterators to the same randomized queue must be mutually independent; each iterator must maintain its own random order. 
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] q;
    private int head = 0;
    private int length;
    public RandomizedQueue() {
        // construct an empty randomized queue
        N = 0;
        Item[] q = (Item[]) new Object[1];
        q[0] = "bcd";
        head = 0;
        length = 1;
    }
    public boolean isEmpty() {
        // is the queue empty?
        return q[head] == null;
    }
    public int size() {
        // return the number of items on the queue
        return N - head + 1;
    }
    public void enqueue(Item item) {
        // add the item
        if(item == null) throw new java.lang.NullPointerException();
        if (N == length) {
            resize(2 * length);
        }
        StdOut.println(N);
        StdOut.println(q);
        q[N] = item;
        N++;
    }
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = q[i];
        }
        q = copy;
        length = capacity;
    }
    public Item dequeue() {
        // remove  and return a random item
        if(isEmpty()) throw new java.util.NoSuchElementException();
        Item item = q[head];
        q[head++] = null;
        if (N > 0 && N == length/4) {
            resize(length/2);
        }
        return item;
    }
    public Item sample() {
        // return (but do not remove) a random item
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return q[StdRandom.uniform(size())];
    }
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < N;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (i >= N) throw new java.util.NoSuchElementException();
            return q[i++];
        }
    }
    private void printQueue() {
        StdOut.printf("first ->");
        Iterator<Item> i = iterator();
        while(i.hasNext()) {
            System.out.print(i.next());
            StdOut.printf(" ");
        }
        StdOut.println("<- last");
    }
    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("def");
        rq.enqueue("abc");
        rq.enqueue("ghi");
        rq.enqueue("jkl");
        rq.printQueue();
        String tmpstr = rq.dequeue();
        rq.printQueue();
        tmpstr = rq.dequeue();
        rq.printQueue();
    }
}