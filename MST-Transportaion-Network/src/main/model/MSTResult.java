package main.model;

import java.util.List;

public class MSTResult {
    @JsonProperty("mst_edges")
    public List<Edge> mstEdges;

    @JsonProperty("total_cost")
    public int totalCost;

    @JsonProperty("operations_count")
    public int operationsCount;

    @JsonProperty("execution_time_ms")
    public double executionTimeMs;

    public MSTResult() {}

    public MSTResult(List<Edge> mstEdges, int totalCost, int operationsCount, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }
}
