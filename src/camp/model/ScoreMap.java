package camp.model;

import java.util.*;

public class ScoreMap {
    public Map<String, Map<String, Score>> scoreStore;
    public int scoreIndex;

    public ScoreMap() {
        this.scoreIndex = 0;
        this.scoreStore = new HashMap<>();

    }

    public Map<String, Score> getStudent(String studentId) {
        return this.scoreStore.get(studentId);
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

    public void updateScoreStore(String studentId, String subjectId, String index, String score) {

        if(!scoreStore.get(studentId).get(subjectId).checkScore(Integer.parseInt(index))) {
            System.out.println("* 해당 회차에 등록된 점수가 없습니다 *");
            System.out.println("============점수 수정 실패============");
        }else {
            scoreStore.get(studentId).get(subjectId).setScore(Integer.parseInt(index), Integer.parseInt(score));
            System.out.println("============점수 수정 성공============");
            System.out.println("수강생 : " + studentId);
            System.out.println("과목 : " + subjectId);
            System.out.println("회차 : " + index);
            System.out.println("점수 : " + score);
            System.out.println("====================================");
        }
    }
}
