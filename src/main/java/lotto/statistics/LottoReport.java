package lotto.statistics;

public record LottoReport(LottoResult result, String rateOfReturn) {

    @Override
    public String toString() {
        return result.toString() + "총 수익률은 " + rateOfReturn + "%입니다.";
    }
}
