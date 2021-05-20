게임프로그래밍 텀프로젝트
======

------
게임 컨셉
------
+ High Concept : 적의 공격과 움직임을 예상하여 적을 쓰러뜨려라!

+ 게임 장르 : 탑뷰 턴제 전략시뮬레이션 게임


------
게임 예시
------

+ 게임 예시

![Image 1](https://i.imgur.com/a2SVEaG.png)

    턴제로 진행되며 플레이어는 자신의 턴에 상대 플레이어의 위치를 볼 수 없음


------
개발 일정
------
+ 개발 일정
![Image 2](https://i.imgur.com/py7XLPQ.png)

    + 추가 구현 요소는 개발 중 추가적으로 개발 하고 싶은 요소입니다.

------
GitHub
------
	깃허브 통계입니다.
![Image 3](https://i.imgur.com/LAnGU8A.png)

	깃 오류로 인해 통계가 보이지 않아 업로드하지 못하였습니다. 
	프로젝트에 대한 커밋은 총 17개의 커밋이 있습니다.
------
수정 사항
------
	교수님의 추천으로 게임에 firebase를 이용하여 서버를 붙여보기로 하였습니다.
![Image 4](https://i.imgur.com/eYu8lGu.png)

	그로인해 맵을 조금더 넓게 사용할 수 있어 타일을 4X4에서 5X5로 확장하였습니다.

------
클래스 설명
------
![Image 5](https://i.imgur.com/oDzEL1p.png)
	체크 심볼, 타일, 플레이어(탱크), 버튼 클래스에 대한 설명입니다.


![Image 6](https://i.imgur.com/FGEYJcN.png)

	가장 중요한 터치입력 관련한 코드입니다. 플레이어 이동 및 체크 심볼 위치 조정등을 담당합니다.

![Image 7](https://i.imgur.com/FGEYJcN.png)

	체크 심볼을 결정하는 함수 코드입니다. x,y를 한꺼번에 전달하고 전달받기 위해 Pair 클래스를 만들어 사용하였습니다.


------
기타 사항
------
객체의 가시성 조절, 여러개의 씬을 관리하는 것 등의 내용을 다뤄주시면 개발할 때 도움이 될 것같습니다.

