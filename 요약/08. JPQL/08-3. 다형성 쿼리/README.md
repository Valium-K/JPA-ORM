다형성 쿼리
=========

## TYPE
조회 대상 을 특정 자식으로 한정

```jpaql
select i from Item i where type(i) IN (Book, Movie)
```

## TREAT(JPA 2.1)
상속 구조에서 부모 타입을 특정 자식 타입으로 다룸
```jpaql
select i from Item i where treat(i as Book).auther = 'kim'
```
