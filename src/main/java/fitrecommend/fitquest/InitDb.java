package fitrecommend.fitquest;

import fitrecommend.fitquest.domain.Gym;
import fitrecommend.fitquest.domain.GymType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 모든 운동을 데이터베이스에 저장해두고 시작한다.
 */
@Component
@RequiredArgsConstructor
public class InitDb {

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
            Gym gym1 = createGym("푸쉬 업", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym1);
            Gym gym2 = createGym("바벨 벤치프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym2);
            Gym gym3 = createGym("팩 덱 플라이", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym3);
            Gym gym4 = createGym("머신 체스트 프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym4);
            Gym gym5 = createGym("디클라인 바벨 벤치프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym5);
            Gym gym6 = createGym("덤벨 벤치프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym6);
            Gym gym7 = createGym("인클라인 덤벨 벤치프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym7);
            Gym gym8 = createGym("딥스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym8);
            Gym gym9 = createGym("케이블 플라이", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym9);
            Gym gym10 = createGym("덤벨 플라이", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym10);
            Gym gym11 = createGym("디클라인 덤벨 벤치프레스", GymType.CHEST, "설명", "사진", 5);
            em.persist(gym11);


            // 삼두운동
            Gym gym12 = createGym("덤벨 스컬크러셔", GymType.TRICEPS, "설명", "사진", 5);
            em.persist(gym12);
            Gym gym13 = createGym("트라이셉스 익스텐션", GymType.TRICEPS, "설명", "사진", 5);
            em.persist(gym13);
            Gym gym14 = createGym("케이블 푸시 다운", GymType.TRICEPS, "설명", "사진", 5);
            em.persist(gym14);
            Gym gym15 = createGym("바벨 스컬크러셔", GymType.TRICEPS, "설명", "사진", 5);
            em.persist(gym15);
            Gym gym16 = createGym("오버헤드 트라이셉스 익스텐션", GymType.TRICEPS, "설명", "사진", 5);
            em.persist(gym16);

            // 등운동
            Gym gym17 = createGym("풀업", GymType.BACK, "설명", "사진", 5);
            em.persist(gym17);
            Gym gym18 = createGym("랫 풀다운", GymType.BACK, "설명", "사진", 5);
            em.persist(gym18);
            Gym gym19 = createGym("시티드 로우", GymType.BACK, "설명", "사진", 5);
            em.persist(gym19);
            Gym gym20 = createGym("백 익스텐션", GymType.BACK, "설명", "사진", 5);
            em.persist(gym20);
            Gym gym21 = createGym("원암 케이블 로우", GymType.BACK, "설명", "사진", 5);
            em.persist(gym21);
            Gym gym22 = createGym("원암 덤벨 로우", GymType.BACK, "설명", "사진", 5);
            em.persist(gym22);
            Gym gym23 = createGym("체스트 서포터드 로우", GymType.BACK, "설명", "사진", 5);
            em.persist(gym23);
            Gym gym24 = createGym("암 풀 다운", GymType.BACK, "설명", "사진", 5);
            em.persist(gym24);
            Gym gym25 = createGym("데드 리프트", GymType.BACK, "설명", "사진", 5);
            em.persist(gym25);
            Gym gym26 = createGym("바벨로우", GymType.BACK, "설명", "사진", 5);
            em.persist(gym26);

            // 이두
            Gym gym28 = createGym("바벨 컬", GymType.BICEP, "설명", "사진", 5);
            em.persist(gym28);
            Gym gym29 = createGym("해머 컬", GymType.BICEP, "설명", "사진", 5);
            em.persist(gym29);
            Gym gym30 = createGym("프리쳐 컬 머신", GymType.BICEP, "설명", "사진", 5);
            em.persist(gym30);
            Gym gym31 = createGym("덤벨 컬", GymType.BICEP, "설명", "사진", 5);
            em.persist(gym31);
            Gym gym32 = createGym("케이블 컬", GymType.BICEP, "설명", "사진", 5);
            em.persist(gym32);

            // 하체
            Gym gym33 = createGym("레그익스텐션", GymType.LEG, "설명", "사진", 5);
            em.persist(gym33);
            Gym gym34 = createGym("레그컬", GymType.LEG, "설명", "사진", 5);
            em.persist(gym34);
            Gym gym35 = createGym("레그프레스", GymType.LEG, "설명", "사진", 5);
            em.persist(gym35);
            Gym gym36 = createGym("카프 레이즈", GymType.LEG, "설명", "사진", 5);
            em.persist(gym36);
            Gym gym37 = createGym("런지", GymType.LEG, "설명", "사진", 5);
            em.persist(gym37);
            Gym gym38 = createGym("스쿼트", GymType.LEG, "설명", "사진", 5);
            em.persist(gym38);
            Gym gym39 = createGym("힙 어브덕션", GymType.LEG, "설명", "사진", 5);
            em.persist(gym39);

            // 어깨
            Gym gym40 = createGym("숄더프레스", GymType.SHOULDER, "설명", "사진", 5);
            em.persist(gym40);
            Gym gym41 = createGym("사이드 래터럴 레이즈", GymType.SHOULDER, "설명", "사진", 5);
            em.persist(gym41);
            Gym gym42 = createGym("벤트 오버 레터럴 레이즈", GymType.SHOULDER, "설명", "사진", 5);
            em.persist(gym42);
            Gym gym43 = createGym("프론트 레이즈", GymType.SHOULDER, "설명", "사진", 5);
            em.persist(gym43);
            Gym gym44 = createGym("오버 헤드 프레스", GymType.SHOULDER, "설명", "사진", 5);
            em.persist(gym44);
        }

        private Gym createGym(String name, GymType type, String information, String image, int kcal) {
            Gym gym = new Gym();
            gym.setName(name);
            gym.setType(type);
            gym.setInforamtion(information);
            gym.setImage(image);
            gym.setKcal(kcal);
            return gym;
        }
    }
}

