package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.common.LottoRule;
import lotto.exception.ErrorMessage;

public class InputView {
    public int readLottoPurchaseAmount() {
        System.out.println(Prompt.INPUT_LOTTO_PURCHASE_AMOUNT.Question());
        String input = Console.readLine();

        return parseLottoPurchaseAmount(input);
    }

    public List<Integer> readWinningNumbers() {
        System.out.println(Prompt.INPUT_WINNING_NUMBERS.Question());
        String input = Console.readLine();

        return parseWinningNumbers(input);
    }

    int parseLottoPurchaseAmount(String input) {
        try {
            int lottoPurchaseAmount = Integer.parseInt(input.trim());

            validateLottoPurchaseAmount(lottoPurchaseAmount);

            return lottoPurchaseAmount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }

    List<Integer> parseWinningNumbers(String input) {
        try {
            List<Integer> winningNumbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .toList();

            validateWinningNumbers(winningNumbers);

            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }

    private void validateLottoPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
        }

        if (purchaseAmount % LottoRule.LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
        }
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.stream().distinct().toList().size() != LottoRule.WINNING_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_LENGTH.Message());
        }

        if (winningNumbers.stream()
                .anyMatch(number -> number < LottoRule.WINNING_NUMBERS_MIN_VALUE
                        || number > LottoRule.WINNING_NUMBERS_MAX_VALUE)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_RANGE.Message());
        }
    }
}
