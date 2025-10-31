package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.generator.LottoNumberGenerator;

public interface LottoService {
    List<Lotto> issueLotto(int amount, LottoNumberGenerator lottoNumberGenerator);
}
