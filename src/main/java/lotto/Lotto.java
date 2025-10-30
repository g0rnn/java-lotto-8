package lotto;

import java.util.Collection;
import java.util.Set;

public class Lotto {
    private final Set<Integer> numbers;

    public Lotto(Collection<Integer> numbers) {
        Set<Integer> distinctNumbers = Set.copyOf(numbers);
        validate(numbers, distinctNumbers);
        this.numbers = distinctNumbers;
    }

    private void validate(Collection<Integer> numbers, Set<Integer> distinct) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_SIZE.getMessage());
        }

        if (numbers.size() != distinct.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
