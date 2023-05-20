package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.*;
import fitrecommend.fitquest.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExerciseApiController {

    private final ExerciseJPARepository exerciseJPARepository;

    private final GymReportJPARepository gymReportJPARepository;

    private final MemberRepository memberRepository;

    private final SetJPARepository setJPARepository;

    private final GymJPARepository gymJPARepository;


    @PostMapping("/gym/exercise/complete")
    public ResponseEntity<ExerciseCompleteResponseDto> exerciseComplete(ExerciseCompleteRequestDto exerciseCompleteRequestDto){
        ExerciseCompleteResponseDto exerciseCompleteResponseDto = new ExerciseCompleteResponseDto();
        Member member = memberRepository.findOne(exerciseCompleteRequestDto.memberId);
        List<GymReport> gymAllReports = gymReportJPARepository.findByMember(member); // 회원이 가지고있는 모든 보고서를 조회한다.
        GymReport gymReport = gymAllReports.get(gymAllReports.size()-1);
        Exercise exercise = exerciseJPARepository.findByGymReportAndGymId(gymReport, exerciseCompleteRequestDto.gymId);

        for(Integer lep : exerciseCompleteRequestDto.getLeps()){
            Set set = new Set();
            set.setExercise(exercise);
            set.setRep(lep);
            setJPARepository.save(set);
        }
        exercise.setComplete(Complete.YES);
        exercise.setTotalKcal(exercise.getTotalKcal());
        exerciseJPARepository.save(exercise);
        exerciseCompleteResponseDto.setState("Success");
        return ResponseEntity.ok(exerciseCompleteResponseDto);
    }

    @PostMapping("/gym/exercise/satisfaction")
    public ResponseEntity<GymReportSatisfactionResponse> exerciseSatisfaction(GymReportSatisfactionRequest gymReportSatisfactionRequest){
        Member member = memberRepository.findOne(gymReportSatisfactionRequest.memberId);
        GymReportSatisfactionResponse gymReportSatisfactionResponse = new GymReportSatisfactionResponse();
        List<GymReport> gymAllReports = gymReportJPARepository.findByMember(member); // 회원이 가지고있는 모든 보고서를 조회한다.
        GymReport gymReport = gymAllReports.get(gymAllReports.size()-1);
        for(SatisfactionDto satisfactionDto: gymReportSatisfactionRequest.getSatisfactionDtos()){
            Exercise exercise = exerciseJPARepository.findByGymReportAndGymId(gymReport, satisfactionDto.gymId);
            exercise.setSatisfaction(satisfactionDto.satisfaction);
            exerciseJPARepository.save(exercise);
        }
        gymReportSatisfactionResponse.setState("Success");
        return ResponseEntity.ok(gymReportSatisfactionResponse);
    }

    @Data
    public class ExerciseCompleteRequestDto{
        private Long memberId;
        private Long gymId;
        private List<Integer> leps;
    }

    @Data
    public class ExerciseCompleteResponseDto{
        private String state;
    }

    @Data
    public class GymReportSatisfactionRequest{
        private Long memberId;
        private List<SatisfactionDto> satisfactionDtos;
    }

    @Data
    public class SatisfactionDto{
        private Long gymId;
        private Integer satisfaction;
    }



    @Data
    public class GymReportSatisfactionResponse{
        private String state;
    }


}
