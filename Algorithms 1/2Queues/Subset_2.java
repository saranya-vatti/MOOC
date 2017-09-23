/*import stdlib.*;*/
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Subset {
    public Subset() {
    }
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}