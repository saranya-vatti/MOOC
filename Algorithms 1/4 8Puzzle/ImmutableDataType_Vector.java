public class ImmutableDataType_Vector  {
    private final int N;
    private final doulbe[] data;
    public Vector(double[] data) {
        this.N = data.length;
        this.data = new double[N];
        for(int i = 0;i<N;i++) {
            this.data[i] = data[i];
        }
    }
}
           