cascade
=======
부모 엔티티를 persist할 때 관리되는 다른 엔티티도 함께 persist 한다.
* 대상 엔티티의 소유자가 하나일 때(단일 엔티티에만 종속적일 때) 사용해야 한다.
* 부모와 대상이 비슷한 라이프 사이클을 가질 때 사용해야한다.

`@OneToMany(cascade = CascadeType.ALL)`
  
# 고아객체
참조되는 객체가 지워질 때 DB에서도 remove 한다.
* 참조하는 곳이 한 곳 일 때 사용해야한다.
* 특정 엔티티가 개인 소유할 때만 사용해야한다.
* @OneToOne, @OneToMany만 사용가능 
* 참조하는 객체를 지우면 참조되는 모든 객체가 지워진다.(CascadeType.REMOVE 과 같이 동작.)

`@OneToMany(orphanRemoval = true)`