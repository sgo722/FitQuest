package fitrecommend.fitquest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fitrecommend.fitquest.domain.*;
import fitrecommend.fitquest.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeReportApiController {

    private final HomeReportJPARepository homeReportJPARepository;
    private final MemberRepository memberRepository;

    private final HomeJPARepository homeJPARepository;



    @GetMapping("/home/progress/{memberId}") // 홈트운동 진행여부에 리턴
    public ResponseEntity<HomeProgressResponseDto> getHomeProgress(@PathVariable Long memberId){
        Member member = memberRepository.findOne(memberId);

        List<HomeReport> homeReports = homeReportJPARepository.findByMember(member);

        HomeProgressResponseDto homeProgressResponseDto = new HomeProgressResponseDto();

        if (homeReports.get(homeReports.size() - 1).getProgress() == Progress.READY) {
            homeProgressResponseDto.setProgress(Progress.READY);
            homeProgressResponseDto.setNextApi("report");
            return ResponseEntity.ok(homeProgressResponseDto);
        } else if (homeReports.get(homeReports.size() - 1).getProgress() == Progress.INPROGRESS) {
            homeProgressResponseDto.setProgress(Progress.INPROGRESS);
            homeProgressResponseDto.setNextApi("report");
            return ResponseEntity.ok(homeProgressResponseDto);
        }
        else {
            homeProgressResponseDto.setProgress(Progress.COMPLETE);
            LocalDateTime endtime = homeReports.get(homeReports.size() - 1).getEndtime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(endtime, now);
            long hoursSinceEnd = duration.toHours();
            if (hoursSinceEnd < 12) {
                homeProgressResponseDto.setNextApi("report");
            } else {
                homeProgressResponseDto.setNextApi("recommend");
            }
            return ResponseEntity.ok(homeProgressResponseDto);
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

    @GetMapping("/home/recommend/{memberId}")
    public ResponseEntity<HomeRecommendResponseDto> homeRecommend(@PathVariable Long memberId)throws JsonProcessingException {
        HomeRecommendResponseDto homeRecommendResponseDto = new HomeRecommendResponseDto();
        Member member = memberRepository.findOne(memberId);

        FlaskRecommendRequestDto requestDto = new FlaskRecommendRequestDto();
        requestDto.setMemberId(memberId);
        requestDto.setPrefer1(member.getSurvey().getPrefer1());
        requestDto.setPrefer2(member.getSurvey().getPrefer2());

        // JSON 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(requestDto);

        // HTTP 요청 보내기
        String url = "http://<플라스크 API URL>/api/v1/ai/home/recommend";  // 플라스크 API의 엔드포인트 URL
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FlaskRecommendResponseDto> responseEntity = restTemplate.postForEntity(url, requestEntity, FlaskRecommendResponseDto.class);

        HomeReport homeReport = new HomeReport();
        homeReport.setMember(member);
        member.getHomeReports().add(homeReport);
        memberRepository.save(member);
        Home home = new Home();
        home.setHomereport(homeReport);
        home.setType(responseEntity.getBody().getType());
        home.setVideoName(responseEntity.getBody().videoName);
        home.setUrl(responseEntity.getBody().getUrl());
        homeReport.setHome(home);
        homeReportJPARepository.save(homeReport);
        homeJPARepository.save(home);
        homeRecommendResponseDto.setType(home.getType());
        homeRecommendResponseDto.setVideoName(home.getVideoName());
        homeRecommendResponseDto.setUrl(home.getUrl());
        return ResponseEntity.ok(homeRecommendResponseDto);

    }

    @PostMapping("/home/report/save") // 현재 데이터베이스 있는 그대로 시간만 추가하면된다.
    public ResponseEntity<HomeReportSaveResponse> homeReportSave(HomeReportSaveRequest homeReportSaveRequest){
        HomeReportSaveResponse homeReportSaveResponse = new HomeReportSaveResponse();
        Member member = memberRepository.findOne(homeReportSaveRequest.memberId);
        List<HomeReport> homeAllReports = homeReportJPARepository.findByMember(member);
        HomeReport homeReport = homeAllReports.get(homeAllReports.size()-1);
        homeReport.setStarttime(homeReportSaveRequest.startTime);
        homeReport.setProgress(Progress.INPROGRESS);
        homeReportJPARepository.save(homeReport);
        homeReportSaveResponse.state = "Success";
        return ResponseEntity.ok(homeReportSaveResponse);
    }

    @PostMapping("/home/report/complete") // 끝난시간 저장하고 끝?
    public ResponseEntity<HomeReportCompleteResponse> homeReportComplete(HomeReportCompleteRequest homeReportCompleteRequest){
        HomeReportCompleteResponse homeReportCompleteResponse = new HomeReportCompleteResponse();
        Member member = memberRepository.findOne(homeReportCompleteRequest.memberId);
        List<HomeReport> homeAllReports = homeReportJPARepository.findByMember(member);
        HomeReport homeReport = homeAllReports.get(homeAllReports.size()-1);
        homeReport.setEndtime(homeReportCompleteRequest.endTime);
        homeReport.setProgress(Progress.COMPLETE);
        homeReportJPARepository.save(homeReport);
        homeReportCompleteResponse.state = "Success";
        return ResponseEntity.ok(homeReportCompleteResponse);
    }


    @PostMapping("/home/report/satisfaction")
    public ResponseEntity<HomeReportSatisfactionResponse> homeReportSaveSatisfaction(HomeReportSatisfactionRequest homeReportSatisfactionRequest){
        HomeReportSatisfactionResponse homeReportSatisfactionResponse = new HomeReportSatisfactionResponse();
        Member member = memberRepository.findOne(homeReportSatisfactionRequest.memberId);
        List<HomeReport> homeAllReports = homeReportJPARepository.findByMember(member);
        HomeReport homeReport = homeAllReports.get(homeAllReports.size()-1);
        homeReport.setSatisfaction(homeReportSatisfactionRequest.satifaction);
        homeReportJPARepository.save(homeReport);
        homeReportSatisfactionResponse.state = "Success";
        return ResponseEntity.ok(homeReportSatisfactionResponse);
    }


    @Data
    public class HomeProgressResponseDto{
        private Progress progress;
        private String nextApi;

        public HomeProgressResponseDto(){

        }
    }

    @Data
    public static class HomeRecommendResponseDto{
        private HomeType type;
        private String videoName;
        private String url;
    }

    @Data
    public static class FlaskRecommendRequestDto{
        private Long memberId;
        private HomeType prefer1;
        private HomeType prefer2;
    }


    @Data
    public static class FlaskRecommendResponseDto{
        private HomeType type;
        private String url;
        private String videoName;
    }

    @Data
    public static class HomeReportSaveRequest{
        private Long memberId;
        private LocalDateTime startTime;

        public HomeReportSaveRequest(){

        }
    }

    @Data
    public static class HomeReportSaveResponse{
        private String state;
        public HomeReportSaveResponse(){

        }
    }

    @Data
    public static class HomeReportCompleteRequest{
        private Long memberId;
        private LocalDateTime endTime;
        public HomeReportCompleteRequest(){

        }
    }

    @Data
    public static class HomeReportCompleteResponse{
        private String state;
        public HomeReportCompleteResponse(){
        }
    }

    @Data
    public static class HomeReportSatisfactionRequest{
        private Long memberId;
        private Integer satifaction;

        public HomeReportSatisfactionRequest(){
        }
    }

    @Data
    public class HomeReportSatisfactionResponse{
        private String state;
        public HomeReportSatisfactionResponse(){
        }
    }

    //

    @Data
    public static class HomeRecommendResponseDtos{
        private List<HomeRecommendResponseDto> homeRecommendResponseDtos;
    }



}
