package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.generator.RandomLottoNumberGenerator;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {
    private final LottoView lottoView;
    private final LottoService lottoService;

    public LottoController(LottoView lottoView, LottoService lottoService) {
        this.lottoView = lottoView;
        this.lottoService = lottoService;
    }

    public void run() {
        LottoPurchaseAmount lottoPurchaseAmount = requestLottoPurchaseAmount();
        List<Lotto> issuedLotto = lottoService
                .issueLotto(lottoPurchaseAmount.getAmount(), new RandomLottoNumberGenerator());
        lottoView.printIssuedLotto(issuedLotto);

        WinningNumbers winningNumbers = requestWinningNumbers();
        requestBonusNumber(winningNumbers);
    }

    private LottoPurchaseAmount requestLottoPurchaseAmount() {
        while (true) {
            try {
                int lottoPurchaseAmount = lottoView.readLottoPurchaseAmount();
                return new LottoPurchaseAmount(lottoPurchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers requestWinningNumbers() {
        while (true) {
            try {
                List<Integer> winningNumbers = lottoView.readWinningNumbers();
                return new WinningNumbers(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber requestBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                int bonusNumber = lottoView.readBonusNumber();
                return new BonusNumber(bonusNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
