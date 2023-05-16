package fitrecommend.fitquest.domain;

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
    private List<Set> sets = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "report_id")
    private HomeReport homeReport;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private int satisfaction;

    private Complete complete;

    public int getTotalKcal(){
        int kcal = 0;
        for(Set set : sets){
            kcal +=  set.getRep() * gym.getKcal();
        }
        return kcal;
    }
}
