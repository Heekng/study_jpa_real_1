package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // 업데이트와 유사, 병합
            // 준영속 상태의 엔티티를 영속 상태의 엔티티로 변경
            // merge한 item은 영속상태로 변경되는 것이 아니기 때문에 리턴되는 영속 상태의 엔티티를 사용해야 한다.
            // 또한 선택적인 필드 변경이 아닌 전체 값 변경을 진행한다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
