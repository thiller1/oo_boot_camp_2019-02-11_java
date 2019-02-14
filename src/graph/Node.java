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
        return neighborHopCount(destination, visitedNodes);
    }

    private int neighborHopCount(Node destination, List<Node> visitedNodes) {
        visitedNodes.add(this);
        var champion = UNREACHABLE;
        for (Node n: neighbors) {
            var challenger = n.hopCount(destination, visitedNodes);
            if (challenger == UNREACHABLE) continue;
            if (champion == UNREACHABLE || challenger + 1 < champion)
                champion = challenger + 1;
        }
        return champion;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
