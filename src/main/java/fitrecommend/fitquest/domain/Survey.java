package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Survey {

    @Id @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @OneToOne(mappedBy = "survey", fetch = LAZY)
    private Member member;

    private int career; // 운동 레벨

    private SurveyLocation location; // 운동 장소

    private String goal; // 운동 목표 - 운동목표가 유의미한 데이터가 아니기에 String으로 저

    private SurveyPrefer prefer; // 선호 운동

    private int frequency; // 운동빈도

    private Long birth; // 생년월일

    private int height; // 키

    private int weight; // 몸무게
}
