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
        int indexInt;
        int ScoreInt;

        // 인덱스 예외처리
        try {
            indexInt = Integer.parseInt(index);
            indexInt--;
            ScoreInt = Integer.parseInt(score);

            if(indexInt < 0 || indexInt > 9){
                throw new NumberFormatException();
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
