package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository2 {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member2 member) {
        em.persist(member);
        return member.getId();
    }

    public Member2 find(Long id) {
        return em.find(Member2.class, id);
    }
}
