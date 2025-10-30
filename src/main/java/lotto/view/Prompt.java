package lotto.view;

public enum Prompt {
    INPUT_LOTTO_PURCHASE_AMOUNT("구입금액을 입력해 주세요.");

    private final String question;

    Prompt(String question) {
        this.question = question;
    }

    public String Question() {
        return question;
    }
}
