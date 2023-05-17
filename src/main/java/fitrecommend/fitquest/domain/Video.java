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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    private String name;

    private String url;
}
