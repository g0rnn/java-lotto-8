package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AggregatorTest {

    private final LottoFactory lottoFactory = new LottoFactory();
    private Aggregator aggregator;

    @BeforeEach
    void setup() {
        List<Integer> actualWinningNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertRandomUniqueNumbersInRangeTest(() -> {
            WinningLotto winningLotto = lottoFactory.createWinningLotto();
            this.aggregator = new Aggregator(winningLotto);
        }, actualWinningNumbers);
    }

    @Test
    void 당첨_로또와_발행된_로또를_비교하여_결과를_공개한다() {
        Money money = new Money(10_000);
        Money expected = new Money(50_000);
        List<Integer> actualLottoNumbers = List.of(11, 12, 13, 4, 5, 6);

        assertRandomUniqueNumbersInRangeTest(() -> {
            List<Lotto> lottos = lottoFactory.createLottos(money);
            LottoReport report = aggregator.aggregate(lottos, money);

            assertThat(report.rateOfReturn()).matches("[0-9]+\\.[0-9]");
            assertThat(report.result().calculateTotalProfit()).isEqualTo(expected);
        }, actualLottoNumbers);
    }
}
