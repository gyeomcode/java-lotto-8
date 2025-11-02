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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceImplTest {

    private final LottoService lottoService = new LottoServiceImpl();

    @DisplayName("로또 구입 금액만큼 로또 번호를 발행한다.")
    @ParameterizedTest
    @CsvSource({"1000,1", "5000,5", "10000,10"})
    void 로또번호_발행개수를_확인한다(String input, String expected) {
        int lottoPurchaseAmount = Integer.parseInt(input);

        List<Lotto> result = lottoService.issueLotto(lottoPurchaseAmount, new RandomLottoNumberGenerator());

        assertThat(result.size()).isEqualTo(Integer.parseInt(expected));
    }

    @DisplayName("발행된 로또 번호는 오름차순 정렬된다.")
    @Test
    void 로또번호_오름차순정렬을_확인한다() {
        LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();
        List<Integer> numbers = lottoNumberGenerator.generate();

        List<Integer> sorted = numbers.stream()
                .sorted()
                .toList();

        assertThat(numbers).isEqualTo(sorted);
    }

    @DisplayName("로또 번호에서 당첨 번호 일치 개수와 보너스 번호 포함 여부를 확인해서 상금을 구한다.")
    @ParameterizedTest
    @MethodSource("providerForFindPrize")
    void 당첨된상금을_구한다(int expected, int matchCount, boolean bonusMatched) {
        LottoPrize lottoPrize = LottoPrize.findPrize(matchCount, bonusMatched).get();

        assertThat(lottoPrize.Amount()).isEqualTo(expected);
    }

    @DisplayName("로또 번호와 당첨 번호 및 보너스 번호를 비교하여 당첨 내역를 구한다.")
    @Test
    void 당첨내역를_구한다() {
        List<Lotto> issuedLotto = List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(1, 10, 11, 12, 13, 14)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Map<LottoPrize, Integer> result = lottoService.calculateWinningResult(
                issuedLotto,
                winningNumbers,
                bonusNumber
        );

        assertThat(result).containsEntry(LottoPrize.FIFTH, 3);
        assertThat(result).containsEntry(LottoPrize.FOURTH, 1);
        assertThat(result).containsEntry(LottoPrize.THIRD, 1);
        assertThat(result).containsEntry(LottoPrize.SECOND, 1);
        assertThat(result).containsEntry(LottoPrize.FIRST, 1);
    }

    @DisplayName("당첨 내역과 로또 구입 금액을 통해 수익률을 계산한다.")
    @Test
    void 수익률을_계산한다() {
        Map<LottoPrize, Integer> winningResult = Map.of(LottoPrize.FIFTH, 3);
        int lottoPurchaseAmount = 8000;

        double result = lottoService.calculateProfitRate(winningResult, lottoPurchaseAmount);

        assertThat(result).isEqualTo(187.5);
    }

    @DisplayName("수익률을 둘째 자리에서 반올림한다.")
    @ParameterizedTest
    @CsvSource({"100,100.0", "51.47,51.5", "999999.97,1000000.0"})
    void 수익률_반올림을_확인한다(String input, String expected) {
        double profitRate = Double.parseDouble(input);

        assertThat(Math.round(profitRate * 10) / 10.0)
                .isEqualTo(Double.parseDouble(expected));
    }

    private static Stream<Arguments> providerForFindPrize() {
        return Stream.of(
                Arguments.of(5_000, 3, false),
                Arguments.of(50_000, 4, false),
                Arguments.of(1_500_000, 5, false),
                Arguments.of(30_000_000, 5, true),
                Arguments.of(2_000_000_000, 6, false)
        );
    }
}