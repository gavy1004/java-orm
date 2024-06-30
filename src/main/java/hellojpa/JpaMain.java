package hellojpa;

import jakarta.persistence.*;

import java.util.Iterator;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        // 트랜잭션 안에서 작업 필수
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("helloJPA");

            // 영속 상태
            // 영속성 컨텍스트 안에서 관리되는 상태
            em.persist(member);
            // 1차 캐시에 저장되어있으므로 select 쿼리 나가지 않음
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.getId() = " + findMember.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }
        emf.close();
    }
}
