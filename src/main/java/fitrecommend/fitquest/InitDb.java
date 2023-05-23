package fitrecommend.fitquest;

import fitrecommend.fitquest.domain.Gym;
import fitrecommend.fitquest.domain.GymType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 모든 운동을 데이터베이스에 저장해두고 시작한다.
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    @Autowired
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Gym gym1 = createGym("푸쉬 업", GymType.CHEST, "가슴, 어깨, 삼두근을 강화하는 상체 운동으로, 몸을 바닥에 대고 팔을 펴고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=aoH7qNedO8k", 0.8);
            em.persist(gym1);
            Gym gym2 = createGym("바벨 벤치프레스", GymType.CHEST, "가슴과 삼두근을 주로 강화하는 대표적인 운동으로, 가슴 위에 바벨을 올려놓고 팔을 펴고 다시 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=A2kHURY746E", 0.7);
            em.persist(gym2);
            Gym gym3 = createGym("팩 덱 플라이", GymType.CHEST, "가슴의 외측 부분을 강화하는 운동으로, 팩 덱 머신에서 팔을 벌리고 다시 모으는 동작을 반복합니다.", "https://www.youtube.com/watch?v=Og9pgOtL-04", 0.5);
            em.persist(gym3);
            Gym gym4 = createGym("머신 체스트 프레스", GymType.CHEST, "가슴을 강화하는 운동으로, 머신에서 가슴 앞으로 팔을 밀고 다시 원위치로 돌아오는 동작을 반복합니다.", "https://www.youtube.com/watch?v=cZnN10iJJJU", 0.5);
            em.persist(gym4);
            Gym gym5 = createGym("디클라인 바벨 벤치프레스", GymType.CHEST, "하부 가슴을 강화하는 운동으로, 바벨을 디클라인 벤치에 놓고 가슴 아래로 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=jYgpC5pFb20", 0.6);
            em.persist(gym5);
            Gym gym6 = createGym("덤벨 벤치프레스", GymType.CHEST, " 가슴과 어깨를 강화하는 운동으로, 덤벨을 손에 들고 가슴 앞으로 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=xTQL6jvVMNA", 0.8);
            em.persist(gym6);
            Gym gym7 = createGym("인클라인 덤벨 벤치프레스", GymType.CHEST, "상부 가슴을 강화하는 운동으로, 인클라인 벤치에서 덤벨을 가슴 위로 올리고 다시 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=tIdle9XsoKs", 0.8);
            em.persist(gym7);
            Gym gym8 = createGym("딥스", GymType.CHEST, "삼두근과 가슴의 하부를 강화하는 운동으로, 병렬 바에 손을 놓고 팔을 굽혀 몸을 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=pQSfXvaQGas", 0.7);
            em.persist(gym8);
            Gym gym9 = createGym("케이블 플라이", GymType.CHEST, "가슴의 중앙과 외측 부분을 강화하는 운동으로, 케이블 머신에서 팔을 벌리고 다시 모으는 동작을 반복합니다.", "https://www.youtube.com/watch?v=jtH2YmpGqI8", 0.4);
            em.persist(gym9);
            Gym gym10 = createGym("덤벨 플라이", GymType.CHEST, "가슴의 외측을 강화하는 운동으로, 덤벨을 들고 팔을 벌리고 다시 모으는 동작을 반복합니다.", "https://www.youtube.com/watch?v=gmVan01OP9E", 0.4);
            em.persist(gym10);
            Gym gym11 = createGym("디클라인 덤벨 벤치프레스", GymType.CHEST, "하부 가슴을 강화하는 운동으로, 디클라인 벤치에서 덤벨을 가슴 아래로 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=i2Cp0HCFQfU", 0.5);
            em.persist(gym11);


            // 삼두운동
            Gym gym12 = createGym("덤벨 스컬크러셔", GymType.TRICEPS, "삼두근을 강화하는 운동으로, 덤벨을 들고 팔을 굽혀 머리 뒤로 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=uZOwQLaq0-g", 0.4);
            em.persist(gym12);
            Gym gym13 = createGym("트라이셉스 익스텐션", GymType.TRICEPS, "삼두근을 강화하는 운동으로, 팔을 굽혀 삼두근을 늘리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=ogyGQxuxxn4", 0.4);
            em.persist(gym13);
            Gym gym14 = createGym("케이블 푸시 다운", GymType.TRICEPS, "삼두근을 강화하는 운동으로, 케이블 머신에서 팔을 펴고 다시 구부리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=ObEtLS9heOo", 0.5);
            em.persist(gym14);
            Gym gym15 = createGym("바벨 스컬크러셔", GymType.TRICEPS, "삼두근을 강화하는 운동으로, 바벨을 들고 팔을 굽혀 머리 뒤로 내리고 다시 올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=kOrn-StwaSc", 0.6);
            em.persist(gym15);
            Gym gym16 = createGym("오버헤드 트라이셉스 익스텐션", GymType.TRICEPS, "삼두근을 강화하는 운동으로, 머리 위로 팔을 올리고 다시 구부리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=3Nqt05cRhpU", 0.4);
            em.persist(gym16);

            // 등운동
            Gym gym17 = createGym("풀업", GymType.BACK, "상체 전체를 강화하는 운동으로, 막대나 패턴에서 매달려서 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=nWhS28U6bCY", 0.9);
            em.persist(gym17);
            Gym gym18 = createGym("랫 풀다운", GymType.BACK, "상체 전체를 강화하는 운동으로, 랫 풀다운 머신에서 매달려서 막대를 가슴 쪽으로 당기는 동작을 반복합니다.", "https://www.youtube.com/watch?v=2K2WCGstHOY", 0.4);
            em.persist(gym18);
            Gym gym19 = createGym("시티드 로우", GymType.BACK, "등을 강화하는 운동으로, 시티드 로우 머신에서 상체를 앞으로 구부리고 등을 당기는 동작을 반복합니다.", "https://www.youtube.com/watch?v=pkKfWeQ9APQ", 0.4);
            em.persist(gym19);
            Gym gym20 = createGym("백 익스텐션", GymType.BACK, "등과 허리를 강화하는 운동으로, 백 익스텐션 머신에서 상체를 앞으로 구부리고 등과 허리를 힘으로 들어올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=0KXqeI-uJ3s", 0.5);
            em.persist(gym20);
            Gym gym21 = createGym("원암 케이블 로우", GymType.BACK, "등을 강화하는 운동으로, 케이블 머신을 이용하여 한 손으로 로우 동작을 수행하는 운동입니다.", "https://www.youtube.com/watch?v=VDPteW0KjDA", 0.4);
            em.persist(gym21);
            Gym gym22 = createGym("원암 덤벨 로우", GymType.BACK, "등을 강화하는 운동으로, 덤벨을 이용하여 한 손씩 로우 동작을 수행하는 운동입니다.", "https://www.youtube.com/watch?v=2i1v1UvsP_w", 0.4);
            em.persist(gym22);
            Gym gym23 = createGym("체스트 서포터드 로우", GymType.BACK, "등과 상체를 강화하는 운동으로, 체스트 서포터드 머신에서 가슴을 지지하고 등을 당기는 동작을 반복합니다.", "https://www.youtube.com/watch?v=w5Juhckt-2g", 0.5);
            em.persist(gym23);
            Gym gym24 = createGym("암 풀 다운", GymType.BACK, "등과 상체를 강화하는 운동으로, 암 풀 다운 머신에서 팔을 구부리고 등을 당기는 동작을 반복합니다.", "https://www.youtube.com/watch?v=bIo6_iFHE6U", 0.4);
            em.persist(gym24);
            Gym gym25 = createGym("데드 리프트", GymType.BACK, "전신을 강화하는 운동으로, 막대를 손에 들고 허리를 펴고 엉덩이를 뒤로 밀며 일어서는 동작을 반복합니다.", "https://www.youtube.com/watch?v=C8YzWMsNF9s", 1D);
            em.persist(gym25);
            Gym gym26 = createGym("바벨로우", GymType.BACK, "등을 강화하는 운동으로, 바벨을 손에 들고 허리를 펴고 상체를 앞으로 구부리고 다시 등을 당기는 동작을 반복합니다.", "https://www.youtube.com/watch?v=EEqGCoTuYfQ", 0.5);
            em.persist(gym26);

            // 이두
            Gym gym27 = createGym("바벨 컬", GymType.BICEP, "이두근을 강화하는 운동으로, 바벨을 들고 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=Dlg0W_5mq98", 0.4);
            em.persist(gym27);
            Gym gym28 = createGym("해머 컬", GymType.BICEP, "이두근을 강화하는 운동으로, 해머(망치)처럼 생긴 덤벨을 들고 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=hFM3GLw9Cfk", 0.3);
            em.persist(gym28);
            Gym gym29 = createGym("프리쳐 컬 머신", GymType.BICEP, "이두근을 강화하는 운동으로, 프리쳐 컬 머신에서 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=X1FSQR3EOPY", 0.3);
            em.persist(gym29);
            Gym gym30 = createGym("덤벨 컬", GymType.BICEP, "이두근을 강화하는 운동으로, 덤벨을 들고 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=cOver4_h5Uw",0.4);
            em.persist(gym30);
            Gym gym31 = createGym("케이블 컬", GymType.BICEP, "이두근을 강화하는 운동으로, 케이블 머신에서 팔을 구부리고 다시 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=5bPGsV7aTKU", 0.4);
            em.persist(gym31);

            // 하체
            Gym gym32 = createGym("레그익스텐션", GymType.LEG, "허벅지 앞쪽을 강화하는 운동으로, 레그 익스텐션 머신에서 다리를 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=mS9iwXhycJI", 0.6);
            em.persist(gym32);
            Gym gym33 = createGym("레그컬", GymType.LEG, "허벅지 뒷쪽을 강화하는 운동으로, 레그 컬 머신에서 다리를 구부리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=9w8d_EL-7e4", 0.7);
            em.persist(gym33);
            Gym gym34 = createGym("레그프레스", GymType.LEG, "허벅지 전체를 강화하는 운동으로, 레그프레스 머신에서 다리를 펴는 동작을 반복합니다.", "https://www.youtube.com/watch?v=EV0F_3S7Sks", 0.9);
            em.persist(gym34);
            Gym gym35 = createGym("카프 레이즈", GymType.LEG, "종아리를 강화하는 운동으로, 스탠딩 카프 레이즈 머신에서 발을 들어올리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=QdAtQJMKi0U", 0.3);
            em.persist(gym35);
            Gym gym36 = createGym("런지", GymType.LEG, "허벅지와 엉덩이를 강화하는 운동으로, 한 다리를 앞으로 내밀고 다시 원위치로 돌아가는 동작을 반복합니다.", "https://www.youtube.com/watch?v=oPkwl7cFlyk", 0.5);
            em.persist(gym36);
            Gym gym37 = createGym("스쿼트", GymType.LEG, "허벅지와 엉덩이를 강화하는 운동으로, 머신이나 바벨을 등에 짊어지고 앉았다 일어나는 동작을 반복합니다.", "https://www.youtube.com/watch?v=MWjKQGoNW0U", 0.9);
            em.persist(gym37);
            Gym gym38 = createGym("힙 어브덕션", GymType.LEG, "엉덩이 측면을 강화하는 운동으로, 힙 어브덕션 머신에서 다리를 옆으로 벌리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=E-BbAqqRV94", 0.5);
            em.persist(gym38);

            // 어깨
            Gym gym39 = createGym("숄더프레스", GymType.SHOULDER, "어깨를 강화하는 운동으로, 바벨이나 덤벨을 들어 어깨 위로 올리고 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=rHk4j2WRAE4", 0.6);
            em.persist(gym39);
            Gym gym40 = createGym("사이드 래터럴 레이즈", GymType.SHOULDER, "어깨 측면을 강화하는 운동으로, 덤벨을 들고 옆으로 올리고 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=iNgwwI3WBTo", 0.4);
            em.persist(gym40);
            Gym gym41 = createGym("벤트 오버 레터럴 레이즈", GymType.SHOULDER, "어깨와 상체 뒷부분을 강화하는 운동으로, 허리를 숙인 상태에서 덤벨을 들어 옆으로 올리고 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=n3aX0oJyvWA", 0.4);
            em.persist(gym41);
            Gym gym42 = createGym("프론트 레이즈", GymType.SHOULDER, "어깨 전면을 강화하는 운동으로, 덤벨을 들고 앞으로 올리고 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=nMUQR-dI_S8", 0.4);
            em.persist(gym42);
            Gym gym43 = createGym("오버 헤드 프레스", GymType.SHOULDER, "어깨와 삼두근을 강화하는 운동으로, 바벨이나 덤벨을 어깨 위로 올리고 내리는 동작을 반복합니다.", "https://www.youtube.com/watch?v=DgS7Y2bj2NM", 0.8);
            em.persist(gym43);
        }

        private Gym createGym(String name, GymType type, String information, String url, Double kcal) {
            Gym gym = new Gym();
            gym.setName(name);
            gym.setType(type);
            gym.setInforamtion(information);
            gym.setUrl(url);
            gym.setKcal(kcal);
            return gym;
        }
    }
}

