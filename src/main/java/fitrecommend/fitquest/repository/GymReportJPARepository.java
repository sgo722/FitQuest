package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.GymReport;
import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.Progress;
import fitrecommend.fitquest.domain.Today;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymReportJPARepository extends JpaRepository<GymReport, Long> {

    public List<GymReport> findByMember(Member member);

    public List<GymReport> findByMemberAndProgress(Member member, Progress progress);
}
