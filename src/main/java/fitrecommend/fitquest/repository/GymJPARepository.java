package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymJPARepository extends JpaRepository<Gym, Long> {
    public Gym findOne(Long id);
}
