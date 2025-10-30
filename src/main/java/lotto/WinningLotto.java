package lotto;

import java.util.HashMap;
import java.util.Map;

public class WinningLotto {

    private final Map<LottoNumber, LottoType> status;

    public WinningLotto(Lotto lotto) {
        this.status = new HashMap<>();
        for (int i = 1; i <= 45; i++) {
            status.put(LottoNumber.from(i), LottoType.NONE);
        }

        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            this.status.put(lottoNumber, LottoType.WINNING);
        }
    }

    public int countMatch(Lotto lotto) {
        int count = 0;

        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            LottoType lottoType = status.get(lottoNumber);
            if (lottoType == LottoType.WINNING) {
                count += 1;
            }
        }

        return count;
    }
}
