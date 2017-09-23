/*import stdlib.*;*/
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
//The order of two or more iterators to the same randomized queue must be mutually independent; each iterator must maintain its own random order. 
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int head; //first item index
    private int tail; //index after the last item. q[tail] is always null
    private int capacity; //total  length of the array
    private int size; //number of items in the array
    public RandomizedQueue() {
        // construct an empty randomized queue
        q = (Item[]) new Object[1];
        capacity = 1;
        head = 0;
        tail = 0;
        size = 0;
    }
    public boolean isEmpty() {
        // is the queue empty?
        return q[head] == null;
    }
    public int size() {
        // return the number of items on the queue
        return size;
    }
    public void enqueue(Item item) {
        // add the item
        //changes size but not capacity
        if(item == null) throw new java.lang.NullPointerException();
        if (capacity == tail) {
            resize(2 * tail);
        }
        q[tail++] = item;
        size++;
    }
    private void resize(int cap) {
        //changes capacity but not size
        Item[] copy = (Item[]) new Object[cap];
        for (int i = 0; i < capacity; i++) {
            copy[i] = q[i];
        }
        q = copy;
        capacity = cap;
    }
    public Item dequeue() {
        // remove  and return a random item
        //changes size but not capacity
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int index = getRandomIndex();
        Item itemToBeRemoved = q[index];
        q[index] = null;
        if(index == tail-1) tail--;
        else if(index == head) head++;
        size--;
        if (size() > 0 && size() == tail/4) {
            resize(tail/2);
        }
        return itemToBeRemoved;
    }
    public Item sample() {
        // return (but do not remove) a random item
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return q[getRandomIndex()];
    }
    private int getRandomIndex() {
        int index =  StdRandom.uniform(tail);
        while(q[index] == null) index =  StdRandom.uniform(tail);
        return index;
    }
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Item[] array;
        private int len;
        public RandomizedQueueIterator() {
            i = 0;
            len = size();
            array = (Item[]) new Object[len];        
            for (int i = head, j = 0; i < tail; i++) {
                if(q[i] != null) array[j++] = q[i];
            }
            StdRandom.shuffle(array);
        }
        public boolean hasNext() {
            return i < len;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (i >= len) throw new java.util.NoSuchElementException();
            return array[i++];
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
    private void printQueueArray() {
        for(int i=head; i < tail; i++) {
            StdOut.print(q[i]);
            StdOut.printf(" ");
        }
        StdOut.println();
    }
    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        rq.printQueue();
        rq.printQueue();
        StdOut.println(rq.dequeue());
        rq.printQueue();
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
    }
}