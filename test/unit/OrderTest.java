/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import order.Orderable;
import org.junit.jupiter.api.Test;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.*;
import static quantity.Unit.*;

// Ensures Orderable interface operates correctly
class OrderTest {

    @Test void rectangleWithLargestArea() {
        assertEquals(24, (Orderable.best(
                new Rectangle(2, 3), new Rectangle(4, 6), Rectangle.square(3)))
                .area());
    }
}
