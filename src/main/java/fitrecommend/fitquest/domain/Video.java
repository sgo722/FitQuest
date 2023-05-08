package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Video {

    @Id @GeneratedValue
    @Column(name = "viedo_id")
    private Long id;

    private String name;

    private String url;

    private String image;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;
}
