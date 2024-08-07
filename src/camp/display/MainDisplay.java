package camp.display;

import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;

import java.util.*;

public class MainDisplay {
    public static Scanner sc = new Scanner(System.in);

    // 메인 메뉴
    public static void displayMainView(StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) throws InterruptedException {
        StudentDisplay studentDisplay = new StudentDisplay();
        ScoreDisplay scoreDisplay = new ScoreDisplay();
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentDisplay.display(studentMap, subjectMap); // 수강생 관리
                case 2 -> scoreDisplay.display(studentMap, subjectMap, scoreMap); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                case 27 -> flag = hiddenExit();
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    static boolean hiddenExit() throws InterruptedException {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(" ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⣄⡀⠀⠀⡠⠞⠛⢦⣠⢤⡀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢠⠏⠀⠀⢱⡀⣸⠁⠀⡴⠋⠀⠀⣹⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠋⠉⢿⢀⡤⠶⣴⠇⣯⠀⣼⠁⠀⢀⡴⠷⣄");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠁⠀⣀⡾⠋⠀⠀⢹⣼⠁⢠⡇⠀⡴⠋⠀⠀⡼");
        System.out.println("⠀⠀⠀⠀⢠⠊⠑⢦⠀⡴⠋⢀⣠⠞⠉⠀⠀⠀⣠⣿⠧⣄⡾⠁⡼⠁⣀⣤⠾⡁");
        System.out.println("⠀⠀⠀⠀⢸⠀⠀⣨⠟⠁⢠⡞⠁⠀⠀⠀⣠⡾⠛⠁⠀⣿⠃⣰⠃⣴⠋⠀⠀⣷");
        System.out.println("⠀⠀⠀⠀⣸⢠⠞⠁⠀⢠⠏⠀⠀⢀⡴⠋⠁⠀⢀⣠⡴⠿⣶⡇⢰⠇⠀⠀⢠⠇");
        System.out.println("⠀⠀⠀⢠⢿⠏⠀⠀⠀⠉⠀⠀⣠⠞⠁⠀⡴⠚⠉⠁⠀⢀⡟⠀⣼⠀⠀⠀⢸⠀");
        System.out.println("⠀⠀⠀⡾⣼⢀⠀⠀⠀⠀⠀⠈⠉⠀⣠⠞⠁⠀⠀⢀⡴⠋⠙⢼⠃⠀⠀⠀⣸⠀");
        System.out.println("⠀⠀⠀⡇⠉⡎⠀⣰⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⡼⠉⠀⠀⠀⠘⠂⠀⠀⣠⠇⠀");
        System.out.println("⠀⠀⠀⡇⢸⠀⣰⠃⠀⡴⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⣠⠖⠁⠀⠀");
        System.out.println("⠀⠀⢸⠁⡏⢠⠃⢀⠞⠀⠀⠀⠀⠀⠀⢸⠁⠀⠀⠀⠀⢀⣠⠖⠋⠁⠀⠀⠀⠀");
        System.out.println("⠀⠀⡞⠀⠃⡎⢀⠏⠀⠀⠀⠀⠀⠀⢀⡏⠀⣀⡤⠴⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⡴⢺⠇⠀⠀⠀⠞⠀⠀⠀⠀⠀⠀⢀⡾⠒⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⡇⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⢳⡀⠘⢦⡀⠀⠀⠀⠀⠀⠀⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠳⣄⠀⠙⠲⣤⣀⣠⠴⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠈⠓⠦⣄⣀⡠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("=================만든 사람=================\n");
        Thread.sleep(500);

        System.out.println("┍━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┑");
        System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆ 김 민 주 ☆ﾟ･:*:･｡,★ﾟ･:*:･ ");
        System.out.println("┕━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┙");
        Thread.sleep(400);

        System.out.println("┍━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┑");
        System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆ 김 창 민 ☆ﾟ･:*:･｡,★ﾟ･:*:･ ");
        System.out.println("┕━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┙");
        Thread.sleep(400);

        System.out.println("┍━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┑");
        System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆ 김 태 현 ☆ﾟ･:*:･｡,★ﾟ･:*:･ ");
        System.out.println("┕━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┙");
        Thread.sleep(400);

        System.out.println("┍━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┑");
        System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆  이 봄  ☆ﾟ･:*:･｡,★ﾟ･:*:･ ");
        System.out.println("┕━━━━━━━━━━━━━━━━ ⋆⋅☆⋅⋆ ━━━━━━━━━━━━━━━━━┙\n");
        Thread.sleep(800);


        System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆ 감사합니다 ☆ﾟ･:*:･｡,★ﾟ･:*:･ \n\n");
        return false;
    }
}