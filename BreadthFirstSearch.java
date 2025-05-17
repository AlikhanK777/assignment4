import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    private Set<Vertex<T>> marked = new HashSet<>();
    private Vertex<T> source;

    public BreadthFirstSearch(WeightedGraph<T> graph, Vertex<T> source) {
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, Vertex<T> source) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            for (Edge<T> edge : graph.getEdges(v)) {
                Vertex<T> w = edge.getDestination();
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public List<Vertex<T>> pathTo(Vertex<T> destination) {
        if (!hasPathTo(destination)) return Collections.emptyList();

        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = destination; x != source; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }

    @Override
    public boolean hasPathTo(Vertex<T> destination) {
        return marked.contains(destination);
    }
}