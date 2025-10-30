package lotto.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private final InputView inputView = new InputView();

    @ParameterizedTest
    @ValueSource(strings = {"5000", " 5000", "5000 ", " 5000 ", "  5000"})
    @DisplayName("문자열 로또 구입 금액을 trim 후 숫자로 변환한다.")
    void 로또_구입_금액_파싱(String input) {
        int result = inputView.parseLottoPurchaseAmount(input);

        assertThat(result).isEqualTo(5000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abc", "1000a",})
    @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생한다.")
    void 숫자가_아닌_값_예외(String input) {
        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("int 범위를 초과하는 값을 입력하면 예외가 발생한다.")
    void 표현_범위_초과_예외() {
        String input = "2147483648";

        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000"})
    @DisplayName("0 이하의 값을 입력하면 예외가 발생한다.")
    void 자연수_아닌_값_예외(String input) {
        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_NON_POSITIVE.Message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "300", "1200", "2400", "3456", "5678"})
    @DisplayName("1,000원으로 나누어 떨어지지 않는 값을 입력하면 예외가 발생한다.")
    void 로또_가격_단위_예외(String input) {
        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT_UNIT.Message());
    }
}