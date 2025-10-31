package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.common.LottoPrize;
import lotto.domain.Lotto;
import lotto.generator.LottoNumberGenerator;

public interface LottoService {
    List<Lotto> issueLotto(int amount, LottoNumberGenerator lottoNumberGenerator);

    Map<LottoPrize, Integer> calculateWinningResult(
            List<Lotto> issuedLotto,
            List<Integer> winningNumbers,
            int bonusNumber
    );
}
