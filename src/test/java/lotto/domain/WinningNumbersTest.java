package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @ParameterizedTest
    @MethodSource("invalidLengthWinningNumbers")
    @DisplayName("당첨 번호의 개수가 6개(중복 제외)가 아니면 예외가 발생한다.")
    void 개수_예외(List<Integer> numbers) {
        assertThatThrownBy(() -> new WinningNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_WINNING_NUMBERS_LENGTH.Message());
    }

    @ParameterizedTest
    @MethodSource("invalidRangeWinningNumbers")
    @DisplayName("1에서 45 사이의 값이 아니면 예외가 발생한다.")
    void 범위_예외(List<Integer> numbers) {
        assertThatThrownBy(() -> new WinningNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_WINNING_NUMBERS_RANGE.Message());
    }

    private static Stream<List<Integer>> invalidLengthWinningNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7),
                List.of(1, 2, 2, 3, 4, 5)
        );
    }

    private static Stream<List<Integer>> invalidRangeWinningNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(0, 1, 2, 3, 4, 5)
        );
    }
}