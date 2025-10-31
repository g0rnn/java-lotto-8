package lotto;

import static lotto.ErrorCode.LOTTO_NUMBER_OUT_OF_RANGE;

import java.util.Objects;

public class LottoNumber {

    private static final Integer MINIMUM_NUMBER = 1;
    private static final Integer MAXIMUM_NUMBER = 45;

    private final Integer number;

    public LottoNumber(Integer number) {
        validate(number);
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    private void validate(Integer number) {
        if (MINIMUM_NUMBER > number || MAXIMUM_NUMBER < number) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
