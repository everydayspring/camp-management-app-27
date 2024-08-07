package camp.model;

public class Score {
    private int[] scores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public Score(int index, int score) {
        setScore(index, score);
    }

    public int[] getScores() {
        return scores;
    }

    public void setScore(int index, int score) {
        scores[index - 1] = score;
    }

    public boolean checkScore(int index) {
        if (scores[index - 1] == -1) return false;
        return true;
    }
}