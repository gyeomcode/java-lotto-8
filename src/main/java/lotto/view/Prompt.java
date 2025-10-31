package lotto.view;

public enum Prompt {
    INPUT_LOTTO_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요.");

    private final String text;

    Prompt(String question) {
        this.text = question;
    }

    public String Text() {
        return text;
    }
}
