/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.List;

// Understands its edges
public class Node {
    private static final double UNREACHABLE = Double.POSITIVE_INFINITY;
    private final List<Edge> edges = new ArrayList<>();

    public boolean canReach(Node destination) {
        return cost(destination, noVisitedNodes(), Edge.FEWEST_HOPS) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        return cost(destination, Edge.FEWEST_HOPS);
    }

    public int cost(Node destination) {
        return cost(destination, Edge.LEAST_COST);
    }

    private int cost(Node destination, Edge.CostStrategy fewestHops) {
        double result = this.cost(destination, noVisitedNodes(), fewestHops);
        if (result == UNREACHABLE) {
            throw new IllegalArgumentException("Unreachable target");
        }
        return (int) result;
    }

    double cost(Node destination, List<Node> visitedNodes, Edge.CostStrategy costStrategy) {
        if (this == destination) {
            return 0;
        }
        if (visitedNodes.contains(this)) {
            return UNREACHABLE;
        }
        return edges.stream()
                .mapToDouble(edge -> edge.cost(destination, copyWithThis(visitedNodes), costStrategy))
                .min()
                .orElse(UNREACHABLE);
    }

    public Path path(Node destination) {
        Path path = path(destination, new Path());
        if (path == Path.NOT_REACHED) {
            throw new IllegalArgumentException("Unreachable target");
        }
        return path;
    }


    Path path(Node destination, Path currentPath) {
        if (this == destination) {
            return copyWithThis(currentPath);
        }
        if (currentPath.hasNode(this)) {
            return Path.NOT_REACHED;
        }
        return edges.stream()
                .map(edge -> edge.path(destination, copyWithThis(currentPath)))
                .filter(path -> path != Path.NOT_REACHED)
                .min(Path::isBetterThan)
                .orElse(Path.NOT_REACHED);
    }

    private Path copyWithThis(Path currentPath) {
        return new Path(currentPath, this);
    }



    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }

    public EdgeBuilder cost(int cost) {
        return new EdgeBuilder(cost, this.edges);
    }

    public static final class EdgeBuilder {

        private final int cost;
        private final List<Edge> edges;

        private EdgeBuilder(int cost, List<Edge> edges) {
            this.cost = cost;
            this.edges = edges;
        }

        public Node to(Node destination) {
            edges.add(new Edge(destination, cost));
            return destination;
        }

    }
}
