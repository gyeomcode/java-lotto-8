package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorMessage;

public class InputView {

    private static int LOTTO_PRICE_UNIT = 1000;

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
        List<Integer> winningNumbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();

        return winningNumbers;
    }

    private void validateLottoPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
        }

        if (purchaseAmount % LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
        }
    }
}
