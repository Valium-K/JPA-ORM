벌크 연산
=======
쿼리 한번으로 여러 row가 변경 될 시 사용한다.    
executeUpdate()를 사용하며 반환값은 영향받은 엔티티의 수이다.    
update, delete 문이 지원하며 hibernate에서 insert도 지원한다.


* 벌크연산은 영속성 컨텍스트를 무시하고 DB에 직접쿼리한다.    
    * 그래서 벌크 연산을 먼저 수행 하거나
    * 벌크 연산 수행 후 영속성 컨택스트 초기화( em.clear() )를 한다.
  

           
