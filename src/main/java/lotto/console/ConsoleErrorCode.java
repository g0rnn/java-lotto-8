package lotto.console;

public enum ConsoleErrorCode {

    INVALID_MONEY_TYPE("구매 금액은 숫자가 입력되어야 합니다."),
    INVALID_NUMBERS_FORMAT("당첨 번호는 콤마(,)로 구분되어야 합니다."),
    INVALID_BONUS_NUMBER_TYPE("보너스 번호는 숫자여야 합니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ConsoleErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
