package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.GymReport;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymReportRepository {

    private final EntityManager em;

    public void save(GymReport report){
        em.persist(report);
    }

    public GymReport findOne(Long id){
        return em.find(GymReport.class, id);
    }

    public List<GymReport> findAll(){
        return em.createQuery("select g from GymReport g", GymReport.class)
                .getResultList();
    }

    public List<GymReport> findAllWithExercise(){
        return em.createQuery(
                        "select g from GymReport g" +
                                " join fetch g.exercises e", GymReport.class)
                .getResultList();
    }

}
