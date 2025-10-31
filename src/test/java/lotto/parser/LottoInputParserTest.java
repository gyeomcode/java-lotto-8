package lotto.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoInputParserTest {
    private final InputParser inputParser = new LottoInputParser();

    @ParameterizedTest
    @ValueSource(strings = {"5000", " 5000", "5000 ", " 5000 ", "  5000"})
    @DisplayName("문자열을 trim 처리하고 숫자로 변환한다.")
    void 문자열_숫자_변환(String input) {
        int result = inputParser.parseToInt(input);

        assertThat(result).isEqualTo(5000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abc", "1000a",})
    @DisplayName("숫자가 아닌 문자열을 변환하면 예외가 발생한다.")
    void 숫자가_아닌_문자열_예외(String input) {
        assertThatThrownBy(() -> {
            inputParser.parseToInt(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("문자열이 int가 표현할 수 있는 범위를 초과하면 예외가 발생한다.")
    void 표현범위_초과_문자열_예외() {
        String input = "2147483648";

        assertThatThrownBy(() -> {
            inputParser.parseToInt(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("문자열을 숫자 리스트로 변환한다.")
    void 문자열_숫자리스트_변환() {
        String input = "1,2,3,4,5,6";

        List<Integer> winningNumbers = inputParser.parseToIntegerList(input);

        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,c,d,e,f", "1,a,2,b,c,3"})
    @DisplayName("리스트 내 숫자가 아닌 값이 포함되어 있으면 예외가 발생한다.")
    void 숫자가_아닌_리스트_예외(String input) {
        assertThatThrownBy(() -> {
            inputParser.parseToIntegerList(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @Test
    @DisplayName("리스트 내 int 범위를 초과하는 값이 포함되어 있으면 예외가 발생한다.")
    void 표현범위_초과_리스트_예외() {
        String input = "2147483648,2,3,4,5,6";

        assertThatThrownBy(() -> {
            inputParser.parseToIntegerList(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }
}