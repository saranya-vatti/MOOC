public class QueueOfStrings {
    private Node first, last;
    public QueueOfStrings() {
        //create an empty stack
    }
    public void enqueue(String item) {
        //insert a new string onto queue
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = this.last;
    }
    public String dequeue() {
        //remove and retun the string least recently added
        String item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
    public boolean isEmpty() {
        //is the stack empty?
        return first == null;
    }
    /*private int size() {
        //number of strings on the stack
    }*/
    private class Node {
        String item;
        Node next;
    }
    public static void main(String[] args) {
        QueueOfStrings q = new QueueOfStrings();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(q.dequeue());
            else q.enqueue(s);
        }
    }
}