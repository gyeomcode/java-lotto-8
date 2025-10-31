package lotto.parser;

import java.util.List;

public interface LottoInputParser {

    int parseToInt(String input);

    List<Integer> parseToIntegerList(String input);
}
