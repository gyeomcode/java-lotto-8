package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @DisplayName("보너스 번호를 정상적으로 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 44, 45})
    void 보너스번호_인스턴스생성_숫자번호를확인한다(int number) {
        BonusNumber bonusNumber = new BonusNumber(number);

        assertThat(bonusNumber.getNumber()).isEqualTo(number);
    }

    @DisplayName("보너스 번호가 1에서 45 사이의 값이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 범위미충족번호_인스턴스생성_예외가발생한다(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_BONUS_NUMBER_RANGE.Message());
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 중복된보너스번호_인스턴스생성_예외가발생한다() {
        WinningNumbers winningNumber = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 3;

        assertThatThrownBy(() -> new BonusNumber(bonusNumber, winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATED_BONUS_NUMBER.Message());
    }
}