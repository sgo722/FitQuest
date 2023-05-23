package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Home {

    @Id @GeneratedValue
    @Column(name = "home_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private HomeType type;

    @OneToOne(mappedBy = "home", fetch = LAZY)
    @JoinColumn(name = "homeReport_id")
    private HomeReport homereport;

    private String url;

    private String videoName;
}
