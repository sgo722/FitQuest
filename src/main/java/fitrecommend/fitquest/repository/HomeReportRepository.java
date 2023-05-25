package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.GymReport;
import fitrecommend.fitquest.domain.HomeReport;
import fitrecommend.fitquest.domain.HomeType;
import fitrecommend.fitquest.domain.Today;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeReportRepository {

    private final EntityManager em;

    public List<HomeReport> findByHomeType(HomeType homeType) {
        return em.createQuery("select h from HomeReport h where h.home.type = :type", HomeReport.class)
                .setParameter("type", homeType)
                .getResultList();
    }
}
