package lotto;

import java.util.List;

public class Aggregator {

    private final WinningLotto winningLotto;

    public Aggregator(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public LottoReport aggregate(List<Lotto> lottos, Money amount) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos) {
            int matched = winningLotto.countMatch(lotto);
            boolean matchedBonus = winningLotto.matchBonus(lotto);

            lottoResult.add(Grade.of(matched, matchedBonus));
        }

        Money profit = lottoResult.calculateTotalProfit();
        String rateOfReturn = profit.divide(amount);

        return new LottoReport(lottoResult, rateOfReturn);
    }

}
