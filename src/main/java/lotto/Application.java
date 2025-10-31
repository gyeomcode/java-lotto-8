package lotto;

import lotto.controller.LottoController;
import lotto.parser.LottoInputParser;
import lotto.service.LottoService;
import lotto.service.LottoServiceImpl;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new LottoInputParser());
        LottoService lottoService = new LottoServiceImpl();

        LottoController lottoController = new LottoController(inputView, lottoService);

        lottoController.run();
    }
}
