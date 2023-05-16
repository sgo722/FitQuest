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
    @Column(name = "report_id")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    private int calorie;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private LocalDateTime created;

    private LocalDateTime starttime;
}