package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.common.LottoPrize;
import lotto.domain.Lotto;
import lotto.parser.LottoInputParser;

public class LottoViewImpl implements LottoView {
    private final LottoInputParser lottoInputParser;

    public LottoViewImpl(LottoInputParser lottoInputParser) {
        this.lottoInputParser = lottoInputParser;
    }

    @Override
    public int readLottoPurchaseAmount() {
        System.out.println();
        System.out.println(Prompt.INPUT_LOTTO_PURCHASE_AMOUNT.Text());
        String input = Console.readLine();

        return lottoInputParser.parseToInt(input);
    }

    @Override
    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(Prompt.INPUT_WINNING_NUMBERS.Text());
        String input = Console.readLine();

        return lottoInputParser.parseToIntegerList(input);
    }

    @Override
    public int readBonusNumber() {
        System.out.println();
        System.out.println(Prompt.INPUT_BONUS_NUMBER.Text());
        String input = Console.readLine();

        return lottoInputParser.parseToInt(input);
    }

    @Override
    public void printIssuedLotto(List<Lotto> issuedLotto) {
        System.out.println();
        System.out.println(issuedLotto.size() + Prompt.OUTPUT_LOTTO_COUNT_SUFFIX.Text());
        issuedLotto.forEach(System.out::println);
    }

    @Override
    public void printWinningResult(Map<LottoPrize, Integer> winningResult) {
        System.out.println();
        System.out.println(Prompt.OUTPUT_WINNING_RESULT_TITLE.Text());

        Arrays.stream(LottoPrize.values()).forEach(lottoPrize -> {
            String formatted = String.format(Prompt.OUTPUT_WINNING_RESULT_FORMAT.Text(),
                    lottoPrize.Message(),
                    NumberFormat.getInstance().format(lottoPrize.Amount()),
                    winningResult.getOrDefault(lottoPrize, 0)
            );
            System.out.println(formatted);
        });
    }

    @Override
    public void printProfitRate(double profitRate) {
        String formatted = String.format(Prompt.OUTPUT_PROFIT_RATE_FORMAT.Text(),
                NumberFormat.getInstance().format(profitRate));
        System.out.println(formatted);
    }
}
