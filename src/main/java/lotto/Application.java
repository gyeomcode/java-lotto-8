package lotto;

import lotto.domain.BonusNumber;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.parser.LottoInputParser;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new LottoInputParser());

        new LottoPurchaseAmount(inputView.readLottoPurchaseAmount());
        WinningNumbers winningNumbers = new WinningNumbers(inputView.readWinningNumbers());
        new BonusNumber(inputView.readBonusNumber(), winningNumbers);
    }
}
