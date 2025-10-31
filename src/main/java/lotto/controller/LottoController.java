package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;

public class LottoController {
    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        requestLottoPurchaseAmount();

        WinningNumbers winningNumbers = requestWinningNumbers();
        requestBonusNumber(winningNumbers);
    }

    private LottoPurchaseAmount requestLottoPurchaseAmount() {
        while (true) {
            try {
                int lottoPurchaseAmount = inputView.readLottoPurchaseAmount();
                return new LottoPurchaseAmount(lottoPurchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers requestWinningNumbers() {
        while (true) {
            try {
                List<Integer> winningNumbers = inputView.readWinningNumbers();
                return new WinningNumbers(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber requestBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                int bonusNumber = inputView.readBonusNumber();
                return new BonusNumber(bonusNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
