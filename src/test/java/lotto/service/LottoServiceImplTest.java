package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.generator.LottoNumberGenerator;
import lotto.generator.RandomLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceImplTest {
    private final LottoService lottoService = new LottoServiceImpl();

    @Test
    @DisplayName("로또 구입 금액 만큼 로또를 발행한다.")
    void 로또_발행_개수_확인() {
        int amount = 10000;

        List<Lotto> result = lottoService.issueLotto(amount, new RandomLottoNumberGenerator());

        assertThat(result.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("발행된 로또 번호는 오름차순 정렬된다.")
    void 로또_발행_정렬_확인() {
        LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();
        List<Integer> numbers = lottoNumberGenerator.generate();

        List<Integer> sorted = numbers.stream()
                .sorted()
                .toList();

        assertThat(numbers).isEqualTo(sorted);
    }
}