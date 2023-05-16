package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Gym {

    @Id @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private GymType type;

    private String name;

    private String inforamtion;

    private String Image;

    @OneToMany(mappedBy = "gym",  cascade = CascadeType.ALL)
    private List<Video> video = new ArrayList();

    @OneToOne(mappedBy = "gym", fetch = LAZY)
    private Exercise exercise;

    private int kcal;
}