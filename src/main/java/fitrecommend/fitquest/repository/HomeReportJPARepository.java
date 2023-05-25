package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.HomeReport;
import fitrecommend.fitquest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeReportJPARepository extends JpaRepository<HomeReport, Long> {


    public List<HomeReport> findByMember(Member member);


}
