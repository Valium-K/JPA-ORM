엔티티 매핑
==========

## 객체와 테이블 매핑
### @Entity
* `@Entity`가 붙은 클래스는 JPA가 관리하는 Entity가 된다.
* 즉, 객체와 테이블은 `@Entity`로 매핑된다.
    > * 기본 생성자 필수.
    > * final class, enum, interface, inner class 에는 `@Entity`를 사용 못 한다.
  
## 필드와 칼럼 매핑
### 매핑 애노테이션
  * `@Column`: 컬럼 매핑
    * name: 필드와 매핑할 테이블의 칼럼 이름
    * nullable: null 제약조건
    * columnDefinition = DDL: DB 칼럼 정보를 DDL로 직접 정의
    * length: String의 길이 정의
  * `@Temporal`: 날짜 타입 매핑
    * DB는 날짜, 시간, 날짜시간 을 구분해서 사용한다.
    * Java의 Date는 위 3가지가 통합돼 있으므로 TemporalType로 구분하여 @Temporal 을 사용한다.
    * Java8 이상을 사용시 `LocalDate`, `LocalDateTime`을 사용하면 `@Temporal`을 사용하지 않아도 된다.
  * `@Enumerated`: enum 타입 매핑
    * 참고로 대부분의 DB는 enum 타입이 없다.
    * EnumType은 ORDINAL, STRING 이 있지만 STRING 사용이 **적극 권장**된다.
  * `@Lob`: BLOB, CLOB 매핑
    * 문자면 CLOB, 나머지는 BLOB 매핑된다.
  * `@Transient`: 해당 필드를 칼럼과 매핑시키지 않음.

## DB 스키마 자동 생성
* DDL을 애플리케이션 실행 시점에 테이블 삭제 후 자동생성
    * DB에 맞는 언어로 자동생성됨 ex. 오라클, MySQL 등...
    * `<property name="hibernate.hbm2ddl.auto" value="create" />`
        * value 속성    
          create: 테이블 drop 후 생성    
          create-drop: create 후 종료시점에 drop    
          update 변경분만 반영(운영DB에서 사용하면 안됨 - 지우는 기능 없음)    
          validate: 엔티티와 테이블이 정상 매핑되었는지만 확인
> 운영서버가 아닌 개발 장비에서만 사용이 권장됨.    
> 사용한다면 직접 다듬어야 하기에 의미가 약해짐. 

## 기본 키 매핑
* `@Id`: 해당 필드를 Id로 사용 - 값을 직접 생성
* `@GeneratedValue`: Id 값을 자동생성
    * ### strategy   
        * GenerationType.IDENTITY: 기본 키 생성을 DB에 위임 (MySQL의 auto_increment와 같다.)    
          > 영속성 컨텍스트의 1차 캐시는 id값으로 관리되는데 id값은 db가 생성하므로    
          GenerationType.IDENTITY 는 persist 시점에 insert query가 강제로 나가는 단점이 있어 버퍼링을 사용 못 한다.    
          그러나 일반적인 상황에서는 성능차이가 크지는 않다.
        * GenerationType.SEQUENCE: DB에서 기본키 값을 생성하는 SEQUENCE를 정의해 사용.
            ```java
            @Entity
            @SequenceGenerator(
                    name = "MEMBER_SEQ_GENERATOR",  // SequenceGenerator 이름
                    sequenceName = "MEMBER_SEQ",    // DB의 sequenceName
                    initialValue = 1, allocationSize = 50)
            public class Member {
                @Id
                @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
                private Long id;
            }
            ```
            > persist 시 1차 캐시에 저장되야 하므로 DB의 Sequence에서 Id값을 받아온다.    
              이 때 allocationSize 를 알고 있으므로 메모리에서 직접 count를 해 네트워크는 allocationSize 당 1번만 타면 된다.       
              그러므로 IDENTITY와 다르게 버퍼링이 가능하다.
          
        * GenerationType.TABLE: SEQUENCE의 역할을 하는 TABLE을 따로 만들어 사용. 잘 사용하지 않는다.
> **기본키는 비지니스와 상관없는 값을 사용해야한다.**
> **권장은 Long형 + 대체키 + 키 생성전략 이다.** 

## 연관관계 매핑