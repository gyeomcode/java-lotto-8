package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE("로또 구입 금액은 0보다 커야 합니다."),
    INVALID_LOTTO_PURCHASE_AMOUNT_UNIT("로또 구입 금액은 1,000원 단위여야 합니다."),
    INT_RANGE_EXCEEDED("int로 표현할 수 없는 값입니다.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String Message() {
        return message;
    }
}
