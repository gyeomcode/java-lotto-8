package lotto.dto;

public class LottoIssueRequest {

    private final int lottoPurchaseAmount;

    public LottoIssueRequest(int lottoPurchaseAmount) {
        this.lottoPurchaseAmount = lottoPurchaseAmount;
    }

    public int getLottoPurchaseAmount() {
        return lottoPurchaseAmount;
    }
}
