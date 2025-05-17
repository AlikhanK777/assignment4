import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacentVertices;

    public Vertex(T data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<T> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public T getData() {
        return data;
    }

    public Map<Vertex<T>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }
}