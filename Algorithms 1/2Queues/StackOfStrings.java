public class StackOfStrings {
    private Node first = null;
    private size;
    public StackOfStrings() {
        //create an empty stack
        this.size = 0;
    }
    public void push(String item) {
        //insert a new string onto stack
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.size++;
    }
    public String pop() {
        //remove and retun the string most recently added
        String item = first.item;
        first = first.next;
        this.size--;
        return item;
    }
    public boolean isEmpty() {
        //is the stack empty?
        return first == null;
    }
    private int size() {
        //number of strings on the stack
        return this.size;
    }
    private class Node() {
        String item;
        Node next;
    }
    public String void main(String[] args) {
        StackOfStrings stack -= new StackOfStrings();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else stack.push(s);
        }
    }
}