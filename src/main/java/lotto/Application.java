package lotto;

import lotto.controller.LottoController;
import lotto.parser.LottoInputParserImpl;
import lotto.service.LottoService;
import lotto.service.LottoServiceImpl;
import lotto.view.LottoView;
import lotto.view.LottoViewImpl;

public class Application {
    public static void main(String[] args) {
        LottoView lottoView = new LottoViewImpl(new LottoInputParserImpl());
        LottoService lottoService = new LottoServiceImpl();

        LottoController lottoController = new LottoController(lottoView, lottoService);

        lottoController.run();
    }
}
