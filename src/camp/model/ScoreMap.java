package camp.model;

import java.util.*;

public class ScoreMap {
    public Map<String, Map<String, Score>> scoreStore; // < 수강생 고유 번호, < 과목 고유 번호, 점수 객체 > >
    public int scoreIndex; // 고유 번호 인덱스

    public ScoreMap() {
        this.scoreIndex = 0;
        this.scoreStore = new HashMap<>();
    }

    // 수강중인 과목 Map 반환
    public Map<String, Score> getStudent(String studentId) {
        return this.scoreStore.get(studentId);
    }

    // 점수 등록
    public void setScoreStore(String studentId, String subjectId, String index, String score) {
        Map<String, Score> inner = new HashMap<>();

        // 해당 과목에 점수가 동록된 적이 없는경우 새로운 Score 객체 생성 후 점수 등록
        if (!scoreStore.containsKey(studentId) || !scoreStore.get(studentId).containsKey(subjectId)) {
            inner.put(subjectId, new Score(Integer.parseInt(index), Integer.parseInt(score)));
            scoreStore.put(studentId, inner);
        // 해당 회차에 이미 점수가 등록되어 있는 경우 등록 실패
        } else if (scoreStore.get(studentId).get(subjectId).checkScore(Integer.parseInt(index))) {
            System.out.println("* 해당 회차의 점수가 이미 등록되어 있습니다 *");
            System.out.println("============점수 등록 실패============");
            return;
        } else {
            scoreStore.get(studentId).get(subjectId).setScore(Integer.parseInt(index), Integer.parseInt(score));
        }
        System.out.println("============점수 등록 성공============");
        System.out.println("수강생 : " + studentId);
        System.out.println("과목 : " + subjectId);
        System.out.println("회차 : " + index);
        System.out.println("점수 : " + score);
        System.out.println("====================================");
    }

    // 점수 수정
    public void updateScoreStore(String studentId, String subjectId, String index, String score) {
        // 등록된 점수가 없는 경우 수정 실패
        if (!scoreStore.get(studentId).get(subjectId).checkScore(Integer.parseInt(index))) {
            System.out.println("* 해당 회차에 등록된 점수가 없습니다 *");
            System.out.println("============점수 수정 실패============");
        } else {
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