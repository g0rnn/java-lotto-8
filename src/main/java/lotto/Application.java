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

        // TODO: 출력: 로또 현황

        List<Integer> winningNumbers = retryOnException(() -> {
            String input = input("당첨 번호를 입력해 주세요.");
            return parseNumbers(input);
        });

        Integer bonus = retryOnException(() -> {
            String input = input("보너스 번호를 입력해 주세요.");
            return Integer.parseInt(input);
        });

        WinningLotto winningLotto = LottoFactory.createWinningLotto(winningNumbers, bonus);

        Aggregator aggregator = new Aggregator(winningLotto);
        LottoReport report = aggregator.aggregate(lottos, money);

        // TODO: 출력: 통계 출력
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
