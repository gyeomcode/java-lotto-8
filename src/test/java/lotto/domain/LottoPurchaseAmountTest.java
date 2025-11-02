package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseAmountTest {

    @DisplayName("로또 구입 금액이 0 이하의 값이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 양의정수가아닌값_인스턴스생성_예외가발생한다(int amount) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(amount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
    }

    @DisplayName("로또 구입 금액이 1,000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 500, 1234})
    void 로또가격단위가아닌값_인스턴스생성_예외가발생한다(int amount) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(amount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
    }
}