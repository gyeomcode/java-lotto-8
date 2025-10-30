package lotto.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private final InputView inputView = new InputView();

    @ParameterizedTest
    @ValueSource(strings = {"5000", " 5000", "5000 ", " 5000 ", "  5000"})
    @DisplayName("문자열 로또 구입 금액을 trim 후 숫자로 변환한다.")
    void 로또_구입_금액_파싱(String input) {
        int result = inputView.parsePurchaseAmount(input);

        assertThat(result).isEqualTo(5000);
    }
}