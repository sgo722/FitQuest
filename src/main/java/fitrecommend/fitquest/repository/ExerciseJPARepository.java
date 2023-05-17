package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseJPARepository extends JpaRepository<Exercise, Long> {
}
