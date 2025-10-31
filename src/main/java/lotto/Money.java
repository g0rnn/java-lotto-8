package lotto;

import static lotto.ErrorCode.INVALID_MONEY_UNIT;
import static lotto.ErrorCode.POSITIVE_AMOUNT_REQUIRED;

public record Money(int amount) {

    public static final int UNIT = 1000;
    private static final int MINIMUN_MONEY_AMOUNT = 1;

    public Money {
        if (amount < MINIMUN_MONEY_AMOUNT) {
            throw new IllegalArgumentException(POSITIVE_AMOUNT_REQUIRED.getMessage());
        }

        if (amount % UNIT != 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT.getMessage());
        }
    }
}
