package lotto.purchase;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoFactory {

    public LottoFactory() {
    }

    public List<Lotto> createLottos(Money money) {
        long amount = money.amount();
        long count = amount / Money.UNIT;

        List<Lotto> results = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            List<Integer> randomUniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            results.add(Lotto.of(randomUniqueNumbers));
        }
        return results;
    }

    public WinningLotto createWinningLotto() {
        List<Integer> randomUniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 7);
        List<Integer> lottoNumbers = randomUniqueNumbers.subList(0, 6);
        Integer bonusNumber = randomUniqueNumbers.get(6);

        return new WinningLotto(
                Lotto.of(lottoNumbers),
                new LottoNumber(bonusNumber)
        );
    }
}
