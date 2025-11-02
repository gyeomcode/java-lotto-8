package lotto.dto;

import java.util.Map;
import lotto.common.LottoPrize;

public class LottoResultResponse {

    private final Map<LottoPrize, Integer> winningResult;
    private final double profitRate;

    public LottoResultResponse(Map<LottoPrize, Integer> winningResult, double profitRate) {
        this.winningResult = winningResult;
        this.profitRate = profitRate;
    }

    public Map<LottoPrize, Integer> getWinningResult() {
        return winningResult;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
