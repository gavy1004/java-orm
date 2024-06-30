package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        // 트랜잭션 안에서 작업 필수
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //Member member = new Member();
            //member.setId(1L);
            //member.setName("helloA");
            //em.persist(member);

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloA-11");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }
        emf.close();
    }
}
