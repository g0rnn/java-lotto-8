package lotto.statistics;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import lotto.purchase.Money;

public class LottoResult {

    private final Map<Grade, Integer> results = new EnumMap<>(Grade.class);

    public LottoResult() {
        for (Grade grade : Grade.values()) {
            if (grade == Grade.NONE) {
                continue;
            }
            results.put(grade, 0);
        }
    }

    public void add(Grade grade) {
        if (grade == Grade.NONE) {
            return;
        }
        results.put(grade, results.get(grade) + 1);
    }

    public Money calculateTotalProfit() {
        long total = 0L;

        for (Entry<Grade, Integer> entry : results.entrySet()) {
            long prize = entry.getKey().getPrize();
            int count = entry.getValue();

            total += prize * count;
        }

        return new Money(total);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        results.forEach((grade, matched) ->
                builder.append(grade.toString())
                        .append(" - ")
                        .append(matched)
                        .append("개\n"));
        return builder.toString();
    }
}
