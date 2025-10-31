package lotto.domain;

import static lotto.common.LottoRule.LOTTO_NUMBERS_LENGTH;

import java.util.List;
import lotto.common.LottoRule;
import lotto.exception.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_LENGTH.Message());
        }

        if (numbers.stream().distinct().toList().size() != LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_LOTTO.Message());
        }

        if (numbers.stream()
                .anyMatch(number -> number < LottoRule.LOTTO_NUMBER_MIN_VALUE
                        || number > LottoRule.LOTTO_NUMBER_MAX_VALUE)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_RANGE.Message());
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
