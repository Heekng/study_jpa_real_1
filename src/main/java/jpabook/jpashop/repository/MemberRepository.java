package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
//    @PersistenceContext
//    @Autowired //스프링 데이터 jpa 가 Autowired 를 사용할 수 있게 해준다.
    private final EntityManager em;

    /*
    public MemberRepository(EntityManager em) {
        this.em = em;
    }
     */

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        //createQuery: 쿼리(jqpl)와 리턴타입 클래스를 지정하고 getResultList로 리스트를 받아온다.
        // 멤버의 엔티티에 대해 조회하는 것
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
