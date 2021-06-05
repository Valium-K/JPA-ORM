package jpabook1.jpashop;

import jpabook1.jpashop.domain.Book;
import jpabook1.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook1");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {
            Member member = new Member();
            member.setName("asdf");

            em.persist(member);

            //em.flush();
            //em.clear();

            Member foundMember = em.getReference(Member.class, 1L);

            System.out.println(foundMember.getClass());
            System.out.println(foundMember.getId());
            System.out.println(foundMember.getName());



        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }


}
