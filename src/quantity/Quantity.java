/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import probability.Chance;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Quantity {
    public static final Object TEASPOON = new Object();
    public static final Object TABLESPOON = new Object();

    private final double amount;
    private final Object unit;

    public Quantity(double amount, Object unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
                other != null && other.getClass() == Quantity.class && this.equals((Quantity) other);
    }

    private boolean equals(Quantity other) {
        return this.amount == other.amount && this.unit == other.unit;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(amount) * 37 + unit.hashCode();
    }
}
