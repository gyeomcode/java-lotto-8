package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public class LottoIssueResponse {
    private final List<Lotto> issuedLotto;

    public LottoIssueResponse(List<Lotto> issuedLotto) {
        this.issuedLotto = issuedLotto;
    }

    public List<Lotto> getIssuedLotto() {
        return issuedLotto;
    }
}
