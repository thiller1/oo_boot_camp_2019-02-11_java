/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private final List<Node> neighbors = new ArrayList<>();

    public Node to(final Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return canReach(destination, noVisitedNodes());
    }

    private boolean canReach(Node destination, List<Node> visitedNodes) {
        if (this == destination) return true;
        if (visitedNodes.contains(this)) return false;
        visitedNodes.add(this);
        for (Node n: neighbors) if (n.canReach(destination, visitedNodes)) return true;
        return false;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
