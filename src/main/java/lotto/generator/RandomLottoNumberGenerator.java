package lotto.generator;

import static lotto.common.LottoRule.LOTTO_NUMBERS_LENGTH;
import static lotto.common.LottoRule.LOTTO_NUMBER_MAX_VALUE;
import static lotto.common.LottoRule.LOTTO_NUMBER_MIN_VALUE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                        LOTTO_NUMBER_MIN_VALUE,
                        LOTTO_NUMBER_MAX_VALUE,
                        LOTTO_NUMBERS_LENGTH)
                .stream()
                .sorted()
                .toList();
    }
}
