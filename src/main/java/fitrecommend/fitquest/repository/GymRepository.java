package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Gym;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymRepository {

    private final EntityManager em;

    public Gym findOne(Long id){
        return em.find(Gym.class, id);
    }

    public List<Gym> findAll() {
        return em.createQuery("select g from Gym g", Gym.class)
                .getResultList();
    }
}
