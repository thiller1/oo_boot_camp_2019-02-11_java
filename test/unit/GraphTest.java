/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;
import graph.Node;
import graph.Path;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

// Ensures graph algorithms function correctly
class GraphTest {
    private final static Node A, B, C, D, E, F, G;

    static {
        A = new Node();
        B = new Node();
        C = new Node();
        D = new Node();
        E = new Node();
        F = new Node();
        G = new Node();


        B.cost(5).to(A);
        B.cost(6).to(C).cost(7).to(D).cost(2).to(E).cost(3).to(B).cost(4).to(F);
        C.cost(1).to(D);
        C.cost(8).to(E);
    }

    @Test void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }

    @Test void hopCount() {
        assertEquals(0, B.hopCount(B));
        assertEquals(1, B.hopCount(A));
        assertEquals(1, B.hopCount(F));
        assertEquals(2, B.hopCount(D));
        assertEquals(3, C.hopCount(F));
        assertThrows(IllegalArgumentException.class, () -> G.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> A.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
    }

    @Test void cost() {
        assertEquals(0, B.cost(B));
        assertEquals(5, B.cost(A));
        assertEquals(4, B.cost(F));
        assertEquals(7, B.cost(D));
        assertEquals(10, C.cost(F));
        assertEquals(11, C.cost(A));
        assertEquals(1, C.cost(D));

        assertThrows(IllegalArgumentException.class, () -> G.cost(A));
        assertThrows(IllegalArgumentException.class, () -> F.cost(B));
        assertThrows(IllegalArgumentException.class, () -> A.cost(B));
    }


    @Test void path() {
//        assertEquals(Path.NOT_REACHED, B.path(B));
        Path pathBtoA = B.path(A);
        assertTrue(pathBtoA.hasNode(A));
        assertTrue(pathBtoA.hasNode(B));
        assertEquals(1, pathBtoA.hopCount());
        assertEquals(5, pathBtoA.cost());

        Path pathBtoF = B.path(F);
        assertTrue(pathBtoF.hasNode(B));
        assertTrue(pathBtoF.hasNode(F));
        assertEquals(1, pathBtoF.hopCount());
        assertEquals(4, pathBtoF.cost());


        Path pathBtoD = B.path(D);
        assertTrue(pathBtoD.hasNode(B));
        assertTrue(pathBtoD.hasNode(C));
        assertTrue(pathBtoD.hasNode(D));
        assertEquals(2, pathBtoD.hopCount());
        assertEquals(7, pathBtoD.cost());

        Path pathCtoF = C.path(F);
        assertTrue(pathCtoF.hasNode(C));
        assertTrue(pathCtoF.hasNode(E));
        assertTrue(pathCtoF.hasNode(B));
        assertTrue(pathCtoF.hasNode(F));
        assertEquals(4, pathCtoF.hopCount());
        assertEquals(10, pathCtoF.cost());

        assertThrows(IllegalArgumentException.class, () -> G.path(A));
        assertThrows(IllegalArgumentException.class, () -> F.path(B));
        assertThrows(IllegalArgumentException.class, () -> A.path(B));
    }
}
