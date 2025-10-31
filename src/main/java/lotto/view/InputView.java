package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.parser.InputParser;

public class InputView {
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public int readLottoPurchaseAmount() {
        System.out.println();
        System.out.println(Prompt.INPUT_LOTTO_PURCHASE_AMOUNT.Text());
        String input = Console.readLine();

        int lottoPurchaseAmount = inputParser.parseToInt(input);

        return lottoPurchaseAmount;
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(Prompt.INPUT_WINNING_NUMBERS.Text());
        String input = Console.readLine();

        List<Integer> winningNumbers = inputParser.parseToIntegerList(input);

        return winningNumbers;
    }

    public int readBonusNumber() {
        System.out.println();
        System.out.println(Prompt.INPUT_BONUS_NUMBER.Text());
        String input = Console.readLine();

        int bonusNumber = inputParser.parseToInt(input);

        return bonusNumber;
    }
}
