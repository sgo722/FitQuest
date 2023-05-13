package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Home {

    @Id @GeneratedValue
    @Column(name = "home_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private HomeType type;

    @OneToMany(mappedBy = "home",  cascade = CascadeType.ALL)
    private List<Video> video = new ArrayList();
}
