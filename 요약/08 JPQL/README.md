JPQL(Java Persistence Query Language)
====
객체지향 쿼리 언어로 엔티티를 대상으로 쿼리한다.

## 중간 처리
TypeQuery: 반환 타입이 명확할 때 사용    
* `TypeQuery<T> query = em.createQuery("select m from Member m", T.class)`    

Query: 반환 타입이 명확하지 않을 때 사용    
* `Query query = em.createQuery("select m.name, m.age from Member m")`    

> Member m 은 Member as m의 축약형 이다.

## 최종 처리
query.getResultList(): 결과가 하나 이상일 때 사용.
* 결과가 없으면: 빈 리스트 반환

query.getSingleResult(): 결과가 하나 일 때 사용.
* 결과가 없으면: `javax.persistence.NoResultException`
* 결과가 둘 이상이면: `javax.persistence.NonUniqueResultException`