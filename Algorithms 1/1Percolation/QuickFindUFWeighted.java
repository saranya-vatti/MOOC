public class QuickFindUFWeighted {
    private int[] id;
    private int[] sz;
    public void QuickFindUFWeighted(int N) {
        id = new int[N];
        sz = new int[N];
        for(int i=0; i<N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    private int root(int i) {
        while (i!=id[i]) i = id[i];
        return i;
    }
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] = sz[i] + sz[j];
        } else {
            id[j] = i;
            sz[i] = sz[i] + sz[j];
        }
    }
    public static void main(String[] args) {
        QuickFindUFWeighted qf = new QuickFindUFWeighted();
        boolean x = qf.connected(3,4);
        StdOut.println(x);
    }
}