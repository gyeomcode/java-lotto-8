package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    @DisplayName("1,2를 쉼표로 split하면 1과 2로 분리된다")
    void 문자열_1과2_분리() {
        String input = "1,2";

        String[] result = input.split(",");

        assertThat(result).containsExactly("1", "2");
    }

    @Test
    @DisplayName("1를 쉼표로 split하면 1만 포함하는 배열이 된다.")
    void 문자열_1만_분리() {
        String input = "1";

        String[] result = input.split(",");

        assertThat(result).containsExactly("1");
    }

    @Test
    @DisplayName("(1,2)에서 괄호를 제거하면 1,2가 된다.")
    void 문자열_괄호_제거() {
        String input = "(1,2)";

        String result = input.substring(1, input.length() - 1);

        assertThat(result).isEqualTo("1,2");
    }

    @Test
    @DisplayName("abc에서 특정 위치 문자인 a, b, c를 가져온다.")
    void 문자열_특정_위치_문자_가져오기() {
        String input = "abc";

        char a = input.charAt(0);
        char b = input.charAt(1);
        char c = input.charAt(2);

        assertThat(a).isEqualTo('a');
        assertThat(b).isEqualTo('b');
        assertThat(c).isEqualTo('c');
    }

    @Test
    @DisplayName("특정 위치의 문자를 가져올 때 위치 값이 벗어나면 예외가 발생한다.")
    void 문자열_초과된_위치_가져오기() {
        String input = "abc";

        assertThatThrownBy(() -> {
            input.charAt(3);
        }).isInstanceOf(StringIndexOutOfBoundsException.class);
    }
}
