/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Test;
import probability.Chance;
import quantity.Quantity;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Quantity operates correctly
public class QuantityTest {

    @Test void equalityOfLikeUnits() {
        assertEquals(new Quantity(4, Quantity.TABLESPOON), new Quantity(4, Quantity.TABLESPOON));
        assertNotEquals(new Quantity(4, Quantity.TABLESPOON), new Quantity(6, Quantity.TABLESPOON));
        assertNotEquals(new Quantity(4, Quantity.TABLESPOON), new Object());
        assertNotEquals(new Quantity(4, Quantity.TABLESPOON), null);
    }

    @Test void equalityOfDifferentUnits() {
        assertNotEquals(new Quantity(4, Quantity.TABLESPOON), new Quantity(4, Quantity.TEASPOON));
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(new Quantity(4, Quantity.TABLESPOON))).contains(new Quantity(4, Quantity.TABLESPOON)));
        assertEquals(1, new HashSet<>(Arrays.asList(new Quantity(4, Quantity.TABLESPOON), new Quantity(4, Quantity.TABLESPOON))).size());
    }

    @Test void hash() {
        assertEquals(new Quantity(4, Quantity.TABLESPOON).hashCode(), new Quantity(4, Quantity.TABLESPOON).hashCode());
    }

}
