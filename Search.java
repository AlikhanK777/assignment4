import java.util.List;

public interface Search<T> {
    List<Vertex<T>> pathTo(Vertex<T> destination);
    boolean hasPathTo(Vertex<T> destination);
}