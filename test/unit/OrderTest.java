/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import order.Orderable;
import org.junit.jupiter.api.Test;
import probability.Chance;
import quantity.Unit;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.*;
import static quantity.Unit.*;

// Ensures Orderable interface operates correctly
class OrderTest {

    @Test void rectangleWithLargestArea() {
        assertEquals(24, (Orderable.best(
                new Rectangle(2, 3), new Rectangle(4, 6), Rectangle.square(3)))
                .area());
        assertNull(Orderable.best());
    }

    @Test void mostLikelyChance() {
        assertEquals(new Chance(0.75), Orderable.best(
                new Chance(0.5), new Chance(0.75), new Chance(0.25)
        ));
    }

    @Test void testMaxQuantity() {
        assertEquals(QUART.s(2), Orderable.best(
                GALLON.s(0.2), OUNCE.s(24), GALLON.s(0.5), CUP.s(7)
        ));
        assertEquals(CELSIUS.s(100), Orderable.best(
                FAHRENHEIT.s(212), CELSIUS.s(0), FAHRENHEIT.s(50), CELSIUS.s(-40)
        ));
    }
}
