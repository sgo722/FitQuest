package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Video {

    @Id @GeneratedValue
    @Column(name = "video_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private String name;

    private String url;
}
