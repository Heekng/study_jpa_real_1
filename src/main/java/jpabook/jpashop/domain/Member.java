package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id") //컬럼명 지정
    private Long id;

    private String name;

    @Embedded //내장타입을 포함했다, 한쪽만 존재해도 된다.
    private Address address;

    @OneToMany(mappedBy = "member") //다대일 mappedBy: 나는 이것에 의해 매핑되었다.(읽기전용)
    private List<Order> orders = new ArrayList<>();
}
