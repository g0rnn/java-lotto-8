package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    @Test
    void 입금_금액에_맞게_로또를_발급한다() {
        int amount = 1000;
        Money money = new Money(amount);
        LottoFactory lottoFactory = new LottoFactory();

        List<Lotto> lottos = lottoFactory.createLottos(money);
        int expected = amount / Money.UNIT;

        assertThat(lottos).hasSize(expected);
    }
}
