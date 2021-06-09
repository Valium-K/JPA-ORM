package jpabook1.jpashop;

import jpabook1.jpashop.domain.Book;
import jpabook1.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook1");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }

        /**
         * validate every single field is not null.
         */
        emf.close();
    }
}
