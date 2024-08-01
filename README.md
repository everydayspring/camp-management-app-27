# camp-management-app-27


# 팀프로젝트 기획 회의

## 팀명 “이칠피티”

27조와 ChatGPT를 합친 이름입니다.

ChatGPT 처럼 유용하고 도움이 되는 개발자를 목표로 하는 팀

## [다이어그램](https://drive.google.com/file/d/1E_w3Wfiswu1zd-VvQwfa93AKxKGgSiWM/view?usp=sharing)

## [GitHub](https://github.com/everydayspring/camp-management-app-27)

TO DO

- [x]  팀명
- [ ]  제공 및 필요한 기능 정리
- [ ]  기능 흐름도
- [ ]  git 연결

<aside>
💡 FEEDBACK

- code formatting
- git commit 목적, 작업 상세히 작성
- 필수 요구사항 먼저 진행 후 추가 요구사항 진행
- 요구사항별 단계 나눠서 진행하기
- 팀원 전원 코드 작성
- 가능하면 코드 리뷰 후 merge
</aside>

-

---

<aside>
💡 프로젝트 기능 정리

---

- 수강생 관리 (필)
    - 수강생 정보(고유 번호, 이름, 과목 목록) 등록
    - 수강생 정보(고유 번호, 이름) 조회
- 점수 관리 (필)
    - 수강생 과목별 시험 회차 및 점수 등록
    - 특정과목 회차별 등급 조회

---

- 수강생 관리 (추)
    - 상태 관리 (Green, Red, Yellow 등급으로)
    - 정보 조회 (고유 번호, 이름, 상태, 선택 과목)
    - 정보 수정 (이름, 상태를 수정)
    - 상태별 수강생 조회 (고유 번호, 이름)
    - 수강생 삭제, 점수 기록도 삭제
- 점수 관리 (추)
    - 수강생의 과목별 평균 등급 조회 (과목명, 평균등급)
    - 특성 상태 수강생들의 필수 과목 평균 등급 (이름, 필수 과목 평균 등급)
</aside>

<aside>
💡 요구사항 정리

- 수강생 관리 (필)
    - 고유번호 중복 x
- 점수 관리 (필)
    - 해당 회차 점수에 이미 점수가 존재하면 입력 불가
    - 회차 범위 1~10
    - 점수 범위 0~100

---

- 수강생 관리 (추)
    - 수강생 삭제시 점수 기록도 함께 삭제
- 점수 관리 (추)
    - x
</aside>

---

# Template Method

- void setInitData()
    - `*StudentStore` ,`subjectStore`, `scoreStore`  초기화 함수*
        - `*StudentStore`* : 학생 정보 저장 공간, ArrayList로 받음
        - `*subjectStore*` : 과목 정보, 필수 학습이랑, 선택 학습이랑 같이 관리되고있음
        - `*scoreStore`*  : 점수 저장 공간 ,ArrayList로 받음
- String sequence(String type)
    - 입력되는 `type` 의 현 작업 index를 증가시키는 메소드
- void displayMainView() throws InterruptedException
    - 메인 화면 출력 및 페이지 입력중에 오류가 나면 프로그램을 종료시키는 메소드
    - 추가해야되는 예외 : ?
- void displayStudentView()
    - 초기화면에서 1번 수강생 관리 선택시 다음 선택 페이지 프린트.
- void createStudent()
    - 수강생 등록
    - 필요한 기능 : 등록 일자, 수강생이 원하는 과목
- void inquireStudent()
    - 수강생 목록 조회
    - 필요한기능
        - 수강생 목록 출력
- void displayScoreView()
    - 점수 관리 페이지
- String getStudentId()
    - 수강생 고유 번호 getter
- void createScore()
    - 수강생 점수 등록
    - getStudentId()를 이용해서 수강생의 고유 번호를 받아옴
    - 구현해야할 내용
        - 입력할 과목, 회차, 점수를 입력받아서
        - 등록하면 된다.
- void updateRoundScoreBySubject()
    - 수강생의 과목별 회차 점수 수정
    - getStudentId()를 이용해서 수강생의 고유 번호를 받아옴
    - 구현해야할 내용
        - 수정할 과목, 회차, 점수를 입력받아서
        - 수정하면 된다.
- void inquireRoundGradeBySubject()
    - 특정 과목 회차별 등급 조회 목적 메소드
    - getStudentId()를 이용해서 수강생의 고유 번호를 받아옴
    - 구현해야할 내용
        - 조회할 특정 과목을 입력받고
        - 해당 과목들의 등급을 출력

---

# 모델 정보 구조 (예시)


<aside>
💡 관계

- 수강생 - 점수
    - `수강생 고유 번호` 로 두 테이블 접근 가능
- 점수 - 과목
    - `과목 고유 번호`로 두 테이블 접근 가능
- 세 table 모두 Map으로? 흠..
    - 수강생
      Map<Integer,ArrayList<String>>
        - Key : 고유번호
        - Value : 이름, 과목 목록..
    - 점수
      ??

    - 과목
      Map<Integer,ArrayList<String>>
        - Key : 고유번호
        - Value : 과목 타입
</aside>

<aside>
🙏🏻 고유번호 나누는 기준

- 과목 고유 번호 나누는 기준
    - 과목 이름 앞에서 두글자 따고, 큰틀, 마지막은 타입
    - ex) java, 큰틀:programming, 타입:a → japroa
- 수강생 고유 번호 나누는 기준
    - 등록한 연도, 달, 들어온 순서
    - ex)2024년, 07월, 23번째 → 240723
</aside>

- Class Score 과목
    - String scoreId

- 필수/ 선택 과목 점수:등급 표

| 과목,등급 | A | B | C | D | F | N |
| --- | --- | --- | --- | --- | --- | --- |
| 필수 | 100-95 | 94-90 | 89-80 | 79-70 | 69-60 | 59~ |
| 선텍 | 100~90 | 89~80 | 79~70 | 69~60 | 59~50 | 49~ |

수강생

| 고유 번호 | 이름 | 과목 목록 |
| --- | --- | --- |
|  |  | Java |
|  |  | 객체지향 |
|  |  | Spring |
|  |  | JPA |
|  |  | MySQL |
|  |  | 디자인 패턴 |
|  |  | Spring Security |
|  |  | Redis |
|  |  | MongoDB |

---