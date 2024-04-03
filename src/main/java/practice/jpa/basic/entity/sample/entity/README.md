양방향 관계는 가급적 사용하지 않는다.

1 -> N 조회가 정말 필요한 경우에만 사용한다.

---

application.properties

spring.jpa.hibernate.ddl-auto = update

없는 테이블 => JPA에서 생성

있는 테이블 => 수정 사항만 반영

>주의
> 
> string name; => Long name;
> 
> 데이터 타입은 감지하지 못함
> 
> 직접 변경하거나, 테이블 삭제 후 재실행