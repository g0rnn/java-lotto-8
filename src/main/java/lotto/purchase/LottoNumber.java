package lotto.purchase;

import static lotto.purchase.PurchaseErrorCode.LOTTO_NUMBER_OUT_OF_RANGE;

public record LottoNumber(Integer number) implements Comparable<LottoNumber> {

    private static final Integer MINIMUM_NUMBER = 1;
    private static final Integer MAXIMUM_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.number.compareTo(other.number);
    }

    @Override
    public String toString() {
        return number.toString();
    }

    private void validate(Integer number) {
        if (MINIMUM_NUMBER > number || MAXIMUM_NUMBER < number) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
