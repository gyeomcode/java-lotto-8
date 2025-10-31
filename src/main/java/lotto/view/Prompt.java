package lotto.view;

public enum Prompt {
    INPUT_LOTTO_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    OUTPUT_LOTTO_COUNT_SUFFIX("개를 구매했습니다."),
    OUTPUT_WINNING_RESULT_TITLE("당첨 통계\n---"),
    OUTPUT_WINNING_RESULT_FORMAT("%d개 일치 (%s원) - %d개"),
    OUTPUT_PROFIT_RATE_FORMAT("총 수익률은 %.1f%%입니다.");

    private final String text;

    Prompt(String text) {
        this.text = text;
    }

    public String Text() {
        return text;
    }
}
