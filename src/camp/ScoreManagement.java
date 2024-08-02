package camp;

import camp.model.Score;
import camp.model.Student;

import java.util.*;

import static camp.CampManagementApplication.*;

public class ScoreManagement {
    // 점수 관리 메뉴
    public static void displayScoreView() {
        System.out.println(scoreStore.size());
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 특정 과목 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateScore(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGradeBySubject();
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 고유 번호 입력값 검증 --> 김창민
    private static String checkStudentId() {
        printStudentInfo();

        System.out.print("\n관리할 수강생의 고유 번호를 입력하세요 (ex. ST) : ");
        String studentId = sc.next();
        if (!studentStore.containsKey(studentId))
            throw new IllegalArgumentException("해당 학생은 존재하지 않습니다.");

        return studentId;
    }

    // 수강중인 과목 고유 번호 입력값 검증 --> 이봄
    private static String checkSubjectId(String studentId) {
        CampManagementApplication.printSubjectInfoByStudentId(studentId);
        Student student = studentStore.get(studentId);
        ArrayList<String> scores = student.getSubjectList();

        System.out.print("\n관리할 과목의 고유 번호를 입력하세요 (ex. SU1) : ");
        String subjectId = sc.next();  //SU1
        if (!scores.contains(subjectId)) {
            throw new IllegalArgumentException("선택한 수강생이 수강중인 과목이 아닙니다.");
        }
        return subjectId;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록 --> 이봄
    private static void createScore() {
        String studentId = checkStudentId(); // 관리할 수강생 고유 번호
        String subjectId = checkSubjectId(studentId); // 등록할 과목 고유 번호

        System.out.println("등록할 시험 회차를 입력하시오...");
        String index = sc.next();
        System.out.println("점수를 입력하시오...");
        String score = sc.next();

        Map<String, Score> inner = new HashMap<>();

        if (!scoreStore.containsKey(studentId) || !scoreStore.get(studentId).containsKey(subjectId)) {
            inner.put(subjectId, new Score(index, score));
            scoreStore.put(studentId, inner);
        } else {
            scoreStore.get(studentId).get(subjectId).setScores(index, score);
        }
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정 --> 김민주
    public static void updateScore() {
        String studentId = checkStudentId(); // 관리할 수강생 고유 번호
        if (!scoreStore.containsKey(studentId)) {
            System.out.println("등록된 점수가 없는 학생입니다");
            return;
        }

        String subjectId = checkSubjectId(studentId); // 등록할 과목 고유 번호
        if (!scoreStore.get(studentId).containsKey(studentId)) {
            System.out.println("등록된 점수가 없는 학생입니다");
            return;
        }

        System.out.println("등록할 시험 회차를 입력하시오...");
        String index = sc.next();
        System.out.println("점수를 입력하시오...");
        String score = sc.next();

        int indexInt; //번호
        int ScoreInt; //점수

        // 인덱스 예외처리
        try {
            indexInt = Integer.parseInt(index) - 1;
            ScoreInt = Integer.parseInt(score);

            if (indexInt < 0 || indexInt > 9 || ScoreInt < 0 || ScoreInt > 100) {
                throw new NumberFormatException();
            }

            int[] scores = scoreStore.get(studentId).get(subjectId).getScores();

            if (scores[indexInt] != -1) {
                scores[indexInt] = ScoreInt;
            } else {
                System.out.println(index + "회차의 점수는 이미 등록되어 있습니다");
            }

        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력됨");
        }
    }


    // 수강생의 특정 과목 회차별 등급 조회 --> 김태현
    private static void inquireRoundGradeBySubject() {
        Scanner sc = new Scanner(System.in);
        String studentId = checkStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)

        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현

        try {
            //scoreStore 맵에 입력한 학생고유번호가 존재할경우
            if (scoreStore.containsKey(studentId)) {
                System.out.println("점수 조회를 원하는 과목의 고유번호를 입력하세요");
                //전체보유중인 subject 의 고유번호 전체를 보여줌
                printSubjectInfoByStudentId(studentId);
                String inputSubjectId = sc.nextLine();
                int indicator = Character.getNumericValue(inputSubjectId.charAt(2));
                //scoreStore안에있는 Map 을 지정해줌
                Map<String, Score> innerScoreStore = scoreStore.get(studentId);

                //만약 2중 맵안에 고유학생번호키가 가진 Value의 Map안에 고유 과목 키값이 존재할경우
                if (innerScoreStore.containsKey(inputSubjectId) && indicator < 6) {
                    int[] d = innerScoreStore.get(inputSubjectId).getScores();

                    for (int i = 0; i < d.length; i++) {
                        int num = d[i];

                        if (num > 94) {
                            System.out.println((i + 1) + "회차 등급: A");
                        } else if (num > 89) {
                            System.out.println((i + 1) + "회차 등급: B");
                        } else if (num > 79) {
                            System.out.println((i + 1) + "회차 등급 : C");
                        } else if (num > 69) {
                            System.out.println((i + 1) + "회차 등급 : D");
                        } else if (num > 59) {
                            System.out.println((i + 1) + "회차 등급 : F");
                        } else if (num > 0) {
                            System.out.println((i + 1) + "회차 등급 : N");
                        } else {
                            continue;
                        }
                    }
                    System.out.println("\n등급 조회 성공!");

                } else if (innerScoreStore.containsKey(inputSubjectId) && indicator > 5) {
                    int[] d = innerScoreStore.get(inputSubjectId).getScores();

                    for (int i = 0; i < d.length; i++) {
                        int num = d[i];

                        if (num > 89) {
                            System.out.println((i + 1) + "회차 등급: A");
                        } else if (num > 79) {
                            System.out.println((i + 1) + "회차 등급: B");
                        } else if (num > 69) {
                            System.out.println((i + 1) + "회차 등급 : C");
                        } else if (num > 59) {
                            System.out.println((i + 1) + "회차 등급 : D");
                        } else if (num > 49) {
                            System.out.println((i + 1) + "회차 등급 : F");
                        } else if (num > 0) {
                            System.out.println((i + 1) + "회차 등급 : N");
                        } else {
                            continue;
                        }
                    }
                    System.out.println("\n등급 조회 성공!");
                } else {
                    throw new Exception("잘못된 과목 고유번호를 입력하였습니다.");
                }
            }//해당 고유번호를지닌 학생이 없을시
            else {
                throw new Exception("해당 고유번호를 가진 수강생이 존재하지않습니다");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void inquireAverageGradeBySubject() {
        Scanner sc = new Scanner(System.in);
        String studentId = checkStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        int totalScore = 0;
        int totalRound = 0;
        int averageScore = 0;
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현

        try {
            //scoreStore 맵에 입력한 학생고유번호가 존재할경우
            if (scoreStore.containsKey(studentId)) {
                System.out.println("평균 점수 조회를 원하는 과목의 고유번호를 입력하세요");
                //전체보유중인 subject 의 고유번호 전체를 보여줌
                printSubjectInfoByStudentId(studentId);
                String inputSubjectId = sc.nextLine();
                int indicator = Character.getNumericValue(inputSubjectId.charAt(2));
                //scoreStore안에있는 Map 을 지정해줌
                Map<String, Score> innerScoreStore = scoreStore.get(studentId);
                int[] d = innerScoreStore.get(inputSubjectId).getScores();
                //평균점수를 위한 총 점수 및 총횟수를 구해줌
                for (int i = 0; i < d.length; i++) {
                    if (d[i] > 0) {
                        totalScore += d[i];
                        totalRound++;
                    } else {
                        continue;
                    }
                }
                //평균점수를 구해줌
                averageScore = totalScore / totalRound;
                //평균점수를 기반으로한 등급을 구해줌
                if (innerScoreStore.containsKey(inputSubjectId) && indicator < 6) {
                    if (averageScore > 94) {
                        System.out.println("평균 등급: A");
                    } else if (averageScore > 89) {
                        System.out.println("평균 등급: B");
                    } else if (averageScore > 79) {
                        System.out.println("평균 등급 : C");
                    } else if (averageScore > 69) {
                        System.out.println("평균 등급 : D");
                    } else if (averageScore > 59) {
                        System.out.println("평균 등급 : F");
                    } else if (averageScore > 0) {
                        System.out.println("평균 등급 : N");
                    }
                } else if (innerScoreStore.containsKey(inputSubjectId) && indicator > 5) {
                    if (averageScore > 89) {
                        System.out.println("평균 등급: A");
                    } else if (averageScore > 79) {
                        System.out.println("평균 등급: B");
                    } else if (averageScore > 69) {
                        System.out.println("평균등급 : C");
                    } else if (averageScore > 59) {
                        System.out.println("평균 등급 : D");
                    } else if (averageScore > 49) {
                        System.out.println("평균 등급 : F");
                    } else if (averageScore > 0) {
                        System.out.println("평균 등급 : N");
                    }
                }
                System.out.println("\n등급 조회 성공!");
            } else {
                throw new Exception("해당 고유번호를 가진 수강생이 존재하지않습니다");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
