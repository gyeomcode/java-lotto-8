package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.common.LottoPrize;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoIssueRequest;
import lotto.dto.LottoIssueResponse;
import lotto.dto.LottoResultRequest;
import lotto.dto.LottoResultResponse;
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
        LottoIssueResponse lottoIssue = responseLottoIssue(new LottoIssueRequest(lottoPurchaseAmount.getAmount()));

        lottoView.printIssuedLotto(lottoIssue.getIssuedLotto());

        WinningNumbers winningNumbers = requestWinningNumbers();
        BonusNumber bonusNumber = requestBonusNumber(winningNumbers);

        LottoResultResponse lottoResult = responseLottoResult(
                new LottoResultRequest(lottoPurchaseAmount.getAmount(), bonusNumber.getNumber(),
                        lottoIssue.getIssuedLotto(),
                        winningNumbers.getNumbers()));

        lottoView.printWinningResult(lottoResult.getWinningResult());
        lottoView.printProfitRate(lottoResult.getProfitRate());
    }

    private LottoIssueResponse responseLottoIssue(LottoIssueRequest lottoIssueRequest) {
        List<Lotto> issuedLotto = lottoService
                .issueLotto(lottoIssueRequest.getLottoPurchaseAmount(), new RandomLottoNumberGenerator());

        return new LottoIssueResponse(issuedLotto);
    }

    private LottoResultResponse responseLottoResult(LottoResultRequest lottoResultRequest) {
        Map<LottoPrize, Integer> winningResult = lottoService.calculateWinningResult(
                lottoResultRequest.getIssuedLotto(),
                lottoResultRequest.getWinningNumbers(),
                lottoResultRequest.getBonusNumber()
        );
        double profitRate = lottoService.calculateProfitRate(winningResult,
                lottoResultRequest.getLottoPurchaseAmount());

        return new LottoResultResponse(winningResult, profitRate);
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
