package tests;


import java.util.*;

import main.algoritm.KruskalAlgorithm;
import main.algoritm.PrimAlgorithm;
import main.model.Edge;
import main.model.Graph;
import main.model.MSTResult;

public class MSTAlgorithmTest {

    @Test
    public void testSameMSTCost() {
        // Create test graph
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
        );
        Graph graph = new Graph("test1", nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        MSTResult primResult = prim.findMST(graph);
        MSTResult kruskalResult = kruskal.findMST(graph);

        assertEquals(primResult.totalCost, kruskalResult.totalCost);
        assertEquals(6, primResult.totalCost);
    }

    @Test
    public void testMSTHasVMinus1Edges() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
        );
        Graph graph = new Graph("test2", nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(graph);

        assertEquals(nodes.size() - 1, result.mstEdges.size());
    }

    @Test
    public void testPerformanceMetrics() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("A", "C", 3)
        );
        Graph graph = new Graph("test3", nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(graph);

        assertTrue(result.executionTimeMs >= 0);
                assertTrue(result.operationsCount > 0);
                assertEquals(3, result.totalCost);
                    }
                
                    private void assertTrue(boolean b) {
                throw new UnsupportedOperationException("Unimplemented method 'assertTrue'");
            }
        
                    private void assertEquals(int i, int totalCost) {
                throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
            }
        
            @Test
    public void testSingleVertex() {
        List<String> nodes = Collections.singletonList("A");
        List<Edge> edges = Collections.emptyList();
        Graph graph = new Graph("test4", nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        MSTResult primResult = prim.findMST(graph);
        MSTResult kruskalResult = kruskal.findMST(graph);

        assertEquals(0, primResult.totalCost);
        assertEquals(0, kruskalResult.totalCost);
        assertEquals(0, primResult.mstEdges.size());
        assertEquals(0, kruskalResult.mstEdges.size());
    }

    @Test
    public void testMSTIsAcyclic() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("D", "A", 4), 
                new Edge("A", "C", 5)
        );
        Graph graph = new Graph("test5", nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(graph);

        assertEquals(nodes.size() - 1, result.mstEdges.size());
    }
}
