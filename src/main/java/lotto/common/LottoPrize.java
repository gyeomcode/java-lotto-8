package lotto.common;

import java.util.Arrays;
import java.util.Optional;

public enum LottoPrize {
    FIFTH(5_000, 3, false, "3개 일치"),
    FOURTH(50_000, 4, false, "4개 일치"),
    THIRD(1_500_000, 5, false, "5개 일치"),
    SECOND(30_000_000, 5, true, "5개 일치, 보너스 볼 일치"),
    FIRST(2_000_000_000, 6, false, "6개 일치");

    private final int amount;
    private final int matchCount;
    private final boolean bonusMatched;
    private final String message;

    LottoPrize(int amount, int matchCount, boolean bonusMatched, String message) {
        this.amount = amount;
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
        this.message = message;
    }

    public int Amount() {
        return amount;
    }

    public String Message() {
        return message;
    }

    public static Optional<LottoPrize> findPrize(int matchCount, boolean bonusMatched) {
        return Arrays.stream(LottoPrize.values())
                .filter(lottoPrize -> lottoPrize.matchCount == matchCount
                        && lottoPrize.bonusMatched == bonusMatched)
                .findFirst();
    }
}