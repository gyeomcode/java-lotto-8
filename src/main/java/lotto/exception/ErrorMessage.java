package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE("로또 구입 금액은 0보다 커야 합니다."),
    INVALID_LOTTO_PURCHASE_AMOUNT_UNIT("로또 구입 금액은 1,000원 단위여야 합니다."),
    INVALID_WINNING_NUMBERS_LENGTH("당첨 번호의 개수는 중복 없이 6개여야 합니다."),
    INVALID_WINNING_NUMBERS_RANGE("당첨 번호는 1에서 45 사이의 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1에서 45 사이의 숫자여야 합니다."),
    DUPLICATED_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
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
