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
    @JoinColumn(name = "gymreport_id")
    private GymReport gymReport;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private Integer satisfaction;

    private Complete complete;

    private Double totalKcal;

    public Double getTotalKcal(){
        Double kcal = 0D;
        for(Set set : sets){
            kcal +=  set.getRep() * gym.getKcal();
        }
        return kcal;
    }
}
