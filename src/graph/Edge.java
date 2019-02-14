package graph;

import java.util.List;

class Edge {

    interface CostStrategy { double cost(Edge edge); }
    static final CostStrategy LEAST_COST = edge -> edge.weight;
    static final CostStrategy FEWEST_HOPS = ignore -> 1;

    private final Node target;
    private final int weight;

    Edge(Node target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    double cost(Node destination, List<Node> nodes, CostStrategy strategy) {
        return target.cost(destination, nodes, strategy) + strategy.cost(this);
    }

    Path path(Node destination, Path path) {
        return target.path(destination, new Path(path, this));
    }

    double cost(CostStrategy costStrategy) {
        return costStrategy.cost(this);
    }
}
