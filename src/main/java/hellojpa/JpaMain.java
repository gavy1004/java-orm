package hellojpa;

import jakarta.persistence.*;

import java.util.Iterator;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 안에서 작업 필수
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            /*
            // 영속
            Member findMember1 = em.find(Member.class, 101L);
            // 1차 캐시에서 조회하므로 쿼리 나가지 않음
            Member findMember2 = em.find(Member.class, 101L);
            // 동일한 객체 주소 참조
            System.out.println("(findMember1 == findMember2) = " + (findMember1 == findMember2));
            */

            // insert 쿼리 모아서 한번에 insert
            //Member mem1 = new Member(130L, "test");
            //Member mem2 = new Member(131L, "test");
            //em.persist(mem1);
            //em.persist(mem2);
            //System.out.println(" ================= ");
            // batch

            // 엔티티 수정 변경감지 (Dirty Checking)
            //Member member = em.find(Member.class, 130L);
            //member.setName("zzz");
            // em.update(member) 라는 코드 없어도 됨

            // 엔티티 삭제
            // em.remove(member);

            // 플러시
            // 영속성 컨텍스트의 변경내용을 데이터 베이스에 반영
            // 영속성 컨텍스트를 비우지 않음 
            // 커밋직전에만 동기화 하면됨
            // 영속성 컨텍스트를 플러시 하는 방법
            // em.fluch(); 직접 호출
            // tx.commit(); 시 자동 호출
            //Member member = new Member(200L, "dfs200");
            //em.persist(member);
            //em.flush();
            //System.out.println(" ================= ");
            //tx.commit();

            // 준영속 상태

            Member member = new Member();
            member.setId(142L);
            member.setName("C");

            member.setRoleType(RoleType.GUEST);

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }
        emf.close();
    }
}
