package main.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import main.model.GraphResult;

public class PerformanceAnalyzer {

    public static void generateReport(List<GraphResult> results, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        // Performance Summary
        writer.write("PERFORMANCE SUMMARY\n");
        writer.write("===================\n\n");

        Map<String, List<GraphResult>> byCategory = new HashMap<>();
        for (GraphResult result : results) {
            byCategory.computeIfAbsent(result.getCategory(), k -> new ArrayList<>()).add(result);
        }

        for (Map.Entry<String, List<GraphResult>> entry : byCategory.entrySet()) {
            String category = entry.getKey();
            List<GraphResult> categoryResults = entry.getValue();

            double avgPrimTime = categoryResults.stream()
                    .mapToDouble(r -> r.getPrimResult().executionTimeMs)
                    .average().orElse(0);
            double avgKruskalTime = categoryResults.stream()
                    .mapToDouble(r -> r.getKruskalResult().executionTimeMs)
                    .average().orElse(0);
            double avgPrimOps = categoryResults.stream()
                    .mapToDouble(r -> r.getPrimResult().operationsCount)
                    .average().orElse(0);
            double avgKruskalOps = categoryResults.stream()
                    .mapToDouble(r -> r.getKruskalResult().operationsCount)
                    .average().orElse(0);

            writer.write(String.format("%s Graphs (%d):\n", category, categoryResults.size()));
            writer.write(String.format("  Prim:     %.3f ms avg, %.0f ops avg\n", avgPrimTime, avgPrimOps));
            writer.write(String.format("  Kruskal:  %.3f ms avg, %.0f ops avg\n", avgKruskalTime, avgKruskalOps));
            writer.write(String.format("  Speedup:  %.1f%%\n\n",
                    ((avgPrimTime - avgKruskalTime) / avgPrimTime) * 100));
        }

        writer.close();
        System.out.println("Performance report saved: " + filePath);
    }
}
