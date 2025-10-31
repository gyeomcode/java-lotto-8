package lotto.service;

import java.util.List;
import java.util.stream.IntStream;
import lotto.common.LottoRule;
import lotto.domain.Lotto;
import lotto.generator.LottoNumberGenerator;

public class LottoServiceImpl implements LottoService {

    @Override
    public List<Lotto> issueLotto(int amount, LottoNumberGenerator lottoNumberGenerator) {
        int lottoCount = getLottoCount(amount);

        return IntStream.range(0, lottoCount)
                .mapToObj(i -> new Lotto(lottoNumberGenerator.generate()))
                .toList();
    }

    private int getLottoCount(int amount) {
        return amount / LottoRule.LOTTO_PRICE_UNIT;
    }
}
