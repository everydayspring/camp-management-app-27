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
        } else if(scoreStore.get(studentId).get(subjectId).checkScore(Integer.parseInt(index))) {
            System.out.println("* 해당 회차의 점수가 이미 등록되어 있습니다 *");
            System.out.println("============점수 등록 실패============");
            return;
        }else {
            scoreStore.get(studentId).get(subjectId).setScore(Integer.parseInt(index), Integer.parseInt(score));
        }
        System.out.println("============점수 등록 성공============");
        System.out.println("수강생 : " + studentId);
        System.out.println("과목 : " + subjectId);
        System.out.println("회차 : " + index);
        System.out.println("점수 : " + score);
        System.out.println("====================================");
    }
}
