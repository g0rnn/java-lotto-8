package lotto.purchase;

public enum PurchaseErrorCode {

    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1 이상 45 이하여야 합니다."),
    NEGATIVE_AMOUNT_NOT_ALLOWED("구매 금액은 0 이상이어햐 합니다."),
    INVALID_MONEY_UNIT("돈은 1000원 단위여야 합니다."),
    EXCEED_PURCHASE_LIMIT("하루 최대 구매 한도를 초과하였습니다.")
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    PurchaseErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
