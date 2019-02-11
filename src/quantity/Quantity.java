/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

// Understands a particular measurement
public class Quantity {
    private final double amount;
    private final Unit unit;

    Quantity(double amount, Unit unit) {
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