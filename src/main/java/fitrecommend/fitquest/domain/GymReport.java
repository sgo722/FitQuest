package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "gymreport")
public class GymReport {

    @Id
    @GeneratedValue
    @Column(name = "gymreport_id")
    private Long id;

    @OneToMany(mappedBy = "gymreport",  cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer reportKcal;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private LocalDateTime created;

    private LocalDateTime starttime;

    private LocalDateTime endtime;

    public int getTotalKcal(){
        int kcal = 0;
        for(Exercise exercise : exercises){
            kcal += exercise.getTotalKcal();
        }
        return kcal;
    }

}
