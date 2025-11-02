package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public class LottoResultRequest {

    private final int lottoPurchaseAmount;
    private final int bonusNumber;
    private final List<Lotto> issuedLotto;
    private final List<Integer> winningNumbers;

    public LottoResultRequest(int lottoPurchaseAmount, int bonusNumber, List<Lotto> issuedLotto,
                              List<Integer> winningNumbers) {
        this.lottoPurchaseAmount = lottoPurchaseAmount;
        this.bonusNumber = bonusNumber;
        this.issuedLotto = issuedLotto;
        this.winningNumbers = winningNumbers;
    }

    public int getLottoPurchaseAmount() {
        return lottoPurchaseAmount;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Lotto> getIssuedLotto() {
        return issuedLotto;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
