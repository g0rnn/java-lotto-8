package lotto.purchase;

import static lotto.purchase.PurchaseErrorCode.EXCEED_PURCHASE_LIMIT;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoFactory {

    private LottoFactory() {
    }

    public static List<Lotto> createLottos(Money money) {
        long amount = money.amount();
        long count = amount / Money.UNIT;
        validateCount(count);

        List<Lotto> results = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            List<Integer> randomUniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            results.add(Lotto.of(randomUniqueNumbers));
        }
        return results;
    }

    public static WinningLotto createWinningLotto() {
        List<Integer> randomUniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 7);
        List<Integer> lottoNumbers = randomUniqueNumbers.subList(0, 6);
        Integer bonusNumber = randomUniqueNumbers.get(6);

        return new WinningLotto(
                Lotto.of(lottoNumbers),
                new LottoNumber(bonusNumber)
        );
    }

    public static WinningLotto createWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        return new WinningLotto(
                winningLotto,
                bonusNumber
        );
    }

    private static void validateCount(long count) {
        if (count > 10_000) {
            throw new IllegalArgumentException(EXCEED_PURCHASE_LIMIT.getMessage());
        }
    }
}
