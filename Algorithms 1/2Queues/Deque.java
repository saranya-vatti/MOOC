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
        return first == null;
    }
    public int size() {
        // return the number of items on the deque
        return s;
    }
    public void addFirst(Item item) {
        // add the item to the front
        validateAdd(item);
        Node oldFirst = first;
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) last = newNode;
        newNode.next = oldFirst;
        first = newNode;
        s++;
    }
    public void addLast(Item item) {
        // add the item to the end
        validateAdd(item);
        Node oldLast = last;
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) first = newNode;
        else last.next = newNode;
        last = newNode;
        s++;
    }
    private void validateAdd(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
    }
    public Item removeFirst() {
        // remove and return the item from the front
        validateRemove();
        Item item = first.item;
        if (first == last) {
            first = last = null;
        } else {
            Node oldFirst = first;
            first = first.next;
            oldFirst = null;
        }        
        s--;
        return item;
    }
    public Item removeLast() {
        // remove and return the item from the end
        validateRemove();
        Item item = last.item;
        if (first == last) {
            first = last = null;
        } else {
            Node curr = first;         
            while (curr.next != last) {
                curr = curr.next;
            }
            curr.next = null;
            last = curr;
        }
        s--;        
        return item;
    }
    private void validateRemove() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
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
        while (i.hasNext()) {
            System.out.print(i.next());
            StdOut.printf(" ");
        }
        StdOut.println("<- last");
    }
    public static void main(String[] args) {
        // unit testing
        Deque<Integer> deque = new Deque<Integer>();
         StdOut.println(deque.isEmpty());
         deque.addFirst(1);
         StdOut.println(deque.isEmpty());
         deque.addFirst(3);
         deque.addFirst(4);
         StdOut.println(deque.removeLast());
         StdOut.println(deque.removeLast());
         StdOut.println(deque.removeLast());
         StdOut.println(deque.isEmpty());
         deque.addFirst(9);
         StdOut.println(deque.removeLast());
    }
}