/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

// Understands a particular measurement
public class RatioQuantity {
    private final double amount;
    private final Unit unit;

    RatioQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
                other != null && other.getClass() == RatioQuantity.class && this.equals((RatioQuantity) other);
    }

    private boolean equals(RatioQuantity other) {
        return this.isCompatible(other) && this.amount == convertedAmount(other);
    }

    private boolean isCompatible(RatioQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    private double convertedAmount(RatioQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    public RatioQuantity plus(RatioQuantity other) {
        return new RatioQuantity(this.amount + convertedAmount(other), this.unit);
    }

    public RatioQuantity negate() {
        return new RatioQuantity(-amount, unit);
    }

    public RatioQuantity minus(RatioQuantity other) {
        return this.plus(other.negate());
    }
}
