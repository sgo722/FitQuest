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
//@Table(name = "gymreport")
public class GymReport {

    @Id
    @GeneratedValue
    @Column(name = "gymReport_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "gymReport",  cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long reportKcal;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private LocalDateTime created;

    private LocalDateTime starttime;

    private LocalDateTime endtime;

    @Enumerated(EnumType.STRING) // ORDINAL로 숫자로 인식해서 +N을하여 데이터를 반환해야하나?
    private Today today;


    public Double getTotalKcal(){
        Double kcal = 0D;
        for(Exercise exercise : exercises){
            kcal += exercise.getTotalKcal();
        }
        return kcal;
    }

}
