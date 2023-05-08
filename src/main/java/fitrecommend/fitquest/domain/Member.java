package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberGender gender;

    private String email;

    private Long birth;

    private int height;

    private int weight;

    @OneToMany(mappedBy = "member")
    private List<Like> like = new ArrayList();
}
