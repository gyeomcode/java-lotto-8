package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.common.LottoPrize;
import lotto.domain.Lotto;
import lotto.generator.LottoNumberGenerator;
import lotto.generator.RandomLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("당첨 번호 개수와 보너스 번호 포함 여부로 등수를 구한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForfindPrize")
    void 당첨_번호_개수와_보너스_번호_포함_여부로_등수를_구한다(int expected, int matchCount, boolean bonusMatched) {
        LottoPrize lottoPrize = LottoPrize.findPrize(matchCount, bonusMatched).get();

        assertThat(lottoPrize.Amount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과를 계산한다")
    void 당첨_결과를_계산한다() {
        List<Lotto> issuedLotto = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Map<LottoPrize, Integer> result = lottoService.calculateWinningResult(
                issuedLotto,
                winningNumbers,
                bonusNumber
        );

        assertThat(result).containsEntry(LottoPrize.SECOND, 1);
        assertThat(result).containsEntry(LottoPrize.FIRST, 1);
        assertThat(result).containsEntry(LottoPrize.FIFTH, 1);
        assertThat(result.values().size()).isEqualTo(3);
    }

    private static Stream<Arguments> provideStringsForfindPrize() {
        return Stream.of(
                Arguments.of(5_000, 3, false),
                Arguments.of(50_000, 4, false),
                Arguments.of(1_500_000, 5, false),
                Arguments.of(30_000_000, 5, true),
                Arguments.of(2_000_000_000, 6, false)
        );
    }
}