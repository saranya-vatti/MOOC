/*import stdlib.*;*/
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int s;
    public Deque() {
        // construct an empty deque
        first = null;
        last = null;
        s = 0;
    }
    private class Node {
        Item item;
        Node next;
    }
    public boolean isEmpty() {
        // is the deque empty?
        return first == null || last == null;
    }
    public int size() {
        // return the number of items on the deque
        return s;
    }
    public void addFirst(Item item) {
        // add the item to the front
        validateAdd(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if(isEmpty()) last = first;
        first.next = oldFirst;
        s++;
    }
    public void addLast(Item item) {
        // add the item to the end
        validateAdd(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        s++;
    }
    private void validateAdd(Item item) {
        if(item == null) throw new java.lang.NullPointerException();
    }
    public Item removeFirst() {
        // remove and return the item from the front
        validateRemove();
        Node oldFirst = first;
        Item item = oldFirst.item;
        first = oldFirst.next;
        oldFirst = null;
        s--;
        return item;
    }
    public Item removeLast() {
        // remove and return the item from the end
        validateRemove();
        Node oldLast = last;
        Item item = oldLast.item;
        Node curr = first;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        oldLast = null;
        s--;
        return item;
    }
    private void validateRemove() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
    }
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
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
        Deque<String> dq = new Deque<String>();
        dq.addFirst("2");
        dq.addFirst("1");
        dq.addLast("3");
        dq.addLast("4");
        dq.printQueue();
        String tmpstr = dq.removeFirst();
        dq.printQueue();
        tmpstr = dq.removeLast();
        dq.printQueue();
    }
}