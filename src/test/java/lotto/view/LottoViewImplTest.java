package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LottoViewImplTest {

    @Test
    void 수익률에_천단위구분쉼표_확인한다() {
        double profitRate = 1000000.0;

        String formatted = String.format(Prompt.OUTPUT_PROFIT_RATE_FORMAT.Text(), profitRate);

        assertThat(formatted).isEqualTo("총 수익률은 1,000,000.0%입니다.");
    }
}