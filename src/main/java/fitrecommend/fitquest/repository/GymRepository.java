package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Gym;
import fitrecommend.fitquest.domain.Set;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymRepository {
    private final EntityManager em;

    public Gym findOne(Long id){
        return em.find(Gym.class, id);
    }
}
