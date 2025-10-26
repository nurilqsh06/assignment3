package main.algoritm;

import main.model.Edge;
import main.model.Graph;
import main.model.MSTResult;

import java.util.*;

public class KruskalAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();

        if (nodes.isEmpty()) {
            return new MSTResult(new ArrayList<>(), 0, 0, 0);
        }

        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));
        operationsCount += edges.size() * (int) (Math.log(edges.size()) / Math.log(2));

        UnionFind uf = new UnionFind(nodes.size());
        Map<String, Integer> nodeIndex = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            nodeIndex.put(nodes.get(i), i);
        }
        operationsCount += nodes.size();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int edgesUsed = 0;

        for (Edge edge : sortedEdges) {
            if (edgesUsed == nodes.size() - 1) break;

            int u = nodeIndex.get(edge.from);
            int v = nodeIndex.get(edge.to);
            operationsCount += 2;

            if (uf.union(u, v)) {
                mstEdges.add(edge);
                totalCost += edge.weight;
                edgesUsed++;
                operationsCount += 3;
            }
            operationsCount++;
        }

        operationsCount += uf.getOperationsCount();

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}
