package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoFactory {

    public List<Lotto> createLottos(Money money) {
        int amount = money.amount();
        int count = amount / Money.UNIT;

        List<Lotto> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> randomUniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            results.add(Lotto.of(randomUniqueNumbers));
        }
        return results;
    }
}
