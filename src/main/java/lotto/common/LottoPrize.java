package lotto.common;

import java.util.Arrays;
import java.util.Optional;

public enum LottoPrize {
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final int amount;
    private final int matchCount;
    private final boolean bonusMatched;

    LottoPrize(int amount, int matchCount, boolean bonusMatched) {
        this.amount = amount;
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
    }

    public int Amount() {
        return amount;
    }

    public static Optional<LottoPrize> findPrize(int matchCount, boolean bonusMatched) {
        return Arrays.stream(LottoPrize.values())
                .filter(lottoPrize -> lottoPrize.matchCount == matchCount
                        && lottoPrize.bonusMatched == bonusMatched)
                .findFirst();
    }
}