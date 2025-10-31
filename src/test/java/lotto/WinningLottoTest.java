package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private WinningLotto winningLotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        this.bonusNumber = new LottoNumber(7);
        this.winningLotto = new WinningLotto(Lotto.of(List.of(1, 2, 3, 4, 5, 6)), bonusNumber);
    }

    @Test
    void 로또_번호의_당첨_유무를_알려준다() {
        Lotto lotto = Lotto.of(List.of(11, 12, 13, 14, 15, 6));

        int matched = winningLotto.countMatch(lotto);

        assertEquals(1, matched);
    }

    @Test
    void 로또의_보너스_당첨_유무를_알려준다() {
        Lotto lotto = Lotto.of(List.of(11, 12, 13, 14, 15, 7));

        boolean matched = winningLotto.matchBonus(lotto);

        assertTrue(matched);
    }
}
