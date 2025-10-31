package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
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
}
