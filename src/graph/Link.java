/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.List;

// Understands a connnection from one Node to another
class Link {
    private final Node target;

    Link(Node target) {
        this.target = target;
    }

    double hopCount(Node destination, List<Node> visitedNodes) {
        return target.hopCount(destination, visitedNodes) + 1;
    }
}
