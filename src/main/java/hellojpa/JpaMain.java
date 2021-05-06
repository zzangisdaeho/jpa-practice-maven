package hellojpa;

import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try {
//            쿼리는 JPQL
//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class).getResultList();
//            Member m = em.find(Member.class, 2L);

            //비영속
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            //영속
            em.persist(member);

            //수동 flush -> 쓰기 지연 Sql저장소의 쿼리가 날라감
            //쓰기 지연 저장소만 비워질 뿐 , 1차 캐시는 남아있음
//            em.flush();

//            영속성 지우기
//            특정
//            em.detach(member);
//            전채
//            em.clear();

            //커밋시에 자동으로 flush 실행되어 sql 발생
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
