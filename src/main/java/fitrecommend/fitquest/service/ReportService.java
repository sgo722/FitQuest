package fitrecommend.fitquest.service;

import fitrecommend.fitquest.domain.HomeReport;
import fitrecommend.fitquest.repository.HomeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

    private final HomeReportRepository homeReportRepository;

    @Transactional
    public void saveReport(HomeReport report){
        homeReportRepository.save(report);
    }


    public List<HomeReport> findReports() {
        return homeReportRepository.findAll();
    }

    public HomeReport findOne(Long reportId) {
        return homeReportRepository.findOne(reportId);
    }
}
