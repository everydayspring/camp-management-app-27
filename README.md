# 💡 프로젝트 기능 정리

- 수강생 관리 (필수)
    - 수강생 정보(고유 번호, 이름, 과목 목록) 등록
    - 수강생 정보(고유 번호, 이름) 조회
- 점수 관리 (필수)
    - 수강생 과목별 시험 회차 및 점수 등록
    - 특정과목 회차별 등급 조회

- 수강생 관리 (추가)
    - 상태 관리 (Green, Red, Yellow 등급으로)
    - 정보 조회 (고유 번호, 이름, 상태, 선택 과목)
    - 정보 수정 (이름, 상태를 수정)
    - 상태별 수강생 조회 (고유 번호, 이름)
    - 수강생 삭제, 점수 기록도 삭제
- 점수 관리 (추가)
    - 수강생의 과목별 평균 등급 조회 (과목명, 평균등급)
    - 특성 상태 수강생들의 필수 과목 평균 등급 (이름, 필수 과목 평균 등급)

---
# 💡 요구사항 정리

- 수강생 관리 (필)
    - 고유번호 중복 x
- 점수 관리 (필)
    - 해당 회차 점수에 이미 점수가 존재하면 입력 불가
    - 회차 범위 1~10
    - 점수 범위 0~100



- 수강생 관리 (추)
    - 수강생 삭제시 점수 기록도 함께 삭제
- 점수 관리 (추)
    - x


---

# Template Method

- `void setInitData()`
    - `*StudentStore` ,`subjectStore`, `scoreStore`  초기화 함수*
        - `*StudentStore`* : 학생 정보 저장 공간, ArrayList로 받음
        - `*subjectStore*` : 과목 정보, 필수 학습이랑, 선택 학습이랑 같이 관리되고있음
        - `*scoreStore`*  : 점수 저장 공간 ,ArrayList로 받음
- `String sequence(String type)`
    - 입력되는 `type` 의 현 작업 index를 증가시키는 메소드
- `void displayMainView() throws InterruptedException`
    - 메인 화면 출력 및 페이지 입력중에 오류가 나면 프로그램을 종료시키는 메소드
    - 추가해야되는 예외 : ?
- `void displayStudentView()`
    - 초기화면에서 1번 수강생 관리 선택시 다음 선택 페이지 프린트.
- `void createStudent()`
    - 수강생 등록
    - 필요한 기능 : 등록 일자, 수강생이 원하는 과목
- `void inquireStudent()`
    - 수강생 목록 조회
    - 필요한기능
        - 수강생 목록 출력
- `void displayScoreView()`
    - 점수 관리 페이지
- `String getStudentId()`
    - 수강생 고유 번호 getter
- `void createScore()`
    - 수강생 점수 등록
    - getStudentId()를 이용해서 수강생의 고유 번호를 받아옴
    - 구현해야할 내용
        - 입력할 과목, 회차, 점수를 입력받아서
        - 등록하면 된다.
- `void updateRoundScoreBySubject()`
    - 수강생의 과목별 회차 점수 수정
    - getStudentId()를 이용해서 수강생의 고유 번호를 받아옴
    - 구현해야할 내용
        - 수정할 과목, 회차, 점수를 입력받아서
        - 수정하면 된다.
- `void inquireRoundGradeBySubject()`
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
    - 과목 나열된 순서대로 과목 고유번호 부여
    - ex) SU1 : Java
- 수강생 고유 번호 나누는 기준
    - 수강생 들어온 순서
    - ex) 홍길동 : ST1
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
# ETC

