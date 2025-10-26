package main.model;

public class GraphResult {
    private final int graphId;
    private final String category;
    private final int vertices;
    private final int edges;
    private final MSTResult primResult;
    private final MSTResult kruskalResult;
    private final boolean costsMatch;
    private final boolean validMST;

    public GraphResult(int graphId, String category, int vertices, int edges,
                       MSTResult primResult, MSTResult kruskalResult,
                       boolean costsMatch, boolean validMST) {
        this.graphId = graphId;
        this.category = category;
        this.vertices = vertices;
        this.edges = edges;
        this.primResult = primResult;
        this.kruskalResult = kruskalResult;
        this.costsMatch = costsMatch;
        this.validMST = validMST;
    }

    // Getters
    public int getGraphId() { return graphId; }
    public String getCategory() { return category; }
    public int getVertices() { return vertices; }
    public int getEdges() { return edges; }
    public MSTResult getPrimResult() { return primResult; }
    public MSTResult getKruskalResult() { return kruskalResult; }
    public boolean isCostsMatch() { return costsMatch; }
    public boolean isValidMST() { return validMST; }
}