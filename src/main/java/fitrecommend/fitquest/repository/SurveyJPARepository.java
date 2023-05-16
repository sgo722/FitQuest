package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyJPARepository extends JpaRepository<Survey, Long> {

    public Survey findByMember(Member member);

}
