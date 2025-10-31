package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("보너스 번호가 1에서 45 사이의 값이 아니면 예외가 발생한다.")
    void 보너스번호_범위_예외(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_BONUS_NUMBER_RANGE.Message());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void 보너스번호_중복_예외() {
        WinningNumbers winningNumber = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 3;

        assertThatThrownBy(() -> new BonusNumber(bonusNumber, winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATED_BONUS_NUMBER.Message());
    }
}