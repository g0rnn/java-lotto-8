package lotto.purchase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    static LottoFactory lottoFactory;

    @BeforeAll
    static void setup() {
        lottoFactory = new LottoFactory();
    }

    @Test
    void 입금_금액에_맞게_로또를_발급한다() {
        int amount = 1000;
        Money money = new Money(amount);

        List<Lotto> lottos = lottoFactory.createLottos(money);
        int expected = amount / Money.UNIT;

        assertThat(lottos).hasSize(expected);
    }

    @Test
    void 당첨_로또를_발급한다() {
        assertThat(lottoFactory.createWinningLotto())
                .isInstanceOf(WinningLotto.class);
    }
}
