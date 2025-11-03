package lotto.purchase;

import java.util.List;

public record LottoReceipt(Money price, List<Lotto> issuedLotto) {
}
