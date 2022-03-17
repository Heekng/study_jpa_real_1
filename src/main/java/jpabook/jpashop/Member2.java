package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member2 {

    @Id @GeneratedValue //@GeneratedValue: 자동생성
    private Long id;
    private String username;

}
