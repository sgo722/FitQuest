package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.SurveyLocation;
import fitrecommend.fitquest.repository.MemberRepository;
import fitrecommend.fitquest.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApiController {

        private MemberService memberService;
        private MemberRepository memberRepository;

        @PostMapping("/join") // 회원 가입
        public ResponseEntity<JoinResponseDTO> join(@RequestBody JoinRequestDTO joinRequestDto) {
            String token = joinRequestDto.getToken();
            String name = joinRequestDto.getName();
            String gender = joinRequestDto.getGender();
            String email = joinRequestDto.getEmail();
            String birth = joinRequestDto.getBirth();

            Member member = new Member();
            member.setToken(token);
            member.setName(name);
            member.setGender(gender);
            member.setEmail(email);
            member.setBirth(birth);

            Long memberId = memberService.join(member);

            JoinResponseDTO responseDTO = new JoinResponseDTO();
            responseDTO.setNextpage("survey");
            responseDTO.setId(memberId);

            return ResponseEntity.ok(responseDTO);
        }

        @GetMapping("/auth/member") //로그인
        public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto) {
            Long id = loginRequestDto.getId();
            Member member = memberRepository.findOne(id);

            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setId(id);

            if (member != null) {
                // 이미 저장된 멤버인 경우
                if (member.getSurvey() != null) {
                    if(member.getSurvey().getLocation() == SurveyLocation.GYM) { // 설문조사에서 헬스장으로 답한경우
                        responseDTO.setNextpage("gym");
                    }
                    else{ // 설문조사에서 홈트로 답한경우
                        responseDTO.setNextpage("home");
                    }
                } else { // 회원이지만 설문조사를 하지 않은 경우
                    responseDTO.setNextpage("survey");
                }
            } else { // 신규 멤버인 경우
                responseDTO.setNextpage("join");
            }
            return ResponseEntity.ok(responseDTO);
        }

    @Data
    static class JoinResponseDTO {

        private String nextpage;
        private Long id;
    }

    @Data
    static class LoginResponseDTO { // 로그인 회원에 대한 아이디를 넘겨줄 필요가있나?
        private Long id;
        private String nextpage;
    }

    @Data
    public class JoinRequestDTO {
        private String name;
        private String gender;
        private String email;
        private String birth;
        private String token;
    }

    @Data
    public class LoginRequestDTO {
        private Long id;
    }

}
