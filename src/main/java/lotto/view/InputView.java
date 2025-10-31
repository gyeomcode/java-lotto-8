package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.exception.ErrorMessage;

public class InputView {
    public LottoPurchaseAmount readLottoPurchaseAmount() {
        System.out.println(Prompt.INPUT_LOTTO_PURCHASE_AMOUNT.Question());
        String input = Console.readLine();

        int lottoPurchaseAmount = parseLottoPurchaseAmount(input);

        return new LottoPurchaseAmount(lottoPurchaseAmount);
    }

    public WinningNumbers readWinningNumbers() {
        System.out.println(Prompt.INPUT_WINNING_NUMBERS.Question());
        String input = Console.readLine();

        List<Integer> winningNumbers = parseWinningNumbers(input);

        return new WinningNumbers(winningNumbers);
    }

    int parseLottoPurchaseAmount(String input) {
        try {
            int lottoPurchaseAmount = Integer.parseInt(input.trim());

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

            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }
}
