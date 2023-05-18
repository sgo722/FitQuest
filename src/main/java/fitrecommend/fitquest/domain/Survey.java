package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "survey")
public class Survey {

    @Id @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @OneToOne(mappedBy = "survey", fetch = LAZY)
    private Member member;

    private Integer career; // 운동 레벨

    private SurveyLocation location; // 운동 장소

    private String goal; // 운동 목표 - 운동목표가 유의미한 데이터가 아니기에 String으로 저

    private GymType prefer; // 선호 운동

    private Integer frequency; // 운동빈도

    private String birth; // 생년월일

    private Integer height; // 키

    private Integer  weight; // 몸무게
}
