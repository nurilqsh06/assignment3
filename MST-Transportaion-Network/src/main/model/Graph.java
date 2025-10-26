package main.model;

import java.util.*;

public class Graph {
    @JsonProperty("id")
    public String id;

    @JsonProperty("nodes")
    public List<String> nodes;

    @JsonProperty("edges")
    public List<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public Graph(String id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes != null ? new ArrayList<>(nodes) : new ArrayList<>();
        this.edges = edges != null ? new ArrayList<>(edges) : new ArrayList<>();
    }

    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }

    @Override
    public String toString() {
        return String.format("Graph{id='%s', nodes=%d, edges=%d}", id, nodes.size(), edges.size());
    }
}
