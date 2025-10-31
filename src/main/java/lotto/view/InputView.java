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

        return inputParser.parseToInt(input);
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(Prompt.INPUT_WINNING_NUMBERS.Text());
        String input = Console.readLine();

        return inputParser.parseToIntegerList(input);
    }

    public int readBonusNumber() {
        System.out.println();
        System.out.println(Prompt.INPUT_BONUS_NUMBER.Text());
        String input = Console.readLine();

        return inputParser.parseToInt(input);
    }
}
