package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorMessage;

public class InputView {

    private static int LOTTO_PRICE_UNIT = 1000;

    public int readLottoPurchaseAmount() {
        String input = Console.readLine();

        return parseLottoPurchaseAmount(input);
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

    private void validateLottoPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
        }

        if (purchaseAmount % LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
        }
    }
}
