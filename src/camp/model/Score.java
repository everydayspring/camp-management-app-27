package camp.model;

public class Score {

    private int[] scores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public Score(String index, String score) {
        setScores(index, score);
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(String index, String score) {
        int indexInt; //번호
        int ScoreInt; //점수

        // 인덱스 예외처리
        try {
            indexInt = Integer.parseInt(index)-1;
            ScoreInt = Integer.parseInt(score);

            if(indexInt < 1 || indexInt > 10){
                throw new NumberFormatException("유효하지 않은 숫자");
            }

            if(scores[indexInt] == -1){
                scores[indexInt] = ScoreInt;
            } else {
                System.out.println(index + "회차의 점수는 이미 등록되어 있습니다");
            }

        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력됨");
        }

    }

}
