package lotto.parser;

import java.util.List;

public interface InputParser {

    int parseToInt(String input);

    List<Integer> parseToIntegerList(String input);
}
