package lotto.purchase;

import static lotto.purchase.PurchaseErrorCode.INVALID_MONEY_UNIT;
import static lotto.purchase.PurchaseErrorCode.NEGATIVE_AMOUNT_NOT_ALLOWED;

public record Money(long amount) {

    public static final int UNIT = 1000;
    private static final int MINIMUM_MONEY_AMOUNT = 0;

    public Money {
        if (amount < MINIMUM_MONEY_AMOUNT) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_NOT_ALLOWED.getMessage());
        }

        if (amount % UNIT != 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT.getMessage());
        }
    }

    public String rateBasedOn(Money seed) {
        double result = (double) amount / seed.amount * 100.0;
        double rounded = Math.round(result * 10) / 10.0;
        return String.valueOf(rounded);
    }
}
