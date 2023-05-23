package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class HomeReport {

    @Id
    @GeneratedValue
    @Column(name = "homeReport_id")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    private Integer calorie;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private Integer satisfaction;

    private LocalDateTime created;

    private LocalDateTime starttime;

    private LocalDateTime endtime;
}
