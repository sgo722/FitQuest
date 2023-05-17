package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.*;
import fitrecommend.fitquest.repository.GymReportJPARepository;
import fitrecommend.fitquest.repository.HomeReportJPARepository;
import fitrecommend.fitquest.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportApiController {

    private final HomeReportJPARepository homeReportJPARepository;
    private final GymReportJPARepository gymReportJPARepository;
    private final MemberRepository memberRepository;

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
            gymProgressResponseDto.setNextapi("recommend");
            return ResponseEntity.ok(gymProgressResponseDto);
        }
        if (gymReports.get(gymReports.size() - 1).getProgress() == Progress.READY) {
            gymProgressResponseDto.setProgress(Progress.READY);
            gymProgressResponseDto.setNextapi("report");
            return ResponseEntity.ok(gymProgressResponseDto);
        } else if (gymReports.get(gymReports.size() - 1).getProgress() == Progress.INPROGRESS) {
            gymProgressResponseDto.setProgress(Progress.INPROGRESS);
            gymProgressResponseDto.setNextapi("report");
            return ResponseEntity.ok(gymProgressResponseDto);
        }
        else {
            gymProgressResponseDto.setProgress(Progress.COMPLETE);
            LocalDateTime endtime = gymReports.get(gymReports.size() - 1).getEndtime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(endtime, now);
            long hoursSinceEnd = duration.toHours();
            if (hoursSinceEnd < 12) {
                gymProgressResponseDto.setNextapi("report");
            } else {
                gymProgressResponseDto.setNextapi("recommend");
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


    @GetMapping("/home/progress/{memberId}") // 홈트운동 진행여부에 리턴
    public ResponseEntity<HomeProgressResponseDto> getHomeProgress(@PathVariable Long memberId){
        Member member = memberRepository.findOne(memberId);

        List<HomeReport> homeReports = homeReportJPARepository.findByMember(member);

        HomeProgressResponseDto homeProgressResponseDto = new HomeProgressResponseDto();

        if (homeReports.get(homeReports.size() - 1).getProgress() == Progress.READY) {
            homeProgressResponseDto.setProgress(Progress.READY);
            homeProgressResponseDto.setNextapi("report");
            return ResponseEntity.ok(homeProgressResponseDto);
        } else if (homeReports.get(homeReports.size() - 1).getProgress() == Progress.INPROGRESS) {
            homeProgressResponseDto.setProgress(Progress.INPROGRESS);
            homeProgressResponseDto.setNextapi("report");
            return ResponseEntity.ok(homeProgressResponseDto);
        }
        else {
            homeProgressResponseDto.setProgress(Progress.COMPLETE);
            LocalDateTime endtime = homeReports.get(homeReports.size() - 1).getEndtime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(endtime, now);
            long hoursSinceEnd = duration.toHours();
            if (hoursSinceEnd < 12) {
                homeProgressResponseDto.setNextapi("report");
            } else {
                homeProgressResponseDto.setNextapi("recommend");
            }
            return ResponseEntity.ok(homeProgressResponseDto);
        }
    }

//    @GetMapping("/gym/calender/{memberId}") // 캘린더에서 운동보고서를 조회할떄 사용하면 됌. 아직 미완성
//    public ResponseEntity<Progress> getGymReports(@PathVariable Long memberId){
//        Member member = memberRepository.findOne(memberId);
//
//        List<GymReport> gymReports = gymReportJPARepository.findByMember(member); // 그 회원에 해당하는 보고서 전체를 조회한다.
//
//
//    }


    @PostMapping("/gym/report/save/{memberId}") // 운동을 저장할것이다.
    public ResponseEntity<GymSaveResponseDto> GymReportSave(@PathVariable Long memberId, GymSaveRequestDto gymSaveRequestDto){
        GymReport gymreport = new GymReport();
        List<Long> GymIds = gymSaveRequestDto.id; // 해야하는 운동 Id값들을 받아온다.
        for(Long id : GymIds){
            Exercise exercise = new Exercise();
            exercise.getGymreport().setId(id);
        }


    }

    @Data
    public class GymProgressResponseDto{
        private Progress progress;
        private String nextapi;
    }

    @Data
    public class HomeProgressResponseDto{
        private Progress progress;
        private String nextapi;
    }


    @Data
    public class GymSaveRequestDto{ // 운동을 저장하고 어떤값을 반환해줘야할까?
        private List<Long> id; // 운동 id값을 리스트로 받는다.
    }
    @Data
    public class GymSaveResponseDto{ // 운동을 저장하고 어떤값을 반환해줘야할까?
        private Progress progress;
    }



}
