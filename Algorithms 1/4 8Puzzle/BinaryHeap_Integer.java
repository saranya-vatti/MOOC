public class BinaryHeap_Integer {
    private Integer[] pq;
    private int N;
    public BinaryHeap_Integer(Integer[] arr, int N){
        pq = new Integer[arr.length];
        for(int i =0;i<N;i++) pq[i] = arr[i];
        this.N = N-1;
    }
    private void swim(int k) {
        while(k>0 && less(k/2, k)) {
            exch(k, k/2);
            k=k/2;
        }
    }
    public void insert(int x) {
        pq[++N] = x;
        swim(N);
    }
    private void sink(int k) {
        while(2*k < N) {
            int j = 2*k;
            if(j<N-1 && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exch(k,j);
            k=j;
        }
    }
    public int delMax() {
        int max = pq[0];
        exch(0, N--);
        sink(0);
        pq[N+1] = null;
        return max;
    }
    private boolean less(int i, int j) {
        return pq[i]<pq[j];
    }
    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private void printHeap() {
        for(int i=0;i<N;i++){
            StdOut.printf("%d ", pq[i]);
        }
        StdOut.println();
    }
    public static void main(String[] args) {
        Integer[] arr = new Integer[11];
        arr[0] = 91;
        arr[1] = 53;
        arr[2] = 77;
        arr[3] = 47;
        arr[4] = 49;
        arr[5] = 67;
        arr[6] = 68;
        arr[7] = 12;
        arr[8] = 11;
        arr[9] = 38;
        BinaryHeap_Integer heap = new BinaryHeap_Integer(arr, 10);
        heap.printHeap();
        heap.insert(999);
        heap.printHeap();
        int tmp = heap.delMax();
        heap.printHeap();
    }
}