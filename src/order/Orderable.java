/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package order;

import rectangle.Rectangle;

public interface Orderable<T extends Orderable> {

    boolean isBetterThan(T other);

    static <S extends Orderable<S>> S best(S... candidates) {
        S champion = null;
        for (S challenger: candidates)
            if (champion == null || challenger.isBetterThan(champion))
                champion = challenger;
        return champion;
    }
}
