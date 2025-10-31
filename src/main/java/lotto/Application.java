package lotto;

import java.util.List;
import java.util.function.Supplier;
import lotto.console.ConsoleView;
import lotto.purchase.Lotto;
import lotto.purchase.LottoFactory;
import lotto.purchase.LottoNumber;
import lotto.purchase.Money;
import lotto.purchase.WinningLotto;
import lotto.statistics.Aggregator;
import lotto.statistics.LottoReport;

public class Application {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();

        Money money = retryOnException(() -> {
            long amount = consoleView.readAmount();
            return new Money(amount);
        });

        List<Lotto> lottos = LottoFactory.createLottos(money);

        int size = lottos.size();
        System.out.println("\n" + size + "개를 구매했습니다.");
        lottos.forEach(System.out::println);

        Lotto winning = retryOnException(() -> {
            List<Integer> numbers = consoleView.readWinningNumbers();
            return Lotto.of(numbers);
        });

        WinningLotto winningLotto = retryOnException(() -> {
            Integer bonus = consoleView.readBonusNumber();
            LottoNumber bonusNumber = new LottoNumber(bonus);
            return LottoFactory.createWinningLotto(winning, bonusNumber);
        });

        Aggregator aggregator = new Aggregator(winningLotto);
        LottoReport report = aggregator.aggregate(lottos, money);

        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println(report.toString());
    }

    private static <T> T retryOnException(Supplier<T> supplier) {
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
}
