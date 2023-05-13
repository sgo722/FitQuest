package fitrecommend.fitquest.domain;

import fitrecommend.fitquest.domain.report.Report;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Exercise {

    @Id @GeneratedValue
    @Column(name = "exercise_id")
    private Long id;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Sets> sets = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Report report;


}
