package lotto.purchase;

import static lotto.purchase.PurchaseErrorCode.DUPLICATE_LOTTO_NUMBER;

import java.util.HashMap;
import java.util.Map;

public class WinningLotto {

    private final Map<LottoNumber, LottoType> status;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validate(lotto, bonusNumber);
        this.bonusNumber = bonusNumber;
        this.status = new HashMap<>();

        for (int i = 1; i <= 45; i++) {
            status.put(new LottoNumber(i), LottoType.NONE);
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

    public boolean matchBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private void validate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }
}
