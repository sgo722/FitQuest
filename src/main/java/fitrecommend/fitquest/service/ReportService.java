package fitrecommend.fitquest.service;

import fitrecommend.fitquest.domain.HomeReport;
import fitrecommend.fitquest.repository.HomeReportJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

    private final HomeReportJPARepository homeReportJPARepository;

    @Transactional
    public void saveReport(HomeReport report){
        homeReportJPARepository.save(report);
    }


    public List<HomeReport> findReports() {
        return homeReportJPARepository.findAll();
    }

//    public HomeReport findOne(Long reportId) {
//        return homeReportJPARepository.findOne(reportId);
//    }
}
