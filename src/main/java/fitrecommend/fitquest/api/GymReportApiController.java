package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.*;
import fitrecommend.fitquest.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GymReportApiController {
    private final GymReportJPARepository gymReportJPARepository;

    private final GymJPARepository gymJPARepository;
    private final MemberRepository memberRepository;

    private final ExerciseJPARepository exerciseJPARepository;

//    @GetMapping("/gym/progress/{memberId}") // 헬스 운동 진행여부 리턴.
//    public ResponseEntity<GymProgressResponseDto> getGymProgress(@PathVariable Long memberId){
//        Member member = memberRepository.findOne(memberId);
//        List<GymReport> gymReports = gymReportJPARepository.findByMember(member);
//        GymProgressResponseDto gymProgressResponseDto = new GymProgressResponseDto();
//        gymProgressResponseDto.setProgress(Progress.COMPLETE);
//        if(gymReports == null) {
//            return ResponseEntity.ok(gymProgressResponseDto);
//        }
//        for(GymReport gymreport : gymReports) {
//            if (gymreport.getProgress() == Progress.READY) {
//                gymProgressResponseDto.setProgress(Progress.READY);
//                return ResponseEntity.ok(gymProgressResponseDto);
//            } else if (gymreport.getProgress() == Progress.INPROGRESS) {
//                gymProgressResponseDto.setProgress(Progress.INPROGRESS);
//                return ResponseEntity.ok(gymProgressResponseDto);
//            }
//        }
//        return ResponseEntity.ok(gymProgressResponseDto);
//    }

    @GetMapping("/gym/progress/{memberId}")
    public ResponseEntity<GymProgressResponseDto> getGymProgress(@PathVariable Long memberId) {
        Member member = memberRepository.findOne(memberId);
        List<GymReport> gymReports = gymReportJPARepository.findByMember(member);

        GymProgressResponseDto gymProgressResponseDto = new GymProgressResponseDto();

        if (gymReports == null || gymReports.isEmpty()) {
            gymProgressResponseDto.setNextApi("recommend");
            return ResponseEntity.ok(gymProgressResponseDto);
        }
        if (gymReports.get(gymReports.size() - 1).getProgress() == Progress.READY) {
            gymProgressResponseDto.setProgress(Progress.READY);
            gymProgressResponseDto.setNextApi("report");
            return ResponseEntity.ok(gymProgressResponseDto);
        } else if (gymReports.get(gymReports.size() - 1).getProgress() == Progress.INPROGRESS) {
            gymProgressResponseDto.setProgress(Progress.INPROGRESS);
            gymProgressResponseDto.setNextApi("report");
            return ResponseEntity.ok(gymProgressResponseDto);
        }
        else {
            gymProgressResponseDto.setProgress(Progress.COMPLETE);
            LocalDateTime endtime = gymReports.get(gymReports.size() - 1).getEndtime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(endtime, now);
            long hoursSinceEnd = duration.toHours();
            if (hoursSinceEnd < 12) {
                gymProgressResponseDto.setNextApi("report");
            } else {
                gymProgressResponseDto.setNextApi("recommend");
            }
            return ResponseEntity.ok(gymProgressResponseDto);
        }
    }

    // 플러터입장
    // 1. nextapi의 값을 확인하고 어떠한 api를 호출할지 결정합니다.
    // 2. Progress의 상태를 확인하고 어떤 화면으로 넘어갈지 결정합니다.
    // report - 보고서를 조회하고 recommend - 운동을 추천해주세요
    // COMPLETE, report - 운동 완료 페이지 이동, 운동보고서 api 호출
    // COMPLETE, recommend - 운동 전 페이지 이동, 운동추천 api호출
    // INPROGRESS, report - 운동 중 페이지 이동, 운동보고서 api호출
    // READY, report - 운동 전 페이지 이동, 운동보고서 api호출
    // NULL, recommend - 운동 전 페이지 이동, 운동 추천 api호출

//    @GetMapping("/gym/calender/{memberId}") // 캘린더에서 운동보고서를 조회할떄 사용하면 됌. 아직 미완성
//    public ResponseEntity<Progress> getGymReports(@PathVariable Long memberId){
//        Member member = memberRepository.findOne(memberId);
//
//        List<GymReport> gymReports = gymReportJPARepository.findByMember(member); // 그 회원에 해당하는 보고서 전체를 조회한다.
//
//
//    }


    // AI와 통신해야할 내용이 있어서 보류하자
//    @GetMapping("/gym/report/recommend/{memberId}") // AI와 통신해야할 내용이 있어서 보류하자
//    public ResponseEntity<GymRecommendResponseDto> GymReportRecommend(@PathVariable Long memberId, GymRecommendRequestDto gymRecommendRequestDto){
//        Member member = memberRepository.findOne(memberId);
//        GymReport gymreport = new GymReport();
//        GymRecommendResponseDto gymRecommendResponseDto = new GymRecommendResponseDto();
//        gymreport.setMember(member);
//        for(Long gymId : gymRecommendRequestDto.){ // 운동을 받아와서 저장한다.
//            Gym gym = gymJPARepository.findOne(gymId);
//            Exercise exercise = new Exercise();
//            exercise.setGym(gym);
//            exercise.setGymreport(gymreport);
//            exercise.setComplete(Complete.NO);
//            exercise.setTotalkcal(0);
//            gymCreateResponseDto.setId(gymId);
//            gymCreateResponseDto.setName(gym.getName());
//            gymCreateResponseDto.setType(gym.getType());
//        }
//        return ResponseEntity.ok(GymSaveResponseDto);
//    }

    @PostMapping("/gym/report/save")
    public ResponseEntity<GymSaveResponseDto> gymReportSave(GymSaveRequestDto gymSaveRequestDto){
        Member member = memberRepository.findOne(gymSaveRequestDto.memberId);
        List<GymReport> gymAllReports = gymReportJPARepository.findByMember(member);
        GymReport gymReport = gymAllReports.get(gymAllReports.size()-1);
        for (Exercise exercise : gymReport.getExercises()) {
            exercise.setGymReport(null); // 연결된 GymReport를 끊어줍니다.
            exerciseJPARepository.delete(exercise); // Exercise 객체를 삭제합니다.
        }
        gymReport.getExercises().clear(); // 마지막으로 조회한 운동보고서에 AI로부터 저장한 운동내역을 다 삭제해버림.
        GymSaveResponseDto gymSaveResponseDto = new GymSaveResponseDto();
        gymReport.setStarttime(gymSaveRequestDto.startTime);
        gymReport.setMember(member);
        for(Long id : gymSaveRequestDto.getGymId()){
            Optional<Gym> gymOptional = gymJPARepository.findById(id);
            if(gymOptional.isPresent()) {
                Gym gym = gymOptional.get();
                Exercise exercise = new Exercise();
                exercise.setGym(gym);
                exercise.setGymReport(gymReport);
                exercise.setComplete(Complete.NO);
                exercise.setTotalKcal(0);
                exerciseJPARepository.save(exercise);
                gymReport.getExercises().add(exercise);
            }
        }
        gymReport.setProgress(Progress.INPROGRESS);
        gymReportJPARepository.save(gymReport);
        gymSaveResponseDto.setState("Success");
        return ResponseEntity.ok(gymSaveResponseDto);
    }

    @GetMapping("/gym/report/{memberId}")
    public ResponseEntity<GymReportResponseDtos> gymReport(@PathVariable Long memberId){
        Member member = memberRepository.findOne(memberId);
        List<GymReport> gymAllReports = gymReportJPARepository.findByMember(member);
        GymReport gymReport = gymAllReports.get(gymAllReports.size()-1);
        GymReportResponseDtos gymReportResponseDtos = new GymReportResponseDtos();
        for(Exercise exercise : gymReport.getExercises()){
            GymReportResponseDto gymReportResponseDto = new GymReportResponseDto();
            gymReportResponseDto.setGymId(exercise.getGym().getId());
            gymReportResponseDto.setGymName(exercise.getGym().getName());
            gymReportResponseDto.setGymType(exercise.getGym().getUrl());
            gymReportResponseDto.setGymInformation(exercise.getGym().getInforamtion());
            gymReportResponseDtos.gyms.add(gymReportResponseDto);
        }
        return ResponseEntity.ok(gymReportResponseDtos);
    }

    @PostMapping("gym/report/complete")
    public ResponseEntity<GymReportCompleteResponseDto> gymReportComplete(GymReportCompleteRequestDto gymReportCompleteRequestDto){
        Member member = memberRepository.findOne(gymReportCompleteRequestDto.memberId);
        GymReportCompleteResponseDto gymReportCompleteResponseDto = new GymReportCompleteResponseDto();
        List<GymReport> gymReports = gymReportJPARepository.findByMember(member);
        GymReport gymReport = gymReports.get(gymReports.size()-1);
        for(Exercise exercise : gymReport.getExercises()){
            GymKcalDto gymKcalDto = new GymKcalDto();
            gymKcalDto.setGymTotalKcal(exercise.getTotalKcal());
            gymKcalDto.setGymId(exercise.getGym().getId());
            gymReportCompleteResponseDto.getGymKcalDtos().add(gymKcalDto);
        }
        gymReport.setEndtime(gymReportCompleteRequestDto.endTime);
        gymReport.setReportKcal(gymReport.getReportKcal());
        gymReportCompleteResponseDto.totalGymKcal = gymReport.getReportKcal();
        gymReport.setProgress(Progress.COMPLETE);
        gymReportJPARepository.save(gymReport);
        return ResponseEntity.ok(gymReportCompleteResponseDto);
    }

    @Data
    public class GymProgressResponseDto{
        private Progress progress;
        private String nextApi;
    }



    @Data
    public class GymSaveRequestDto{ // 운동을 저장하고 어떤값을 반환해줘야할까?
        private Long memberId;
        private List<Long> gymId; // 운동 id값을 리스트로 받는다.
        private LocalDateTime startTime;
    }
    @Data
    public class GymSaveResponseDto{ // 운동을 저장하고 어떤값을 반환해줘야할까?
        private String state;
    }

    @Data
    public class GymRecommendRequestDto{
        private Long memberId;

    }

    @Data
    public class GymRecommendResponseDto{
        private Long id;
        private GymType type;
        private String name;
    }


    @Data
    public class GymReportResponseDto{
        private Long gymId;
        private String gymType;
        private String gymInformation;
        private String gymUrl;
        private String gymName;
    }

    @Data
    public class GymReportResponseDtos {
        private List<GymReportResponseDto> gyms;
    }


    @Data
    public class GymReportCompleteRequestDto{
        private Long memberId;
        private LocalDateTime endTime;
    }
    @Data
    public class GymReportCompleteResponseDto{
        private Integer totalGymKcal;
        private List<GymKcalDto> gymKcalDtos;

    }

    @Data
    public class GymKcalDto{
        private Long gymId;
        private Integer gymTotalKcal;
    }

}
