package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.common.LottoPrize;
import lotto.domain.Lotto;

public interface LottoView {
    int readLottoPurchaseAmount();

    List<Integer> readWinningNumbers();

    int readBonusNumber();

    void printIssuedLotto(List<Lotto> issuedLotto);

    void printWinningResult(Map<LottoPrize, Integer> winningResult);
}
