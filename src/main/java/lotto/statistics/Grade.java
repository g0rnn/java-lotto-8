package lotto.statistics;

import java.text.NumberFormat;

public enum Grade {

    NONE(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    ;

    private final int rank;
    private final long prize;
    private final boolean matchedBonus;

    Grade(int rank, long prize) {
        this.rank = rank;
        this.prize = prize;
        this.matchedBonus = false;
    }

    Grade(int rank, long prize, boolean matchedBonus) {
        this.rank = rank;
        this.prize = prize;
        this.matchedBonus = matchedBonus;
    }

    public long getPrize() {
        return this.prize;
    }

    @Override
    public String toString() {
        String money = NumberFormat.getInstance().format(prize);
        if (this.matchedBonus) {
            return rank + "개 일치, 보너스 볼 일치 (" + money + "원)";
        }
        return rank + "개 일치 (" + money + "원)";
    }

    public static Grade of(int matched, boolean matchedBonus) {
        if (SECOND.rank == matched && SECOND.matchedBonus == matchedBonus) {
            return SECOND;
        }
        if (THIRD.rank == matched && THIRD.matchedBonus == matchedBonus) {
            return THIRD;
        }

        for (Grade grade : values()) {
            if (grade.rank == matched) {
                return grade;
            }
        }
        return NONE;
    }
}
