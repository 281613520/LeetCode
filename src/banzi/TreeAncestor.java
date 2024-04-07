package banzi;

public class TreeAncestor {

    int[][] pa;
    public TreeAncestor(int n, int[] parent) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        pa = new int[m][n];
        for (int i = 0; i < n; i++) {
            pa[0][i] = parent[0];
        }

        for (int x = 1; x < n; x++) {
            for (int i = 1; i < n; i++) {
                int p = pa[x][i];
                pa[x][i+1] = p < 0? -1 : pa[p][i];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int m = 32 - Integer.numberOfLeadingZeros(k);
        for (int i = 0 ; i < m; i++) {
            if ( ((k>>i) & 1) == 1){
                node = pa[node][i];
                if (node< 0){
                    break;
                }
            }
        }
        return node;
    }
}
