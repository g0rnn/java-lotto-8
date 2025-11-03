package lotto;

import lotto.console.ConsoleView;

public class Application {
    public static void main(String[] args) {
        try {
            LottoController controller = new LottoController(new ConsoleView());
            controller.run();
        } finally {
            ConsoleView.release();
        }
    }
}
