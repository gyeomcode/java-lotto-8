package study;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("크기는 3이다.")
    void 크기_구하기() {
        int result = numbers.size();

        assertThat(result).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("1, 2, 3이 존재한다.")
    void 포함_여부_구하기(int input) {
        boolean result = numbers.contains(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    @DisplayName("1, 2, 3이 존재하고 4, 5는 존재하지 않는다.")
    void 포함_여부_구하기(String input, String expected) {
        boolean result = numbers.contains(Integer.parseInt(input));

        assertThat(result).isEqualTo(Boolean.parseBoolean(expected));
    }
}
