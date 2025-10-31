package lotto.parser;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorMessage;

public class LottoInputParser implements InputParser {

    public int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }

    public List<Integer> parseToIntegerList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }
}
