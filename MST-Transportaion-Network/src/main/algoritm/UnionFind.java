package main.algoritm;

public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int operationsCount;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        operationsCount = 0;

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            operationsCount++;
        }
    }

    public int find(int x) {
        operationsCount++;
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
            operationsCount++;
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        operationsCount += 2;

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        operationsCount += 3;

        return true;
    }

    public int getOperationsCount() {
        return operationsCount;
    }
}