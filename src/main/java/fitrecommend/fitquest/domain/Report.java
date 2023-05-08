package fitrecommend.fitquest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Report {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    private int calorie;

    private int satisfaction;

    private LocalDateTime created;

    private ReportLocationType type;
}
