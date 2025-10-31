package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("1에서 45 사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void 로또_번호가_1에서_45_사이의_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_RANGE.Message());
    }

    @DisplayName("로또 번호와 당첨 번호를 비교해서 당첨된 개수를 구한다.")
    @Test
    void 로또_번호와_당첨_번호를_비교해서_당첨된_개수를_구한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 10, 11);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = lotto.countMatches(winningNumbers);

        assertThat(matchCount).isEqualTo(4);
    }

    @DisplayName("로또 번호와 보너스 번호를 비교해서 보너스 번호 포함 여부를 구한다.")
    @Test
    void 로또_번호와_보너스_번호를_비교해서_보너스_번호_포함_여부를_구한다() {
        int bonusNumber = 5;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean bonusMatched = lotto.isBonusMatched(bonusNumber);

        assertThat(bonusMatched).isTrue();
    }
}
