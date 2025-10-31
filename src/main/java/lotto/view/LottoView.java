package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public interface LottoView {
    int readLottoPurchaseAmount();

    List<Integer> readWinningNumbers();

    int readBonusNumber();

    void printIssuedLotto(List<Lotto> issuedLotto);
}
