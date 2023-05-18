package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Exercise;
import fitrecommend.fitquest.domain.GymReport;
import fitrecommend.fitquest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseJPARepository extends JpaRepository<Exercise, Long> {

    public Exercise findByGymReportAndMember(GymReport gymReport, Member member);
}
