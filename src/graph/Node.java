/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private static final int UNREACHABLE = -1;
    private final List<Node> neighbors = new ArrayList<>();

    public Node to(final Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination, noVisitedNodes()) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        var result = this.hopCount(destination, noVisitedNodes());
        if (result == UNREACHABLE) throw new IllegalArgumentException("Unreachable destination");
        return result;
    }

    private int hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        visitedNodes.add(this);
        for (Node n: neighbors) {
            var neighborHopCount = n.hopCount(destination, visitedNodes);
            if (neighborHopCount != UNREACHABLE) return neighborHopCount + 1;
        }
        return UNREACHABLE;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
