package lotto;

public enum ErrorCode {

    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1 이상 45 이하여야 합니다."),
    NEGATIVE_AMOUNT_NOT_ALLOWED("돈은 음수일 수 없습니다."),
    INVALID_MONEY_UNIT("돈은 1000원 단위여야 합니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
