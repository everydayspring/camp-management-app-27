package camp.model;

public class Score {
    private int[] scores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public Score(String index, String score) {
        setScores(index, score);
    }

    public int[] getScores() {
        return scores;
    }

    // 점수 등록 --> 이봄
    public void setScores(String index, String score) {
        int indexInt; //번호
        int ScoreInt; //점수

        // 인덱스 예외처리
        try {
            indexInt = Integer.parseInt(index) - 1;
            ScoreInt = Integer.parseInt(score);

            if (indexInt < 0 || indexInt > 9 || ScoreInt < 0 || ScoreInt > 100) {
                throw new NumberFormatException();
            }

            if (scores[indexInt] == -1) {
                scores[indexInt] = ScoreInt;
            } else {
                System.out.println(index + "회차의 점수는 이미 등록되어 있습니다");
            }

        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력됨");
        }
    }
}
