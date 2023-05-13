package fitrecommend.fitquest.domain.report;

import fitrecommend.fitquest.domain.Exercise;
import fitrecommend.fitquest.domain.Member;
import fitrecommend.fitquest.domain.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.cache.interceptor.CacheAspectSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
public abstract class Report {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @OneToMany(mappedBy = "report",  cascade = CascadeType.ALL)
    private List<Exercise> exercise = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int calorie;


    private LocalDateTime created;
}
