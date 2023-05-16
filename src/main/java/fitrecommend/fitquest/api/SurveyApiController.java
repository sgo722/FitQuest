package fitrecommend.fitquest.api;


import fitrecommend.fitquest.domain.GymType;
import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.Survey;
import fitrecommend.fitquest.domain.SurveyLocation;
import fitrecommend.fitquest.repository.MemberRepository;
import fitrecommend.fitquest.repository.SurveyJPARepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SurveyApiController {

    private final SurveyJPARepository surveyJPARepository;

    private final MemberRepository memberRepository;

    @PostMapping("/survey/{memberId}")
    public ResponseEntity<SurveyCompleteDTO> saveSurvey(@PathVariable Long memberId, @RequestBody SurveyDTO surveyDTO) {
        // memberId를 사용하여 해당 회원을 조회
        Member member = memberRepository.findOne(memberId);
        SurveyCompleteDTO surveyCompleteDTO = new SurveyCompleteDTO();

        if (member == null) {
            // 회원이 존재하지 않는 경우 예외 처리
            return ResponseEntity.badRequest().body(surveyCompleteDTO);
        }

        // SurveyDTO에서 필요한 정보를 가져와서 Survey 엔티티에 설정
        Survey survey = new Survey();
        survey.setMember(member);
        survey.setCareer(surveyDTO.getCareer());
        survey.setLocation(surveyDTO.getLocation());
        survey.setGoal(surveyDTO.getGoal());
        survey.setPrefer(surveyDTO.getPrefer());
        survey.setFrequency(surveyDTO.getFrequency());
        survey.setBirth(surveyDTO.getBirth());
        survey.setHeight(surveyDTO.getHeight());
        survey.setWeight(surveyDTO.getWeight());

        // Survey 저장
        surveyJPARepository.save(survey);
        surveyCompleteDTO.setId(survey.getMember().getId());

        if(survey.getLocation() == SurveyLocation.valueOf("GYM")) {
            surveyCompleteDTO.setNextpage("gym");
        }else{
            surveyCompleteDTO.setNextpage("home");
        }
        return ResponseEntity.ok(surveyCompleteDTO);
    }

    @GetMapping("/survey/{memberId}")
    public ResponseEntity<SurveyDTO> getSurvey(@PathVariable Long memberId) {
        Member member = memberRepository.findOne(memberId);

        Survey survey = surveyJPARepository.findByMember(member);

        return ResponseEntity.ok(new SurveyDTO(survey));
    }

    @Data
    public class SurveyDTO {


        private int career;
        private SurveyLocation location;
        private String goal;
        private GymType prefer;
        private int frequency;
        private String birth;
        private int height;
        private int weight;

        public SurveyDTO(Survey survey) {
            this.career = survey.getCareer();
            this.location = survey.getLocation();
            this.goal = survey.getGoal();
            this.prefer = survey.getPrefer();
            this.frequency = survey.getFrequency();
            this.birth = survey.getBirth();
            this.height = survey.getHeight();
            this.weight = survey.getWeight();
        }
    }

    @Data
    static class SurveyCompleteDTO {

        private String nextpage;
        private Long id; // 회원 아이디
    }
}
