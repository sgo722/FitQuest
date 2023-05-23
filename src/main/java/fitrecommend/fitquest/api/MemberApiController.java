package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.SurveyLocation;
import fitrecommend.fitquest.domain.Today;
import fitrecommend.fitquest.repository.MemberRepository;
import fitrecommend.fitquest.service.MemberService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApiController {

        private final MemberService memberService;
        private final MemberRepository memberRepository;

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
            member.setToday(Today.CHEST);

            Long memberId = memberService.join(member);

            JoinResponseDTO responseDTO = new JoinResponseDTO();
            responseDTO.setNextPage("survey");
            responseDTO.setId(memberId);

            return ResponseEntity.ok(responseDTO);
        }

        @GetMapping("/auth/member/{token}") //로그인(토큰의 고유값으로 조회해서 확인한다)
        public ResponseEntity<LoginResponseDTO> login(@PathVariable String token) {
            Member member = memberRepository.findByToken(token);

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
//            loginResponseDTO.setId(member.getId());

            if (member != null) {
                // 이미 저장된 멤버인 경우
                if (member.getSurvey() != null) {
                    if(member.getSurvey().getLocation() == SurveyLocation.GYM) { // 설문조사에서 헬스장으로 답한경우
                        loginResponseDTO.setNextPage("gym");
                        loginResponseDTO.setId(member.getId());
                    }
                    else{ // 설문조사에서 홈트로 답한경우
                        loginResponseDTO.setNextPage("home");
                        loginResponseDTO.setId(member.getId());
                    }
                } else { // 회원이지만 설문조사를 하지 않은 경우
                    loginResponseDTO.setNextPage("survey");
                    loginResponseDTO.setId(member.getId());
                }
            } else { // 신규 멤버인 경우
                loginResponseDTO.setNextPage("join");
            }
            return ResponseEntity.ok(loginResponseDTO);
        }

    @Data
    public static class JoinResponseDTO {

        private String nextPage;
        private Long id;

        public JoinResponseDTO(){

        }
    }

    @Data
    public static class LoginResponseDTO { // 로그인 회원에 대한 아이디를 넘겨줄 필요가있나?
        private String nextPage;
        private Long id;
        public LoginResponseDTO() {
            // 기본 생성자
        }
    }

    @Data
    public static class JoinRequestDTO {
        private String name;
        private String gender;
        private String email;
        private String birth;
        private String token;

        public JoinRequestDTO() {
            // 기본 생성자
        }

    }


}
