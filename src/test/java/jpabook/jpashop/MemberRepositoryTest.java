package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository2 memberRepository;

    @Test
    @Transactional // @Transactional 어노테이션이 테스트에 있으면 해당 테스트가 끝난 이후에 롤백을 한다.
    @Rollback(value = false) //@Rollback(false) 어노테이션이 테스테 있으면 @Transactional 어노테이션이 있더라도 테스트까 끝나고 롤백을 하지 않는다.
    public void testMember() throws Exception {
        //given
        Member2 member = new Member2();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member2 findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(savedId);
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

    }
}