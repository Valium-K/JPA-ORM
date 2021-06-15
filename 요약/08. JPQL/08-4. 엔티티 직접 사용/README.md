엔티티 직접 사용
=============
JPQL에서 엔티티를 직접 사용하면 SQL에서는 해당 엔티티의 기본키 값을 사용

```jpaql
select count(m) from Member m
```

```java
String qlString = "select m from Member m where m.team = :team";
List results = em.createQuery(qlString)
                .setparameter("team", team)
                .getResultList();
```
