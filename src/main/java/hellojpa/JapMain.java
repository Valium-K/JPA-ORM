package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JapMain {
    public static void main(String[] args) {
        // 앱 로딩 시점에 딱 하나만 만들어서 공유한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜젝션 단위 - DB connection을 얻어서 query 를 날리고 종료되는 일괄적인 단위는 꼭 EntityManager를 만들어야 한다.
        // 즉, 트랙젝션 처럼 만들고 끝나면 버린다.
        // 쓰래드간 공유하면 무결성을 보장 못 한다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            ////////// insert //////////
            Member member = new Member();
            member.setId(1L);
            member.setName("memberA");

            em.persist(member);
            transaction.commit();

            ///////////// update ////////////

            Member found = em.find(Member.class, 1L);
            found.setName("Member1");

            // JPA가 em.find로 찾은 객체가 수정되면 자동으로 update 문을 만들어 commit한다.
            // em.persist(member);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
