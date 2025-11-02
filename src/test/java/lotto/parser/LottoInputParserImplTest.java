package lotto.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoInputParserImplTest {
    private final LottoInputParser lottoInputParser = new LottoInputParserImpl();

    @DisplayName("문자열의 앞뒤 공백을 제거하고 숫자로 변환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"5000", " 5000", "5000 ", " 5000 "})
    void 문자열_숫자로_파싱(String input) {
        int result = lottoInputParser.parseToInt(input);

        assertThat(result).isEqualTo(5000);
    }

    @DisplayName("공백 포함 숫자가 아닌 문자열을 변환하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abc", "1000a"})
    void 숫자가아닌문자열_파싱_예외가발생한다(String input) {
        assertThatThrownBy(() -> {
            lottoInputParser.parseToInt(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @DisplayName("int 타입의 범위를 초과한 문자열을 변환하면 예외가 발생한다.")
    @Test
    void 범위를초과한문자열_파싱_예외가발생한다() {
        String input = "2147483648";

        assertThatThrownBy(() -> {
            lottoInputParser.parseToInt(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @DisplayName("쉼표 기준으로 문자열을 분리, 각 문자의 앞뒤 공백 제거 후 숫자 리스트로 변환한다.")
    @Test
    void 문자열_숫자리스트_파싱() {
        String input = "1,2,3,4,5,6";

        List<Integer> winningNumbers = lottoInputParser.parseToIntegerList(input);

        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("리스트에 숫자가 아닌 값이 포함되어 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a,b,c,d,e,f", "1,a,2,b,c,3"})
    void 숫자가아닌값포함리스트_파싱_예외가발생한다(String input) {
        assertThatThrownBy(() -> {
            lottoInputParser.parseToIntegerList(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }

    @DisplayName("리스트 내 int 타입의 범위를 초과하는 값이 포함되어 있으면 예외가 발생한다.")
    @Test
    void 표현범위초과포함리스트_파싱_예외가발생한다() {
        String input = "2147483648,2,3,4,5,6";

        assertThatThrownBy(() -> {
            lottoInputParser.parseToIntegerList(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INT_RANGE_EXCEEDED.Message());
    }
}