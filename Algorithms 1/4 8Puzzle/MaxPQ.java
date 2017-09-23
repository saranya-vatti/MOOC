public class MaxPQ<Key extends Comparable<Key>> {
    public MaxPQ() {
        //create an empty priority queue
    }
    private MaxPQ(Key[] a) {
        //create a priority queue with given key
    }
    public void insert (Key v) {
        //insert a key into the priority queue
    }
    private Key delMax() {
        //return and remove the largest key
    }
    public boolean isEmpty() {
        //is the priotity queue empty?
    }
    public static void main(String[] args) {
        MinPQ<Transaction> pq = new MinPQ<Transaction>();
        while(StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);
            if(pq.size() > M) {
                pq.delMin();
            }
        }
    }
}