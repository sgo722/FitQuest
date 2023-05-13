package fitrecommend.fitquest.domain;

import fitrecommend.fitquest.domain.report.Report;
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
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Report> report = new ArrayList<>();

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberGender gender;

    private String email;

    private Long birth;

    private String token;

    @Enumerated(EnumType.STRING) // ORDINAL로 숫자로 인식해서 +N을하여 데이터를 반환해야하나?
    private Today today;
}
