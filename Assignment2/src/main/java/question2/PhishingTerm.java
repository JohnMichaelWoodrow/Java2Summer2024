package question2;

public class PhishingTerm {
    private final String term;
    private final int points;

    public PhishingTerm(String term, int points) {
        this.term = term;
        this.points = points;
    }

    public String getTerm() {
        return term;
    }

    public int getPoints() {
        return points;
    }
}

