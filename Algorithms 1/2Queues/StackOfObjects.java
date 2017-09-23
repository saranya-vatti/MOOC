public class Stack<Item>() {
    private Node first = null;
    public Stack<Item>() {
        //create an empty stack
    }
    private class Node() {
        Item item;
        Node next;
    }
    public void push(Item item) {
        //insert a new string onto stack
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
    }
    public Item pop() {
        //remove and retun the string most recently added
        Item item = first.item;
        first = first.next;
        return item;
    }
    public boolean isEmpty() {
        //is the stack empty?
        return first == null;
    }
    private int size() {
        //number of strings on the stack
    }
    public String void main(String[] args) {
        Stack<Apple> stack = new Stack<Apple>();
        Apple a = new Apple();
        Orange b = new Orange();
        s.push(a);
        s.push(b); //compile time error
        a = s.pop();
    }
}