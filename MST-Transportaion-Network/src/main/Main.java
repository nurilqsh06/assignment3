package main;

import java.util.*;

import main.algoritm.KruskalAlgorithm;
import main.algoritm.PrimAlgorithm;
import main.model.Graph;
import main.model.GraphResult;
import main.model.InputData;
import main.model.MSTResult;
import main.util.DataGenerator;
import main.util.JsonFileProcessor;
import main.util.PerformanceAnalyzer;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== City Transportation Network Optimization ===");
        System.out.println("Minimum Spanning Tree Algorithms Analysis\n");

        try {
            System.out.println("Generating test datasets...");
            InputData inputData = DataGenerator.generateAllDatasets();

            JsonFileProcessor.saveInputData(inputData, "inputs/ass_3_input.json");

            PrimAlgorithm prim = new PrimAlgorithm();
            KruskalAlgorithm kruskal = new KruskalAlgorithm();

            List<Graph> allGraphs = DataGenerator.getAllTestGraphs();
            List<GraphResult> results = new ArrayList<>();

            System.out.println("Running Algorithm Analysis...");
            System.out.println("=".repeat(70));

            int graphCount = 0;
            for (Graph graph : allGraphs) {
                graphCount++;
                String category = getCategory(graph.nodes.size());

                System.out.printf("Analyzing Graph %2d: %s (V=%d, E=%d)%n",
                        graphCount, category, graph.nodes.size(), graph.edges.size());

                MSTResult primResult = prim.findMST(graph);
                MSTResult kruskalResult = kruskal.findMST(graph);

                boolean costsMatch = primResult.totalCost == kruskalResult.totalCost;
                boolean validMST = primResult.mstEdges.size() == graph.nodes.size() - 1;

                GraphResult graphResult = new GraphResult(
                        graphCount, category, graph.nodes.size(), graph.edges.size(),
                        primResult, kruskalResult, costsMatch, validMST
                );

                results.add(graphResult);

                System.out.printf("  Prim: %.3fms, %d ops, cost=%d%n",
                        primResult.executionTimeMs, primResult.operationsCount, primResult.totalCost);
                System.out.printf("  Kruskal: %.3fms, %d ops, cost=%d%n",
                        kruskalResult.executionTimeMs, kruskalResult.operationsCount, kruskalResult.totalCost);
                System.out.printf("  Validation: Costs Match=%s, Valid MST=%s%n%n",
                        costsMatch ? "YES" : "NO", validMST ? "YES" : "NO");
            }

            JsonFileProcessor.saveResults(results, "outputs/output.json");
            JsonFileProcessor.saveCSVComparison(results, "outputs/comparison.csv");
            PerformanceAnalyzer.generateReport(results, "outputs/performance_analysis.csv");

            generateFinalReport(results);

        } catch (Exception e) {
            System.err.println("Error during analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getCategory(int vertices) {
        if (vertices <= 6) return "Small";
        if (vertices <= 15) return "Medium";
        return "Large";
    }

    private static void generateFinalReport(List<GraphResult> results) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ANALYSIS COMPLETED SUCCESSFULLY!");
        System.out.println("=".repeat(70));

        System.out.println("GENERATED FILES:");
        System.out.println("  inputs/ass_3_input.json - 28 test graphs");
        System.out.println("  outputs/output.json - Complete results");
        System.out.println("  outputs/comparison.csv - Performance comparison table");
        System.out.println("  outputs/performance_analysis.csv - Detailed analysis");

        long correctGraphs = results.stream()
                .filter(r -> r.isCostsMatch() && r.isValidMST())
                .count();

        System.out.printf("\n SUMMARY:%n");
        System.out.printf("  Graphs Analyzed: %d%n", results.size());
        System.out.printf("  Correct Results: %d/%d (%.1f%%)%n",
                correctGraphs, results.size(), (correctGraphs * 100.0) / results.size());

        Map<String, List<GraphResult>> byCategory = new HashMap<>();
        for (GraphResult result : results) {
            byCategory.computeIfAbsent(result.getCategory(), k -> new ArrayList<>()).add(result);
        }

        System.out.println("\n PERFORMANCE BY CATEGORY:");
        for (Map.Entry<String, List<GraphResult>> entry : byCategory.entrySet()) {
            String category = entry.getKey();
            List<GraphResult> categoryResults = entry.getValue();

            double avgPrimTime = categoryResults.stream()
                    .mapToDouble(r -> r.getPrimResult().executionTimeMs)
                    .average().orElse(0);
            double avgKruskalTime = categoryResults.stream()
                    .mapToDouble(r -> r.getKruskalResult().executionTimeMs)
                    .average().orElse(0);

            String fasterAlgo = avgPrimTime < avgKruskalTime ? "Prim" : "Kruskal";
            double speedup = Math.abs(avgPrimTime - avgKruskalTime) / Math.max(avgPrimTime, avgKruskalTime) * 100;

            System.out.printf("  %s: %s is %.1f%% faster%n", category, fasterAlgo, speedup);
        }
    }
}
