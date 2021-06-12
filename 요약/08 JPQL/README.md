JPQL(Java Persistence Query Language)
====
* 객체지향 쿼리 언어로 엔티티를 대상으로 쿼리한다.
* 집합, 정렬(ex. count, sum, group by 등)기본 함수가능.

## 중간 처리
### TypeQuery: 반환 타입이 명확할 때 사용    
* `TypeQuery<T> query = em.createQuery("select m from Member m", T.class);`    

### Query: 반환 타입이 명확하지 않을 때 사용    
* `Query query = em.createQuery("select m.name, m.age from Member m");`    

### 파라미터 바인딩
* 이름 기준
```java
em.createQuery("SELECT m FROM Member m where m.username=:username");
    .setParameter("username", usernameParam);
```
* 위치 기준
```java
em.createQuery("SELECT m FROM Member m where m.username=?1");
    .setParameter(1, usernameParam);
```

### 프로젝션
* SELECT m FROM Member m -> 엔티티 프로젝션    
* SELECT m.team FROM Member m -> 엔티티 프로젝션(join 동작)    
* SELECT m.address FROM Member m -> 임베디드 타입 프로젝션    
* SELECT m.username, m.age FROM Member m -> 스칼라 타입 프로젝션    
    * 위의 스칼라 들의 배열을 오브젝트 타입으로 받을 수 있다. -> Object[]
    * 혹은 `SELECT new 패키지.경로.클래스(m.username, m.age) FROM Member m` 와 같이 new 연산자로 생성자를 쓰듯 받을 수 있다.

### 페이징
```java
String jpql = "select m from Member m order by m.namedesc"; // order by 필수
em.createQuery(jpql, Member.class)
        .setFirstResult(10)     // 조회 시작 위치
        .setMaxResults(20)      // 조회할 데이터 수
```
### JOIN
* 내부 조인: `SELECT m FROM Member m JOIN m.team t`
* 외부 조인: `SELECT m FROM Member m [LEFT|RIGHT] JOIN m.team t`
* 세타 조인(카티션 프로덕트): `select count(m) from Member m, Team t where m.username = t.name`

### JOIN - ON
* 조인 대상 필터링: 조인 전 필터링 한다.    
`SELECT m, t FROM Member m LEFT JOIN m.team t on t.name = 'A'`
  

* 연관관계 없는 외부 엔티티 조인    
`SELECT m, t FROM Member m LEFT JOIN Team t on m.username = t.name`
## 최종 처리
query.getResultList(): 결과가 하나 이상일 때 사용.
* 결과가 없으면: 빈 리스트 반환

query.getSingleResult(): 결과가 하나 일 때 사용.
* 결과가 없으면: `javax.persistence.NoResultException`
* 결과가 둘 이상이면: `javax.persistence.NonUniqueResultException`