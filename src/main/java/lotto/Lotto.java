package lotto;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(Collection<LottoNumber> lottoNumbers) {
        SortedSet<LottoNumber> distinctNumbers = new TreeSet<>(lottoNumbers);
        validate(lottoNumbers, distinctNumbers);
        this.numbers = Collections.unmodifiableSortedSet(distinctNumbers);
    }

    public Set<LottoNumber> getNumbers() {
        return Set.copyOf(numbers);
    }

    public static Lotto of(Collection<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        return new Lotto(lottoNumbers);
    }

    private void validate(Collection<LottoNumber> numbers, Set<LottoNumber> distinct) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_SIZE.getMessage());
        }

        if (numbers.size() != distinct.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }
}
