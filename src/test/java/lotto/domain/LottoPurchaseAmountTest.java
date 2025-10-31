package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("0 이하이면 예외가 발생한다.")
    void 양의정수_아닌값_예외(int amount) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(amount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 500, 1234})
    @DisplayName("1,000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void 로또_가격_단위_예외(int amount) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(amount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
    }
}