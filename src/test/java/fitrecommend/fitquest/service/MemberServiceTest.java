//package fitrecommend.fitquest.service;
//
//import fitrecommend.fitquest.domain.Member;
//import fitrecommend.fitquest.repository.MemberRepository;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class MemberServiceTest {
//
//    @Autowired MemberRepository memberRepository;
//    @Autowired MemberService memberService;
//    @Autowired EntityManager em;
//
//
//    @Test
//    public void 회원가입() throws Exception {
//        //given
//        Member member1 = new Member();
//        Member member2 = new Member();
//        member1.setToken("123");
//        member2.setToken("123");
//
//
//        //when
//        Long savedId1 = memberService.join(member1);
//        Long savedId2 = memberService.join(member2);
//
//
//        //then
//        Assertions.assertEquals(member1, memberRepository.findOne(savedId1));
//        Assertions.assertEquals(member2, memberRepository.findOne(savedId2));
//    }
//}