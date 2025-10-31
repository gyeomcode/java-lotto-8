package lotto.parser;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorMessage;

public class LottoInputParser implements InputParser {

    public int parseToInt(String input) {
        try {
            int value = Integer.parseInt(input.trim());

            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }

    public List<Integer> parseToIntegerList(String input) {
        try {
            List<Integer> values = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .toList();

            return values;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INT_RANGE_EXCEEDED.Message());
        }
    }
}
