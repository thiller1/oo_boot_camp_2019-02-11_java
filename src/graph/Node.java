/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private static final double UNREACHABLE = Double.POSITIVE_INFINITY;
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
        return (int)result;
    }

    private double hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return neighborHopCount(destination, visitedNodes);
    }

    private double neighborHopCount(Node destination, List<Node> visitedNodes) {
        var champion = UNREACHABLE;
        for (Node n: neighbors) {
            var challenger = n.hopCount(destination, copyWithThis(visitedNodes)) + 1;
            if (challenger < champion) champion = challenger;
        }
        return champion;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }

    private List<Node> copyWithThis(List<Node> originals) {
        var results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }
}
