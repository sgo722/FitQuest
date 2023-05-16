package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.HomeReport;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeReportRepository {

    private final EntityManager em;

    public void save(HomeReport report){
        em.persist(report);
    }

    public HomeReport findOne(Long id){
        return em.find(HomeReport.class, id);
    }

    public List<HomeReport> findAll(){
        return em.createQuery("select h from HomeReport h", HomeReport.class)
                .getResultList();
    }

}
