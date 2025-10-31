package lotto;

import lotto.controller.LottoController;
import lotto.parser.LottoInputParser;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new LottoInputParser());
        LottoController lottoController = new LottoController(inputView);

        lottoController.run();
    }
}
