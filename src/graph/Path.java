package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
    static final Path NOT_REACHED = new Path();

    private final List<Node> nodesInPath;
    private final List<Edge> edgesInPath;

    Path() {
        this.nodesInPath = Collections.emptyList();
        this.edgesInPath = Collections.emptyList();
    }


    Path(Path path, Node node) {
        this.nodesInPath = new ArrayList<>(path.nodesInPath);
        this.edgesInPath = path.edgesInPath;
        this.nodesInPath.add(node);
    }

    Path(Path path, Edge edge) {
        this.nodesInPath = path.nodesInPath;
        this.edgesInPath = new ArrayList<>(path.edgesInPath);
        this.edgesInPath.add(edge);
    }

    public int hopCount() {
        return nodesInPath.size() - 1;
    }

    public int cost() {
        return (int) edgesInPath.stream().mapToDouble(edge -> edge.cost(Edge.LEAST_COST)).sum();
    }

    public boolean hasNode(Node node) {
        return nodesInPath.contains(node);
    }

    int isBetterThan(Path otherPath) {
        return Integer.compare(cost(), otherPath.cost());
    }
}