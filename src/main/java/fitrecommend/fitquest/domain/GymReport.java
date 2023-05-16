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
public class GymReport {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @OneToMany(mappedBy = "report",  cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int calorie;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private LocalDateTime created;

    private LocalDateTime starttime;

    public int getTotalKcal(){
        int kcal = 0;
        for(Exercise exercise : exercises){
            kcal += exercise.getTotalKcal();
        }
        return kcal;
    }

}
