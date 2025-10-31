package lotto.domain;

import lotto.common.LottoRule;
import lotto.exception.ErrorMessage;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        validate(number);
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_BONUS_NUMBER.Message());
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number) {
        if (number < LottoRule.LOTTO_NUMBER_MIN_VALUE
                || number > LottoRule.LOTTO_NUMBER_MAX_VALUE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER_RANGE.Message());
        }
    }
}
