package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.GymReport;
import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.Today;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymReportRepository {

    private final EntityManager em;

    public List<GymReport> findByToday(Today today) {
        return em.createQuery("select g from GymReport g where g.today = :today", GymReport.class)
                .setParameter("today", today)
                .getResultList();
    }
}
