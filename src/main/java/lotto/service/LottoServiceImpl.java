package lotto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lotto.common.LottoPrize;
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

    @Override
    public Map<LottoPrize, Integer> calculateWinningResult(
            List<Lotto> issuedLotto,
            List<Integer> winningNumbers,
            int bonusNumber
    ) {
        Map<LottoPrize, Integer> winningResult = new HashMap<>();

        issuedLotto.forEach(lotto -> {
            int matchCount = lotto.countMatches(winningNumbers);
            boolean bonusMatched = lotto.isBonusMatched(bonusNumber);

            LottoPrize.findPrize(matchCount, bonusMatched).ifPresent(lottoPrize -> {
                winningResult.put(lottoPrize, winningResult.getOrDefault(lottoPrize, 0) + 1);
            });
        });

        return winningResult;
    }

    @Override
    public Double calculateProfitRate(Map<LottoPrize, Integer> winningResult, int lottoPurchaseAmount) {
        int totalPrize = winningResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().Amount() * entry.getValue())
                .sum();
        double profitRate = (double) totalPrize / lottoPurchaseAmount * 100;

        return Math.round(profitRate * 10) / 10.0;
    }

    private int getLottoCount(int amount) {
        return amount / LottoRule.LOTTO_PRICE_UNIT;
    }
}
