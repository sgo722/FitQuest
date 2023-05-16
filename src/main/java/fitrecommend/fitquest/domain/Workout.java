package fitrecommend.fitquest.domain;

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
    private HomeReport homeReport;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id")
    private Home home;

    private int satisfaction;

    private String time;
}
