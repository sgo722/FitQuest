package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Set;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SetRepository {
    private final EntityManager em;

    public void save(Set set){
        em.persist(set);
    }

    public Set findOne(Long id){
        return em.find(Set.class, id);
    }

    public List<Set> findAll(){
        return em.createQuery("select s from Set s", Set.class)
                .getResultList();
    }
}
