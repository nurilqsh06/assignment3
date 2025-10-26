package main.model;

import java.util.*;

public class InputData {
    @JsonProperty("small_graphs")
    private List<Graph> smallGraphs;

    @JsonProperty("medium_graphs")
    private List<Graph> mediumGraphs;

    @JsonProperty("large_graphs")
    private List<Graph> largeGraphs;

    @JsonProperty("extra_large_graphs")
    private List<Graph> extraLargeGraphs;

    public InputData() {
        this.smallGraphs = new ArrayList<>();
        this.mediumGraphs = new ArrayList<>();
        this.largeGraphs = new ArrayList<>();
        this.extraLargeGraphs = new ArrayList<>();
    }

    // Getters and Setters
    public List<Graph> getSmallGraphs() { return smallGraphs; }
    public void setSmallGraphs(List<Graph> smallGraphs) { this.smallGraphs = smallGraphs; }

    public List<Graph> getMediumGraphs() { return mediumGraphs; }
    public void setMediumGraphs(List<Graph> mediumGraphs) { this.mediumGraphs = mediumGraphs; }

    public List<Graph> getLargeGraphs() { return largeGraphs; }
    public void setLargeGraphs(List<Graph> largeGraphs) { this.largeGraphs = largeGraphs; }

    public List<Graph> getExtraLargeGraphs() { return extraLargeGraphs; }
    public void setExtraLargeGraphs(List<Graph> extraLargeGraphs) { this.extraLargeGraphs = extraLargeGraphs; }

    public List<Graph> getAllGraphs() {
        List<Graph> allGraphs = new ArrayList<>();
        allGraphs.addAll(smallGraphs);
        allGraphs.addAll(mediumGraphs);
        allGraphs.addAll(largeGraphs);
        allGraphs.addAll(extraLargeGraphs);
        return allGraphs;
    }

    public Object getField1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getField1'");
    }
}
