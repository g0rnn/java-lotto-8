package lotto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final Set<LottoNumber> numbers;

    public Lotto(Collection<LottoNumber> lottoNumbers) {
        Set<LottoNumber> distinctNumbers = Set.copyOf(lottoNumbers);
        validate(lottoNumbers, distinctNumbers);
        this.numbers = distinctNumbers;
    }

    public Set<LottoNumber> getNumbers() {
        return Set.copyOf(numbers);
    }

    public static Lotto of(Collection<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
        return new Lotto(lottoNumbers);
    }

    private void validate(Collection<LottoNumber> numbers, Set<LottoNumber> distinct) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_SIZE.getMessage());
        }

        if (numbers.size() != distinct.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }
}
