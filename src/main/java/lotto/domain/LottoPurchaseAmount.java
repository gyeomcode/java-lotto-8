package lotto.domain;

import lotto.common.LottoRule;
import lotto.exception.ErrorMessage;

public class LottoPurchaseAmount {
    private final int amount;

    public LottoPurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
        }

        if (amount % LottoRule.LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
        }
    }
}
