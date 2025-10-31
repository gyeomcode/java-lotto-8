package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.generator.RandomLottoNumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;

public class LottoController {
    private final InputView inputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, LottoService lottoService) {
        this.inputView = inputView;
        this.lottoService = lottoService;
    }

    public void run() {
        LottoPurchaseAmount lottoPurchaseAmount = requestLottoPurchaseAmount();
        List<Lotto> issuedLotto = lottoService
                .issueLotto(lottoPurchaseAmount.getAmount(), new RandomLottoNumberGenerator());

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
