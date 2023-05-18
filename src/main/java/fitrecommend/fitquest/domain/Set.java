package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Set {

    @Id @GeneratedValue
    @Column(name = "set_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer rep;
}
