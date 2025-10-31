package lotto.console;

import static lotto.console.ConsoleErrorCode.INVALID_BONUS_NUMBER_TYPE;
import static lotto.console.ConsoleErrorCode.INVALID_MONEY_TYPE;
import static lotto.console.ConsoleErrorCode.INVALID_NUMBERS_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class ConsoleView {

    public ConsoleView() {
    }

    public long readAmount() {
        String input = input("구입금액을 입력해 주세요.");
        validateAmount(input);
        return Long.parseLong(input);
    }

    public List<Integer> readWinningNumbers() {
        String input = input("\n당첨 번호를 입력해 주세요.");
        validateNumbers(input);
        return parseNumbers(input);
    }

    public Integer readBonusNumber() {
        String input = input("\n보너스 번호를 입력해 주세요.");
        validateBonusNumber(input);
        return Integer.parseInt(input);
    }

    private String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    private static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    private void validateAmount(String amount) {
        if (!amount.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_MONEY_TYPE.getMessage());
        }
    }

    private void validateNumbers(String numbers) {
        if (!numbers.matches("\\d+(,\\d+)*")) {
            throw new IllegalArgumentException(INVALID_NUMBERS_FORMAT.getMessage());
        }
    }

    private void validateBonusNumber(String bonus) {
        if (!bonus.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_TYPE.getMessage());
        }
    }
}
