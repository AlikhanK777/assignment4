import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create vertices
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");

        // Create graph
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addEdge(v1, v2, 4);
        graph.addEdge(v1, v3, 2);
        graph.addEdge(v2, v4, 5);
        graph.addEdge(v3, v2, 1);
        graph.addEdge(v3, v4, 8);
        graph.addEdge(v3, v5, 10);
        graph.addEdge(v4, v5, 2);
        graph.addEdge(v5, v4, 7);

        // BFS test
        System.out.println("BFS Path from A to E:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, v1);
        printPath(bfs.pathTo(v5));

        // Dijkstra test
        System.out.println("\nDijkstra Shortest Path from A to E:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, v1);
        printPath(dijkstra.pathTo(v5));
        System.out.println("Total distance: " + dijkstra.distTo(v5));
    }

    private static void printPath(List<Vertex<String>> path) {
        if (path.isEmpty()) {
            System.out.println("No path exists");
            return;
        }
        for (Vertex<String> v : path) {
            System.out.print(v.getData() + " ");
        }
        System.out.println();
    }
}