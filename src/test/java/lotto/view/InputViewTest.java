package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private final InputView inputView = new InputView();

    @ParameterizedTest
    @ValueSource(strings = {"5000", " 5000", "5000 ", " 5000 ", "  5000"})
    @DisplayName("입력한 로또 구입 금액을 trim 후 숫자로 변환한다.")
    void 로또구입금액_파싱(String input) {
        int result = inputView.parseLottoPurchaseAmount(input);

        assertThat(result).isEqualTo(5000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abc", "1000a",})
    @DisplayName("숫자가 아닌 로또 구입 금액을 입력하면 예외가 발생한다.")
    void 숫자가_아닌_로또구입금액_예외(String input) {
        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("int 범위를 초과하는 로또 구입 금액을 입력하면 예외가 발생한다.")
    void 표현범위_초과_로또구입금액_예외() {
        String input = "2147483648";

        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("당첨 번호를 숫자 리스트로 변환한다.")
    void 당첨번호_파싱() {
        String input = "1,2,3,4,5,6";

        List<Integer> winningNumbers = inputView.parseWinningNumbers(input);

        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("int 범위를 초과하는 당첨 번호를 입력하면 예외가 발생한다.")
    void 표현범위_초과_당첨번호_예외() {
        String input = "2147483648,2,3,4,5,6";

        assertThatThrownBy(() -> {
            inputView.parseLottoPurchaseAmount(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,c,d,e,f", "1,a,2,b,c,3"})
    @DisplayName("숫자가 아닌 당첨 번호를 입력하면 예외가 발생한다.")
    void 숫자가_아닌_당첨번호_예외(String input) {
        assertThatThrownBy(() -> {
            inputView.parseWinningNumbers(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", " 10", "10 ", " 10 ", "  10"})
    @DisplayName("입력한 보너스 번호를 trim 후 숫자로 변환한다.")
    void 보너스번호_파싱(String input) {
        int result = inputView.parseBonusNumber(input);

        assertThat(result).isEqualTo(10);
    }
}