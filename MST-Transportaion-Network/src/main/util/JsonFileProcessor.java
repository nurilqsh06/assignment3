package main.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import main.model.GraphResult;
import main.model.InputData;

public class JsonFileProcessor {
    private static void ensureParentDirs(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    public static void saveInputData(InputData inputData, String filePath) {
        File file = new File(filePath);
        ensureParentDirs(file);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            String json = String.format(
                "{\n  \"field1\": \"%s\",\n  \"field2\": \"%s\"\n}",
                inputData.getField1(),
                inputData.getField1()
            );
            writer.print(json);
            System.out.println("INPUT.JSON saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving INPUT.JSON: " + e.getMessage());
        }
    }

    public static void saveResults(List<GraphResult> results, String filePath) {
        File file = new File(filePath);
        ensureParentDirs(file);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"analysis_date\": \"").append(new Date()).append("\",\n");
            json.append("  \"total_graphs\": ").append(results.size()).append(",\n");
            json.append("  \"results\": [\n");

            for (int i = 0; i < results.size(); i++) {
                GraphResult result = results.get(i);
                json.append("    {\n");
                json.append("      \"graph_id\": ").append(result.getGraphId()).append(",\n");
                json.append("      \"category\": \"").append(result.getCategory()).append("\",\n");
                json.append("      \"vertices\": ").append(result.getVertices()).append(",\n");
                json.append("      \"edges\": ").append(result.getEdges()).append(",\n");
                json.append("      \"prim_cost\": ").append(result.getPrimResult().totalCost).append(",\n");
                json.append("      \"kruskal_cost\": ").append(result.getKruskalResult().totalCost).append("\n");
                json.append("    }");

                if (i < results.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }

            json.append("  ]\n");
            json.append("}");

            writer.print(json.toString());
            System.out.println("OUTPUT.JSON saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving OUTPUT.JSON: " + e.getMessage());
        }
    }

    public static void saveCSVComparison(List<GraphResult> results, String filePath) {
        File file = new File(filePath);
        ensureParentDirs(file);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("GraphID,Category,Vertices,Edges,Prim_Cost,Kruskal_Cost");

            for (GraphResult result : results) {
                writer.printf(
                    "%d,%s,%d,%d,%d,%d%n",
                    result.getGraphId(),
                    result.getCategory(),
                    result.getVertices(),
                    result.getEdges(),
                    result.getPrimResult().totalCost,
                    result.getKruskalResult().totalCost
                );
            }

            System.out.println("CSV file saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }
}
