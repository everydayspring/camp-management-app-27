
![cover (2)](https://github.com/user-attachments/assets/d7ef56cc-85c6-4d83-b321-9866f462e895)

# 💡 프로젝트 소개
본 프로젝트는 Java 강의를 통해 배운 내용을 기반으로 한 수강생 관리 시스템입니다. 본 시스템은 사용자의 입력을 Scanner 클래스를 활용하여 데이터를 입력받고, 이를 바탕으로 수강생 및 수강 과목의 점수를 관리 할 수 있습니다.


# 🧐 팀구성원
| 이 름| MBTI | 직책 |
| ---- |:----:|:----:|
|  이 봄  | INFJ | 팀장 |
| 김창민 | INFJ | 부팀장 |
| 김민주 | ENTJ | 부팀장 |
| 김태현 | INTP | 부팀장 |

# 🚀STACK

Environment

![인텔리제이](   https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![깃허브](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![깃이그노어](https://img.shields.io/badge/gitignore.io-204ECF?style=for-the-badge&logo=gitignore.io&logoColor=white)
![깃](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

Development

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

Communication

![슬랙](  https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![노션](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)


# 📊다이어그램
[프로젝트 구조도](https://drive.google.com/file/d/1E_w3Wfiswu1zd-VvQwfa93AKxKGgSiWM/view?usp=sharing)

[프로젝트 흐름도]( https://drive.google.com/file/d/1kn5cER_2Tbb8DieV9MowFvmJDkS6rxva/view?usp=sharing)


# ⚒️프로젝트 기능 정리

- **수강생 관리**
    -
  ![Screenshot 2024-08-07 at 1 34 30 PM](https://github.com/user-attachments/assets/6aff0eac-f9f2-4561-81b7-383a6cacec9a)

  
  수강생 정보(이름,상태,과목 목록) 등록

      사용자가 입력한 데이터 (이름,  상태, 수강 과목)를  받아  수강생 고유번호를 지정해  수강생 목록에 저장해줍니다.
      수강생의 이름은 10글자 이하 여야하고, 상태는 Green Red Yellow 중 선택하여 상태를 입력할 수 있습니다.
      과목은 필수 과목 3개 이상 선택과목 2 개 이상 선택하여 입력할 수 있습니다.
      


  수강생 목록 조회

      사용자가 입력한 모든 수강생의  정보목록을 보여줍니다.  목록에는 수강생의 이름과 고유번호가 표기됩니다. 만약 등록된 수강생이 하나도 없다면
      수강생 정보 목록 대신 예외처리된 문자를 보여줍니다.
        
  정보 수정 (이름, 상태를 수정)

      사용자가 정보를 수정하고싶은 등록된 수강생의 고유번호를 입력하면 해당 수강생의 이름 및 상태를 수정해 목록에 업데이트해줍니다.
      이때 사용자는 이름과 알맞은 상태를 띄어쓰기로 구분 지어 입력해주어야 합니다 (Ex: 홍길동 Red).
  상태별 수강생 목록 조회

      세 가지 상태 (Green Red Yellow) 중 사용자가 목록조회를 희망하는 상태 값을 입력하면 해당 상태 값을 가진 학생들의 전체 목록이 출력됩니다.
      
  수강생 삭제

      사용자가 등록된 수강생 중 삭제를 원하는 수강생의 고유번호를 입력할새 해당 수강생은 수강생 목록에서 제거됩니다.
      이때 만약 해당 수강생의 수강과목 중 등록된 점수가 있다면 해당 데이터도 같이 삭제됩니다.



- **점수 관리**
  -
  ![Screenshot 2024-08-07 at 1 34 30 PM](https://github.com/user-attachments/assets/7af4f97c-6bf0-4c1e-95b0-c880951cc915)

수강생 과목별 시험 회차 및 점수 등록

     등록된 수강생 중 사용자가 입력한 수강생 고유번호의 수강생의 과목 회차 및 점수를 등록합니다. 
     지정된 수강생의 수강 과목 목록 중 점수 등록을 원하는 과목의 고유 번호를 입력하여 과목을 지정합니다.  
     회차는 1 -10회차까지이며 점수는 0 - 100점까지의 범위 안의 값을 받습니다.

수강생의 과목별 회차 점수 수정

    사용자가 등록된 수강생의 특정 과목 회차 및 점수를 수정할 수 있습니다.
    사용자가 수정을 원하는 수강생의 학생 고유번호와 과목 고유 번호를 입력한 뒤 수정을 원하는 회차 번호를 입력한 뒤 수정된 점수 값을 입력하면 
    해당 수강생의 해당 회차의 점수 값이 업데이트됩니다.

    

수강생의 특정 과목 회차별 등급 조회
    
    사용자가 원하는 수강생의 특정 과목의회차별 등급을  계산하여  보여줍니다.
    사용자가 수강생의 고유 번호와  등급 조회를 희망하는 수강 과목의  고유번호를 입력할시 화차별 점수에 알맞은 등급을 계산하여 보여줍니다.


수강생의 과목별 평균 등급 조회
    
    사용자가 지정한 수강생의 특정과목의 평균점수를  계산하여 등급을 보여줍니다.
    사용자가 수강생의 고유 번호와  평균등급 조회를 희망하는 수강 과목의  고유번호를 입력할시 등록된 점수들의 평균값을 계산하여 알맞은 등급을 계산하여 보여줍니다.



  과목 목록
  
![Screenshot 2024-08-07 at 2 02 53 PM](https://github.com/user-attachments/assets/8db78529-1615-4136-ab28-478fa9dd0605)

필수/ 선택 과목 점수:등급 표

| 과목,등급 | A | B | C | D | F | N |
| --- | --- | --- | --- | --- | --- | --- |
| 필수 | 100-95 | 94-90 | 89-80 | 79-70 | 69-60 | 59~ |
| 선텍 | 100~90 | 89~80 | 79~70 | 69~60 | 59~50 | 49~ |
---



