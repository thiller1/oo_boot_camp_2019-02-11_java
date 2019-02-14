/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.Collections;
import java.util.List;

// Understands a connnection from one Node to another
class Link {
    interface CostStrategy { double cost(double cost); }
    static final CostStrategy LEAST_COST = cost -> cost;

    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    double hopCount(Node destination, List<Node> visitedNodes) {
        return target.hopCount(destination, visitedNodes) + 1;
    }

    double cost(Node destination, List<Node> visitedNodes, Link.CostStrategy strategy) {
        return target.cost(destination, visitedNodes, strategy) + strategy.cost(cost);
    }
}
