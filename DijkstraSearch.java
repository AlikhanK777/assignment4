import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<Vertex<T>, Double> distTo = new HashMap<>();
    private final Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    private final PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();

    private static class VertexDistance<T> implements Comparable<VertexDistance<T>> {
        final Vertex<T> vertex;
        final double distance;

        VertexDistance(Vertex<T> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> source) {
        for (Vertex<T> v : graph.getEdges(source).stream().map(Edge::getDestination).toList()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        pq.add(new VertexDistance<>(source, 0.0));
        while (!pq.isEmpty()) {
            relax(graph, pq.poll().vertex);
        }
    }

    private void relax(WeightedGraph<T> graph, Vertex<T> v) {
        for (Edge<T> edge : graph.getEdges(v)) {
            Vertex<T> w = edge.getDestination();
            double newDist = distTo.get(v) + edge.getWeight();
            if (newDist < distTo.getOrDefault(w, Double.POSITIVE_INFINITY)) {
                distTo.put(w, newDist);
                edgeTo.put(w, v);
                pq.add(new VertexDistance<>(w, newDist));
            }
        }
    }

    @Override
    public List<Vertex<T>> pathTo(Vertex<T> destination) {
        if (!hasPathTo(destination)) return Collections.emptyList();

        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = destination; x != null && !x.equals(edgeTo.get(x)); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        return path;
    }

    @Override
    public boolean hasPathTo(Vertex<T> destination) {
        return distTo.containsKey(destination) &&
                distTo.get(destination) < Double.POSITIVE_INFINITY;
    }

    public double distTo(Vertex<T> destination) {
        return distTo.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}