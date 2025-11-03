package lotto;

import java.util.List;
import java.util.function.Supplier;
import lotto.console.ConsoleView;
import lotto.purchase.Lotto;
import lotto.purchase.LottoFactory;
import lotto.purchase.LottoNumber;
import lotto.purchase.LottoReceipt;
import lotto.purchase.Money;
import lotto.purchase.WinningLotto;
import lotto.statistics.Aggregator;
import lotto.statistics.LottoReport;

public class LottoController {

    private final ConsoleView consoleView;

    public LottoController(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void run() {
        LottoReceipt receipt = purchaseLotto();
        printPublishedLotto(receipt.issuedLotto());

        WinningLotto winningLotto = raffleWinningLotto();
        printStatistics(winningLotto, receipt.issuedLotto(), receipt.price());
    }

    private LottoReceipt purchaseLotto() {
        return retryOnException(() -> {
            long amount = consoleView.readAmount();
            Money price = new Money(amount);
            List<Lotto> lottos = LottoFactory.createLottos(price);

            return new LottoReceipt(price, lottos);
        });
    }

    private WinningLotto raffleWinningLotto() {
        Lotto winning = retryOnException(() -> {
            List<Integer> numbers = consoleView.readWinningNumbers();
            return Lotto.of(numbers);
        });

        return retryOnException(() -> {
            Integer bonus = consoleView.readBonusNumber();
            LottoNumber bonusNumber = new LottoNumber(bonus);
            return LottoFactory.createWinningLotto(winning, bonusNumber);
        });
    }

    private void printPublishedLotto(List<Lotto> lottos) {
        int size = lottos.size();
        String publishedLotto = getPublishedLotto(lottos);
        consoleView.printLottos(size, publishedLotto);
    }

    private void printStatistics(WinningLotto winningLotto, List<Lotto> lottos, Money money) {
        Aggregator aggregator = new Aggregator(winningLotto);
        LottoReport report = aggregator.aggregate(lottos, money);
        consoleView.printReport(report.toString());
    }

    private <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getPublishedLotto(List<Lotto> lottos) {
        String[] results = lottos.stream().map(Lotto::toString).toArray(String[]::new);
        return String.join("\n", results);
    }
}
