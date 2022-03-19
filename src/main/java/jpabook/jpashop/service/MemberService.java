package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // jpa 의 모든 행위들은 트랜잭션 위에서 실행되어야 한다.
//@AllArgsConstructor //필드의 모든 것을 가지고 생성자를 만들어줌, lombok
@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자를 만들어준다.
public class MemberService {

//    @Autowired //필드인젝션
    //final 권장, 컴파일 시점에 값 체크를 할 수 있기 떄문에
    private final MemberRepository memberRepository;

    /*
    @Autowired //setter 인젝션
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /*
//    @Autowired //생성자 인젝션, 생성자가 하나인 경우에는 Autowired 가 없어도 스프링이 자동 인젝션을 해준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */


    /**
     * 회원 가입
     */
    @Transactional //readonly 가 기본값 false 로 들어감
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 확인
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return
     */
//    @Transactional(readOnly = true) // 성능 향상
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
