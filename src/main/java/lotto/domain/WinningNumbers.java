package lotto.domain;

import java.util.List;
import lotto.common.LottoRule;
import lotto.exception.ErrorMessage;

public class WinningNumbers {
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.stream().distinct().toList().size() != LottoRule.LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_LENGTH.Message());
        }

        if (numbers.stream()
                .anyMatch(number -> number < LottoRule.LOTTO_NUMBER_MIN_VALUE
                        || number > LottoRule.LOTTO_NUMBER_MAX_VALUE)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_RANGE.Message());
        }
    }
}
