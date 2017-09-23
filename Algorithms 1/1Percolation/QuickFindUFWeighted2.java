public class QuickFindUFWeighted2 {
    private int[] id;
    private int[] size;
    int N;
    public QuickFindUFWeighted2(int N) {
        this.id = new int[N*N + 2];
        this.size = new int[N*N + 2];
        for(int i=0; i<N*N + 2; i++) {
            id[i] = i;
            size[i] = 1;
        }
        this.N = N;
    }
    private int root(int i) {
        while (i!=this.id[i]) i = this.id[i];
        return i;
    }
    public boolean connected(int p, int q) {
        return this.root(p) == this.root(q);
    }
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(this.size[i] < this.size[j]) {
            this.id[i] = j;
            this.size[j] = this.size[i] + this.size[j];
        } else {
            this.id[j] = i;
            this.size[i] = this.size[i] + this.size[j];
        }
    }
    public void printQF() {
        for(int i=0;i<=N*N+1;i++){
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }
}