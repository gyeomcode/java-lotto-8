package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @DisplayName("당첨 번호를 정상적으로 생성한다.")
    @Test
    void 당첨번호_인스턴스생성_숫자리스트를확인한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호의 개수가 중복을 제외하고 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("providerForInvalidLengthWinningNumbers")
    void 중복제외개수미달_인스턴스생성_예외가발생한다(List<Integer> numbers) {
        assertThatThrownBy(() -> new WinningNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_WINNING_NUMBERS_LENGTH.Message());
    }

    @DisplayName("당첨 번호의 각 번호가 1에서 45 사이의 값이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("providerForInvalidRangeWinningNumbers")
    void 범위미충족번호_인스턴스생성_예외가발생한다(List<Integer> numbers) {
        assertThatThrownBy(() -> new WinningNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_WINNING_NUMBERS_RANGE.Message());
    }

    @DisplayName("당첨 번호에 특정 번호가 포함되어 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource("providerForContains")
    void 특정번호_당첨번호에포함_확인한다(int number, boolean expected) {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 3, 4, 5, 6, 7));

        assertThat(winningNumbers.contains(number)).isEqualTo(expected);
    }

    private static Stream<List<Integer>> providerForInvalidLengthWinningNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7),
                List.of(1, 2, 2, 3, 4, 5)
        );
    }

    private static Stream<List<Integer>> providerForInvalidRangeWinningNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(0, 1, 2, 3, 4, 5)
        );
    }

    private static Stream<Arguments> providerForContains() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(2, false)
        );
    }
}