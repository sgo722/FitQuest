package fitrecommend.fitquest.api;


import fitrecommend.fitquest.domain.*;
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

    @PostMapping("/survey/save/{memberId}") // 설문완료(분석중 화면에서)
    public ResponseEntity<SurveyResponseDTO> saveSurvey(@PathVariable Long memberId, @RequestBody SurveyRequestDTO surveyRequestDTO) {
        // memberId를 사용하여 해당 회원을 조회
        Member member = memberRepository.findOne(memberId);
        SurveyResponseDTO surveyResponseDTO = new SurveyResponseDTO();

        if (member == null) {
            // 회원이 존재하지 않는 경우 예외 처리
            return ResponseEntity.badRequest().body(surveyResponseDTO);
        }

        // SurveyResponseDTO에서 필요한 정보를 가져와서 Survey 엔티티에 설정
        Survey survey = new Survey();
        survey.setMember(member);
        member.setSurvey(survey);
        survey.setCareer(surveyRequestDTO.getCareer());
        survey.setLocation(surveyRequestDTO.getLocation());
        survey.setGoal1(surveyRequestDTO.getGoal1());
        survey.setGoal1(surveyRequestDTO.getGoal1());
        survey.setPrefer1(surveyRequestDTO.getPrefer1());
        survey.setPrefer2(surveyRequestDTO.getPrefer2());
        survey.setFrequency(surveyRequestDTO.getFrequency());
        survey.setBirth(surveyRequestDTO.getBirth());
        survey.setHeight(surveyRequestDTO.getHeight());
        survey.setWeight(surveyRequestDTO.getWeight());

        // Survey 저장
        surveyJPARepository.save(survey);
        surveyResponseDTO.setId(survey.getMember().getId());

        if(survey.getLocation() == SurveyLocation.GYM) {
            surveyResponseDTO.setNextpage("gym");
        }else{
            surveyResponseDTO.setNextpage("home");
        }
        return ResponseEntity.ok(surveyResponseDTO);
    }

//    @GetMapping("/survey/{memberId}") //설문전체를 조회할수있다.
//    public ResponseEntity<SurveyRequestDTO> getSurvey(@PathVariable Long memberId) {
//        Member member = memberRepository.findOne(memberId);
//
//        Survey survey = surveyJPARepository.findByMember(member);
//
//        return ResponseEntity.ok(new SurveyRequestDTO(survey));
//    }

    @Data
    public static class SurveyRequestDTO {
        private Integer career;
        private SurveyLocation location;
        private GymType goal1;

        private GymType goal2;

        private HomeType prefer1;

        private HomeType prefer2;
        private Integer frequency;
        private String birth;
        private Integer height;
        private Integer weight;

        public SurveyRequestDTO(){

        }

        public SurveyRequestDTO(Survey survey) {
            this.career = survey.getCareer();
            this.location = survey.getLocation();
            this.goal1 = survey.getGoal1();
            this.goal2 = survey.getGoal2();
            this.prefer1 = survey.getPrefer1();
            this.prefer2 = survey.getPrefer2();
            this.frequency = survey.getFrequency();
            this.birth = survey.getBirth();
            this.height = survey.getHeight();
            this.weight = survey.getWeight();
        }
    }

    @Data
    public static class SurveyResponseDTO {

        private String nextpage;
        private Long id; // 회원 아이디

        public SurveyResponseDTO(){

        }
    }
}
