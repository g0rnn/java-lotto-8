package lotto.statistics;

public enum Grade {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5000),
    NONE(0, 0);
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
