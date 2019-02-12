/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

// Understands a particular measurement
public class RatioQuantity extends IntervalQuantity {

    RatioQuantity(double amount, Unit unit) {
        super(amount, unit);
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
