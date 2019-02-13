/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package order;

import rectangle.Rectangle;

import java.util.Arrays;

// Understands sequencing of like elements
public interface Orderable<T extends Orderable> {

    boolean isBetterThan(T other);

    static <S extends Orderable<S>> S best(S... candidates) {
        return Arrays.stream(candidates)
                .reduce((left, right) -> left.isBetterThan(right) ? left : right)
                .orElse(null);
    }
}
