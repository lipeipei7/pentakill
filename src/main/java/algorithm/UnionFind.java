package algorithm;

import java.util.HashSet;
import java.util.Set;

public class UnionFind {
    public int[] root;
    public int[] rank;

    public UnionFind(int n) {
        root = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int a) {
        if (a != root[a]) {
            root[a] = find(root[a]);
        }

        return root[a];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public int getNRoots() {
        Set<Integer> nRoots = new HashSet<>();
        for (int c: root) {
            if (c != -1) {
                nRoots.add(find(c));
            }
        }
        return nRoots.size();
    }
}
