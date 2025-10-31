package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lotto.purchase.Lotto;
import lotto.purchase.LottoFactory;
import lotto.purchase.Money;
import lotto.purchase.WinningLotto;
import lotto.statistics.Aggregator;
import lotto.statistics.LottoReport;

public class Application {
    public static void main(String[] args) {
        Money money = retryOnException(() -> {
            String input = input("구입금액을 입력해 주세요.");
            long amount = Long.parseLong(input);
            return new Money(amount);
        });

        List<Lotto> lottos = LottoFactory.createLottos(money);

        int size = lottos.size();
        System.out.println("\n" + size + "개를 구매했습니다.");
        lottos.forEach(System.out::println);

        List<Integer> winningNumbers = retryOnException(() -> {
            String input = input("\n당첨 번호를 입력해 주세요.");
            return parseNumbers(input);
        });

        WinningLotto winningLotto = retryOnException(() -> {
            String input = input("\n보너스 번호를 입력해 주세요.");
            Integer bonus = Integer.parseInt(input);
            return LottoFactory.createWinningLotto(winningNumbers, bonus);
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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    private static String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