[다이어그램 링크](https://drive.google.com/file/d/1E_w3Wfiswu1zd-VvQwfa93AKxKGgSiWM/view?usp=sharing)

[수강생 관리 시스템 알고리즘 링크] https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=%E1%84%89%E1%85%AE%E1%84%80%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A2%E1%86%BC%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7.png#R%3Cmxfile%3E%3Cdiagram%20id%3D%22C5RBs43oDa-KdzZeNtuy%22%20name%3D%22Page-1%22%3E7V1bc9s4sv4t%2B6CqPQ92kQCvj5KczF4yW7PjzJmdR8VWbM3IlkdWLt6H89sPQBIU2WiSIAWAkM1UKpEoCpLQjb5%2B3T2jy4fvP%2BxXT%2Fc%2F7m7X2xnxbr%2FP6NWMkNAPffYfv%2FKSX%2FHDMM6v3O03t8W144XrzX%2FXxUWvuPplc7t%2Brt142O22h81T%2FeLN7vFxfXOoXVvt97tv9ds%2B77b1T31a3a2lC9c3q6189dfN7eE%2Bv5qQ%2BHj9b%2BvN3b34ZD9K81ceVuLm4pc8369ud98ql%2Bi7GV3ud7tD%2Fujh%2B3K95bsn9uXXv7%2F8uv3wR%2FTDP%2F79%2FOfql8U%2FP%2F7rfy%2Fyxd73eUv5E%2Fbrx4PepUm%2B9NfV9kuxX8VvPbyIDdzvvjzervki3owu7g8PW%2FbQZw9%2FXx8OLwXBV18OO3Zptz%2Fc7%2B52j6vth93uqbjv8%2B7xUNzm8%2Bfrx9s5Jyx7%2Fmm7u%2Fkjv%2FR%2Bs90Wn8GeFfcn7NnzYb%2F7o6QdX6AkBL95u%2Fq03i5WN3%2FcZV90udvu9uylx93jmi91y5ih%2BC3HL%2FfueJV92GH%2F8h%2B%2B2GUonv5WfhH25Op77dlL8UyRJgXtnndf9jfrlvtocTRW%2B7t1sd7X%2F8Q3v%2Fz864Ycfp%2FTX6P3f34%2F%2FF2sx39Xhb8Liv%2Bw3j2s2ZdkN%2BzX29Vh87V%2BCFbFWbor7zvyC3tQsEwP9qES%2BzyzX3Bo4SFOv2%2F3m8P6%2BmmVbcg3JnfqfFXlF%2FbTFnfb1fNzsesdzNCPKF%2FX%2B8P6e%2Bs2ilejQgYIKVg8%2FXaUKL4QE%2FcVaRJ4p298GxtUz%2B275SxJZmkyezefLbxZGmZX6Cxlr3rZxWCWsAeL2TyazZfZxatZyi4G2UV%2Fli6z2%2BLZIuFX0nezeSKR8vnb5mG7yg5XRqjiFb77N%2Feb7e2H1cvuC%2F%2F5jBNu%2FhDPFve7%2Fea%2F7P6VoHLGKAWZqVe745q%2Fs1hzv35m9%2FwkKOqDSz%2Buvtdu%2FLB6Pohvs9tuV0%2FPm0%2FZ9%2BNvfGCHa%2FO42B0Ou4fipg5WNMZPIeAnjKEChKFKLtPOUbIm8C8zHunLVoBf2I4cyoMLpPNnJvPBpdV2c%2FfInm7Xn%2Fnb%2BJZumDKfF5cfNre3mdh%2BZjTbPN59yG67Co5Xfi42i1%2Fasbd%2F3mbS4Z69cc1WWDztNo%2BHbPPCBfvLtnPJJX%2FIvuuSPfePz9lffvv%2BsNw9sq%2B%2F2mQ8sGYc9m3NuWyx3x1Wh9Wnkv97M1Pr0e5mpoJ5qCLvUFOsI2sBIlhnzjimwkMTxzjBMdHoHIPoL8gGFTOxoHRlZ%2BpGacWGC9KkasX5lx4pLzRYctmzn9b7Dftt632jJdG29Z1mW6BotlUoEiIEEdeUrbviE37iLHxUPzHQPpDOubVavKnqUoB1Sm1ULJSAdfJtkdbJWKb80cO5KJCYiF4qmzW5eCKzJMxuo7NkkkT2JVE6tiQScQBFSXTDfZHNTZsw6mczOiIRCDjJ5bp9RcJFmNZXotBibRAKbIdXL5XbCl5X%2F8aR1%2FrFpPuT9vulHwLewB7kX1mrSCOJxI7M4Trc3EtMub%2FfPXz68jyiD5OA%2FQnks5wgHAuVhD6jwtN9lEu7gpkRXlCLDl2SWJNd0UaNTruCuiVFIvzI9JYi1AcWChRHmoQI%2FJwuoSB9rzgxLxNkDaXkmTNLh86YTVAYQVfSWahEckYSIRcpre9n5MkyJMSESGossiYL4JM3ulf0U97rVqZQ3msibbW002gIM9Kw0Si3RL03eskfLIIicsDs%2B%2BLiO2HEe7N5OFxR1iLJPLb5fvWw2fLt%2Btt6%2B3XNbXkkOi2M%2FhtGLy7lsRB1bt2zZ9Hx2ccsP3IRGCS5DyyYUmNXaE48myo61a6hv28OleQNe%2FZbcR9%2FfFTM%2FElz5qZtu6uZmzaBUVXVbfzuiKqOYAwgHqiqY2BXSxFqw0EAH0%2BFAElRXskFNruSZMGA%2BVJcZBInnSWZFE8WxavswTxwVJyT%2Bq5jjrQpcY7TgWg%2F26X1Xbe8yzStcma2bcu7M65ueepQqBN43lQPbumvNUkA0wdXThu8%2B36zfjpsdo8S50yRONMCxYfsgEgUzBI3FpnzEQPRvrGgDx6iamS0GQ%2BdRobwTx0RVhSoKeqFA4UVjCpCttMnrFr3tZe3wh5zg%2BIqu7hs8FaiLRcCt5uv7OEdf%2FjDfs1ETHGdfdnKS8jdPzPuVr33N%2FZD2VlpuH1ymxo0LEWCEqjbVKI49MtCC1mKts1zRJz4KRAnYXAZDpQofgDXAguZNn%2FwQBMTJ8wnYeKBS5TcgQl54hKVKLng4Vo5Wj3wY%2Fn46fkJP%2FvZ2mGRFGV%2BTZrKns6lqmhwwgHyLr3KHyISeTDtNJ5DJAc7Ou3byr5JArLRiMUIcMphHw5jwzY9smkzEiQY8AblJEnSyzCpkSaAuknZ8kLWgsF3w6JSELFC1Y979uh1HCIC86hIZN7uIZId81e83QECvbW73TKMCXMtrvi%2F%2FMpShCW94mIe3qwBLOlskRkR8%2FlsnhbBz%2BJVZlmQTOlnV3JIFDcxGvyT%2FCMW2UfM82WX%2FBMX2TdJ59xIId5fG78NnbN%2Fqfgd5YdcFUvmBg0R19mb556wb67K95P29%2F%2BPxJqnpDY1qUYPsBmCjqAxwmelZ6Gf0UJEOVYMxcoGRn9%2B4VVNC37EL4pTzQnx%2B5fnw%2BbzS25iFvdAh5M5ao%2FoYp%2FKIp2Lmzxcx5fcPG4OG14VgC2pyOBHszX%2F8PoXavGLT%2FzJWR7%2BH6uvK%2FMfVSCq82NwxfeDHePiWMYZnJptVVSJKJj7KhnI8vppz31z4x8WZFv809z8J4X8k358uf73BxNukKQaFQVPqR2UJU8AoxeS3CnjFLXghTFHiGCBXMflDqaKHJM7GfAkyIIEmdZOS82YFNo4WXKkM38QZFlOKzLKlmC4Xt982W8OL5bk3c%2Fr282zJXH34%2B7xbnfVyEfnIYbkKOrYcojKfo2ygeExsdFkomK31zT1ceFFpqwzG3ruc8zY4Ki7Mbs1Cmtki5A0IBq6MAfrlb1%2FadveZkwngnGYE2I6JZ0txXQo5oy8PaoCwHQ8uBILuJuhYn60L2A6wj%2FGKP6Z9gMCqBcLzsaEFVG3oPvaGLFrIdNyRc6Uvl9tn19L9BKidDD1bDV6SeU05isOFsejbzeCoO5dniDsWH4xt13Dv0gEcyPJS%2BrWaIJEUe1mdQVqwB1UWhDU9Zjn%2Bx2a7JSCd1VUPFVtaCQ42hE9SIH%2BSoYqwgCUisW2i%2BPV0kq9erlMvX90FL1FgMVQqApWBE%2BM4csC2RvTU2M44bZ7qshAPdDmSvufQPbPiBrzFOnpumHCA2N5Oms%2B9eQYhaNGbw8UyFYuVeGovN9Uhl7gVm1Qf4t4deIo6xw1epsXYR1WOCooOQqAajPmSYJJgp0vv%2FmqJpU5hpORqKGaCEvIbH4lpNXEPWNwTzg698hRhkjk%2FJm8SQOY6k9TUS2QHvVeWQOdvOc3TJxkn5OQjguWOamlOad%2B1eZAS5kkpjXv2o8QgG%2BABW1DYz1lQjwWAzdTQGjT9EiLGnY3C9bO87Od6YdhaN4CRQxAvLCEyEI8uGTOwY24EVcFDcgbiweHTlQp99zvzuitYFhHgrLampXAMmLfclDW9xDYAy4JYIamAP4jFqFuzFD%2FUwl7S6FqD0vTGAMN%2BZ72c9lz97r7inhuQVEgEQc3FoEyWlrI%2BCFDqqWLQybAsrzGtqILcwh9oTLDWTLPrOaiuKVBKwvNOkK3oP4nFB5Q1YCMuepYr18vsC5UT8%2Bdc%2BTMQXUEQXiqRw6sYxnL52PdMU40gsyCtJSFtGOGUAed1XsfgOI02%2FwiB8ccBmkN8FKSbnFrFTbkI93Z3IVpDTBAYQ01YoBa3vBmG6TD0JehWG8cdqGLR0CjSqqaOJecRI1c0tAERmpzmxUOFYHe5CrjFRHumYK7ekZX%2BOroRFeQFz7SY0YpSig46vqjXII0cY55zhkdYVFGYyA8UGpZl%2FHJD3lPuolTrHPK6MgJX%2FhyA8omcxnzS9Hg45L9mXhoBB4aHw3hY425FItpCyaKJyYal4lGB0X4SGMqiRWGhpmMdPdVzwX4juUCYJNxGAdWHuQDIk0ShN1wqCm2iiR1AP6QSu3hsVAIxjqxuZE6Dbam9v03UrOW9qVBAGqxbU7awfcfV74m4CdvbCJPb96A5WXjj%2BRJEPP%2BRKVqcCaPCHR1DuXxFDWvOBuOKN6kayiHquJNYGrRchI%2BQSBvumw1IylBwVrdHOM5xTFQogyGbcCFFMfB9B65CD6m4FOjHUQSGXXVmm%2BcHEmDGtCvl%2FT7iHlkdc5M4gSC0%2FScGWXF6ZY%2BDODEh6EDZAJYdA3b7BrWh%2BJ3nEUCfoDPAyYBjN7E3Me6HeduSlIBAeK%2BZrNbP%2BXfhwt%2BhbE6aEARopw08gg%2Bg3ZKv48QkS7P6xml30k%2FDKs%2BkHm5W46o6Ri16fvPkgU%2Bq21cIsFBW1NS3LGTPz58gqpNHRbJzJZ5gDykvSxCqkXa02%2BeFjg0kmpOqwf0su7QYZMuiGgHZqf4Z7yWwY41M5OJAzddOToE55nYrpyjckDR9XFq%2FQ8T3GQk527Xi0L6TTbvuv6pOwN2sLaBFEuvYFN3pHmyGrdQycloVxW1qu4cGJM5H6nPxz50Dm5Ky%2FdmF%2Bf47EeoWwwkU4%2F81GPGYI2iWMG33cI1pOMtRk9xJacYu5Jv9nwpLjKCpxmRz5UUBJFOtmmBG%2BwDh6iW5Z8dQ1LPgjpYXN0ydcSnaZR8ucArB97U%2BtyU9bx1E5qJQ75gdluBWEXL6J2wGQbQPYiBjYfFReLQptWANUnuIG1O0aTQbotFg1J79cTzHaAeNienQcFl4VG%2F6USW8x3fBi0bEvEjUlI7CENx9xzzg2HyLxyKkYALRZaziD7Sazc7WnF2tK74v%2Fl4qVInJnNh%2BSz4%2BayqxSnnZdwdTRv4ZbycV9BgMjflvHI3xSsmjs4rMNwGuT1FvpV1THmazyjnFeDub0OO5GemOSY%2Bsc8n42dIkBambbaH8tSuC%2B%2FSC6JZD4iU4riTtgKM7j4xboFCL3zYhCuAuVRVk%2BcihQOFbXcXQlqYnsJJ%2FSjvLEGpNLFRmaLdzGGcpBYck7Okqi9hHtTPKezxZh0%2BEVqY6XmeVE0Gn9UEtpKSljJOVcRhmKg64xjXoVWxcpWmtJRxqspm%2FGgDMRwsm6UeBmDG2Euq0NJnQiM1VDjmbArWtHG4Mhd09xa3HKtBSlOyjk1ycIadt%2FcZfIwdtjyZNjndJ%2FJPefrOKDiTyLr6%2BiPpYpiAp1UnhrHPMONHaRLZDMBCdpOC0aJgSOichpHxfdcfq%2BH%2FSmaAD4eVE0WTnOgtJ9T5xhnFIjiwxicppliW3NRfXB01zMQw9hlmfMUiLIoKw%2FzG3se2ftItRnRLELimW5CS5%2BuPISYzykiDqM%2BdZMapDJSqx0DcUTJyouf6Y9LKMMw8iSbvZRyGcUDJTAkHPDRNfW2haXkp06HpVA5i6Bmd60KcGVR4Uaw9Y4A1gKPG2jP6aQOkB%2Bxm%2F%2F6A5OwnVB6ZcfCISgG4q4rJFIEBm6vDSGUnv7lKJi%2FIIMfpJKqwbd21yEO2Hja9w%2FaeIntvrBS57OX4qrtLlaTqbC9VyprulsiCax1RspSAucIiL967khoOKPbAQob1K%2FGQ3gWaWLLCkEf27GBJf2ainWMfllTt%2FEg8t6YHlg5TiQ0ZyJJ%2B1LGQcZbES46sTOy0VNDZX5uBeVWlwOmc2UmNKTML%2Flb77jly8oCdMbSpBjQVLffUID2HJGuaweo4MQcr9g6mME5M7Ti9Ey3NIK7B9DlwnwQd2l0Rq29R5SdO8SqJQQ%2BGof3BIdhJtd2zPnZVDjoss66LuYNKs%2FLGvJIxEZWPebCAea3npd29ywZajjYogpzXYN4hEYKw26iy2mGJILOz3W0MPITPYWdgMvqOax8jJe%2FeeWYNTigSg3Ft2yVFzBWSqBrlWYMFR6SnZUumpOjjW4Q80%2BN0oRLphPf0rdN8rAxCDJqk0gjLw1lWG4guP9H01Dbs46SyzpKr3D2zp5QB%2BnAt24cWm0Kr1QE9BzkcBSkkqT%2BQpCTqWso4RclEUYwMQw8pXEoaRm%2BcoAaHbtZlO6VUT3TgJJHvVoj%2FQuqJPjTWeCHFk6GFbZyTZAvtZsV9S5GQGtHb0WOc%2BcAKJkgrbLu%2BDjIFxZ2uyXo2XXIwx990JyZK9dvvzpgpcS0QCmzXoXIRikVpIeNiUY7bF2JR5pozPaFwi5HOiJZPKB585jHnaoOzHNMWiILJKJsY1YFCe%2BPlDLoUKX4mqyBJinCMZB1pZBkZROd3oVJldGQx4UM0Saj1VViIEohYvEU0TMiLquaijSNvV%2F1%2BdmzjCFiWrcM%2ByK80YcClyASOVw17H%2BXFKdUUKMeak3Fi5QrDEo0Mi%2FImysUok3bOcp%2BY1DyTIpFj20wqh4toLybNG9ymFT7COBdl2Bo7l0Iza5ebpIJz2zvXTExqnknT8ZlUzooHxpiUaXbGffzV%2BFhCMfGmm7zpI56MbeaU47NhQ96TeENTnxMvWeAlZJyObV6SI7Tn6uM4UOsHwb2IrAgx%2BgZwqIA%2BAkdI31V5L4spYV4PovUo6luIwu2Ed54i0ZafwtvNV%2Fbwjj%2F0yxBL9kr2LRv4BN7Iwwb5emx7KktK3KG7Vu14ctT7NRAA%2F8OmaYVWa9Ui7fB%2BfTCO9m3vBnJERUzelfB1Cgf%2FDM0QU4mRLDeILbcWlpfW5znVxo%2FkWkTmLhPY3AFnkyZwT8fHWEVymsCdTJ62jffC7o23myqI9GPbtCTz2re8O50nTAFHBKIfwyPnD5WIfgyR9dZBMyI52Z5fMm80YbazbDSdkdUEe9xj830ThEvNGU2x%2FmrqgUaTkCt%2BRap0VWHrlyuC9btNspg4JYGoDyVQmbfuX%2BLfuZRxCSQH1s%2FdKIuhbeAjoWHLVlksR99eoVXmd0tdu0ZZrH1%2BjBtGmRCejohEX%2FKDpC5Uw42ykqusiUQkvKEaXfREPypfBDnDv7gqKKWyDn90MUnlrX%2F%2Btjnc3Mt7ONzG1AQ9AsDCEFEydi1MbMi9LmEXRHFV3F16QdIq8viT0wD43UJQZLS6LUjBVo6IS4hJjaACVLYfAWAytO2%2FlsOv33AVj9Ssfyg1gy62ME1NobPfMjU7iTCUmtYLnQNzAQcm%2Fz1SDzpULoxYlFW6Eo5y0%2BDC%2BRBIetvDVUngsgNrpkAIG8Ju130N%2BrmvyrORRy6WD9wqEbrwwezMmKaXERl2UEHgObYu9Z3o7tWPYxRMfccMeCApYojEUS4q8zsWMs4uWIGTVMsEgT7KEC%2BDTb5diwfQEKdkhSMpxpLm4gFBP%2Beshyg4LXN0mihwTHkEnj7dEaTda5kWCGJbagIhO4YF7nfBAeotPf7Lbrv9mvLaa8yn57hLtMKCpzHCd%2BaCpyGRSPfaTHSa0M5dt2uih7Jf5ERbPk3YZNJgn4yX0etn377KGJXksJBoaHtX2fex7bDE2nFzr4Omvgfznso0jbpWMk5TzPLMoWPPT6vHGnGjP7%2Fs%2BAu8CcLFt2Jj%2BRg3bllxYnpcdF4U0pK%2FUFQLHd8qgGhOly2XyLl8Bxqgcy6UoyR17sEKK0MssxkYGxFJEoPgOSOTbsoj0B0NS9zqJOYHsOLA98NhgsinAVwqAkuZlkSJbBOPXcik15vpEMDKxz6EpBIlRVV%2FRrCqHX8maRj2ogscfSaE8LF275YpgZUDNgUFwDnJWu%2BnZVAAEC9T0cdYwLuiST%2BPIwRCS5NsTHgW%2BFt4Mtl0g837kwzE%2FEsAWRVsLhpY2An9Jfor9NybJldSSqEnnTJUSLC6I9qY%2BbsQIT60gR0l0lK2ASaJsYi0CThuD%2FZyjGl8SOlgaIWCH0RwKdspKuFVwAEwwL9CkLeuqvhEIg8SVsORt4EpjZHKHlZz8PgUL1Wb0qWSn4I4qgQbkVyeMwOKF6txlv2NajlNp1EUz9iqbk8tJD2mwpfoc4XSaRHtrcWWQ1PEEzVN0MyVLdqrxuBPjU6Vfjnz96KzThM35CtHlV4779pHVrlK2fASDB%2FzCeZLJoL%2BloiLe5NsP%2BcLUfHmSWcvLAaK8Z2vV7%2BVxMX9zoym88RVGkmnz0%2BjsY9fgmFRBniZ7eK2iX65lBWuJ39L2NItbeTavP4EB1Opkbajvt1EXqopppDTM5XQBy06tZNB2iMO58oCAGuWYkLZLgvgxtJ8KZBe7woaMmovguPhxCWuCPC9FnLBOfIRIqAtH1n92FIHWxj0d%2Fu7o0qpW8lmEkHfiQ7N8VAp1iDFoY0HCGS7wR3cky53l0TQYiuDvKNhcVI5nOcE9knXnsupUIpkxazuOfW0Z8Ll%2FTtLuEw4tFDvAk7OklYyLMCoGOBiGS7jdAP1s4HLSHPXkPatJUy1JiMgZk%2BjkDA4i69fKs9gfqY8Np2GGvVUDTXqOdb%2B0yOXBARqvIGWGh8kDwNzlk21khAdEdZWeQHcQCA4CihPOlK49CjLB7dhQcbS2YV7lIcAUAmZONCAfYQ0ARCdLNpyROZUI%2BYyKLO1pszNgjJtnBHjp7VaUoaVmBjDlVADQ17NefJ9FIRbYh8WlZGh9aVBQNsXMi3wkVmuFtPKA84c3DEsp4xRvsRLGjh0eN9sJr%2FmuV1OjxjWMqfcbtOXaQ%2F5NqYoU78%2BC6RBKB9zJ5UsWTqa5i1ZrYd%2FXyM11lhfyLf63AVjlEYCrHhOBCWJYgbMLODgTIiNTeSxTW3lfGdfu0fQsyi1bT7sUCbAmaVS%2F2i9hb1q3aTd5B9MMVjmHwRNqS75cct42SxdrpoZqYsDipy60xm601kClShWg8PIKE%2BEXN1%2B1rD0%2BVunPyoR7NIfSQ4gygON2jKNEh2rLxZ5OCatlc68GrpFdVGO%2BNW2CUckwjmewhzgWsHmDVhhk91kGjITUdptZ%2BoRzjecQUBBOh3cLktsgdC4lrsgUmTwobsZ7wFHlMDAE%2BITWz6iWCWbyGTO%2F%2B9flbSmYmpRKhOx06B9SPw3kDAfo0%2ByoBSJRp0mM%2BUNPEcAAjs6mgAI8krGxRoG7DMPQHBxOPa54g7QvKFt3AHVPwPPQdyBOC3ddphoj9%2BNOxDEc0S6vTbcATan4HXhDkoRfsa4A2z%2BwIQ7GIEznMMdGJhlYBB3oK4gHBP7rwd3EPQJqOnHHfQ%2Fcw7iDpCRDxPuoIXVzhd3gM2ZeAMggpMp5wCIIMCGFU4ggjPhn%2FFBBEEDuGwCEQwKuZ%2FMEuODCLAJMhOIwBb9xwcRNI2EedUgggF0cw5EgMx7cR1E0N9Pcg9EgE1M0RObGDd47Vhs4vWACEI5mOUwiKD%2FEXUPRIBMxmkEEZyQPBwLWTAgwkuk%2FA4KLQjRJm3mMgDINJ0TZam8h2cJLhg8OFbKE9ueQ0xDPLjELMZU9HRalE3284vZq0cnU%2FRQZAdTtBxxbRwY8UDAO8LSaXaROiEGnTrNMHFjgGzJUs4e1xPmMcFm8hGxfV77Abw6hw6PPzPSNX4pXYaSxkN7ukedjGecW%2FpBfl7lPLZuKijTE47ust2KKNTfoV%2FoDP%2FS80ldb%2FgR6dAc2bOf1vsN%2B2Xc4zGhThxjJsgCosyvLy%2FF0mxpu6wkfodZzIbdOeRuzR6GeoQObYFGgQCTYivGeQUrXLvivkCe4OcpgmXhkud5Sp42LPMMRGShKNay2QkHAcYtCTbVya5%2FEMnO9s3qeT0rfZmzH19Lide56ZoiUezpfsdDSMdzwX7p%2FY%2B72zW%2F4%2F8B%3C%2Fdiagram%3E%3C%2Fmxfile%3E
