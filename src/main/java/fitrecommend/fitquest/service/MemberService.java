package fitrecommend.fitquest.service;

import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMembers = memberRepository.findByToken(member.getToken());
        if (findMembers != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
