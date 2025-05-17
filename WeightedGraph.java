import java.util.*;

public class WeightedGraph<T> {
    private Map<Vertex<T>, List<Edge<T>>> adjacencyMap = new HashMap<>();

    public void addVertex(Vertex<T> vertex) {
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination, double weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).add(new Edge<>(source, destination, weight));
        source.addAdjacentVertex(destination, weight);
    }

    public List<Edge<T>> getEdges(Vertex<T> vertex) {
        return adjacencyMap.getOrDefault(vertex, Collections.emptyList());
    }
}