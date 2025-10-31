package lotto;

import java.util.List;
import lotto.purchase.Lotto;
import lotto.purchase.LottoFactory;
import lotto.purchase.Money;
import lotto.purchase.WinningLotto;
import lotto.statistics.Aggregator;
import lotto.statistics.LottoReport;

public class Application {
    public static void main(String[] args) {
        LottoFactory lottoFactory = new LottoFactory();

        // 입력: 구입 금액

        int amount = 1000;
        Money money = new Money(amount);
        List<Lotto> lottos = lottoFactory.createLottos(money);

        // 출력: 로또 현황

        // 입력: 당첨 번호 입력
        // 입력: 보너스 번호 입력
        List<Integer> winningNumbers = List.of();
        Integer bonus = 0;
        WinningLotto winningLotto = lottoFactory.createWinningLotto(winningNumbers, bonus);
        Aggregator aggregator = new Aggregator(winningLotto);
        LottoReport report = aggregator.aggregate(lottos, money);

        // 출력: 통계 출력
    }
}
