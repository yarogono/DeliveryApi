# 스프링 부트를 사용한 API 기능 구현

### 시작 전 DB 설계
![캡처](https://user-images.githubusercontent.com/70641418/144538924-e616787f-3aee-475a-b01f-215dd7af1628.JPG)
https://www.erdcloud.com/d/5dcNW9ZfKchuXPP9B

- ERDCloud 웹사이트를 활용해서 ERD 설계
- 항해99 동기분들과 ERD 설계 공유  
  
<br/>
<br/>
  
![용빈님 DB설계](https://user-images.githubusercontent.com/70641418/144539419-68491e49-09b9-42aa-9ab4-a434f1bf4cdb.JPG)



--------------------------
# 구현하면서 느낀 점

- 같은 요구사항을 받아도 나오는 결과가 천차만별이다.
- DB설계를 제대로 하지 않으면 구현단계에서 끔찍한 결과를 초래한다.   
   => 결국 마지막에 갈아 엎어야 하는 상황까지 올 수 있다.
- 각 Entity의 연관 관계를 제대로 설정하지 않으면 순환참조에 빠지게 된다.
- JPA를 사용해 코드를 구현하면서 H2를 사용해서 직접 눈으로 설계된 테이블을 확인하는 방식이 큰 도움이 되었다.
