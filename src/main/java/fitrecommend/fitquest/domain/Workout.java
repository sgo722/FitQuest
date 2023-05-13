package fitrecommend.fitquest.domain;

import fitrecommend.fitquest.domain.report.Report;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter @Setter
public class Workout {
    @Id @GeneratedValue
    @Column(name = "workout_id")
    private Long id;

    @OneToOne(mappedBy = "workout", fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    private int satisfaction;
}
