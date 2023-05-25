package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.*;
import fitrecommend.fitquest.repository.GymReportJPARepository;
import fitrecommend.fitquest.repository.HomeReportJPARepository;
import fitrecommend.fitquest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CalenderApiController {
    private final MemberRepository memberRepository;
    private final GymReportJPARepository gymReportJPARepository;
    private final HomeReportJPARepository homeReportJPARepository;

    @GetMapping("/calender/{memberId}")
    public ResponseEntity<ViewCalenderResponseDto> ViewCalender(@PathVariable Long memberId){
        ViewCalenderResponseDto viewCalenderResponseDto = new ViewCalenderResponseDto();
        Member member = memberRepository.findOne(memberId);
        for(GymReport gymReport : member.getGymReports()){
            GymReportDto gymReportDto = new GymReportDto();
            gymReportDto.name = gymReport.getName();
            gymReportDto.endTime = gymReport.getEndtime();
            for(Exercise exercise : gymReport.getExercises()){
                ExerciseDto exerciseDto = new ExerciseDto();
                exerciseDto.gymName = exercise.getGym().getName();
                exerciseDto.totalReps = 0;
                for(Set set : exercise.getSets()) {
                    exerciseDto.totalReps += set.getRep();
                }
                gymReportDto.exerciseDtos.add(exerciseDto);
            }
            viewCalenderResponseDto.gymReports.add(gymReportDto);
        }

        for(HomeReport homeReport : member.getHomeReports()){
            HomeReportDto homeReportDto = new HomeReportDto();
            homeReportDto.name = homeReport.getName();
            homeReportDto.endTime = homeReport.getEndtime();
            homeReportDto.url = homeReport.getHome().getUrl();
            viewCalenderResponseDto.homeReports.add(homeReportDto);
        }
        return ResponseEntity.ok(viewCalenderResponseDto);
    }

    public class ViewCalenderResponseDto{
        private List<GymReportDto> gymReports = new ArrayList<>();
        private List<HomeReportDto> homeReports = new ArrayList<>();
    }

    public class GymReportDto{
        private String name;
        private LocalDateTime endTime;
        private List<ExerciseDto> exerciseDtos = new ArrayList<>(); // 운동종류들
    }

    public class ExerciseDto{
        private String gymName; // 어떤 운동을 했는지
        private Integer totalReps; // 총 수행횟수
    }

    public class HomeReportDto{
        private String name;
        private LocalDateTime endTime;
        private String url;
    }


}
