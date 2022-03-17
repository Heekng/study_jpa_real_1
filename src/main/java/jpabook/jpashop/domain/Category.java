package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", // joinTable: 다대다 관계에서는 JoinTable을 사용
            joinColumns = @JoinColumn(name = "category_id"), //테이블과 테이블 사이를 연결시켜주는 아이디
            inverseJoinColumns = @JoinColumn(name = "item_id") //아이템쪽으로 열결되는 아이디
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
