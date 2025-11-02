package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 각 번호가 1에서 45 사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void 범위미충족번호_인스턴스생성_예외가발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_RANGE.Message());
    }

    @DisplayName("로또 번호를 정상적으로 생성한다.")
    @Test
    void 로또번호_인스턴스생성_숫자리스트를확인한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.toString()).isEqualTo(numbers.toString());
    }

    @DisplayName("로또 번호와 당첨 번호를 비교해서 당첨된 개수를 구한다.")
    @Test
    void 로또번호_당첨된개수를_구한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 10, 11);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = lotto.countMatches(winningNumbers);

        assertThat(matchCount).isEqualTo(4);
    }

    @DisplayName("로또 번호와 보너스 번호를 비교해서 보너스 번호 포함 여부를 확인한다.")
    @ParameterizedTest
    @CsvSource({"1,true", "2,true", "7,false", "8,false"})
    void 로또번호_보너스번호포함을_확인한다(String input, String expected) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean bonusMatched = lotto.isBonusMatched(Integer.parseInt(input));

        assertThat(bonusMatched).isEqualTo(Boolean.parseBoolean(expected));
    }
}
