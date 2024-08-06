package camp.model;

import java.util.*;

public class ScoreMap {
    public Map<String, Map<String, Score>> scoreStore;
    public int scoreIndex;
    public final String INDEX_TYPE_SCORE = "SC";

    public ScoreMap() {
        this.scoreIndex = 0;
        this.scoreStore = new HashMap<>();

    }

    private String subjectSequence() {
        scoreIndex++;
        return INDEX_TYPE_SCORE + scoreIndex;
    }

    public boolean checkEmpty() {
        return this.scoreStore.isEmpty();
    }

    public Map<String, Map<String, Score>> getScoreStore() {
        return scoreStore;
    }

    public void setScoreStore(String studentId, String subjectId, String index, String score) {
        Map<String, Score> inner = new HashMap<>();

        if (!scoreStore.containsKey(studentId) || !scoreStore.get(studentId).containsKey(subjectId)) {
            inner.put(subjectId, new Score(Integer.parseInt(index), Integer.parseInt(score)));
            scoreStore.put(studentId, inner);
            System.out.println(" 처음 넣어요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
            scoreStore.get(studentId).get(subjectId).setScore(Integer.parseInt(index), Integer.parseInt(score));
            System.out.println("점수를 등록한 적 있는 과목에 넣어요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
