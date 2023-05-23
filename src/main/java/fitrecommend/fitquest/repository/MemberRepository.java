package fitrecommend.fitquest.repository;

import fitrecommend.fitquest.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findById(Long id) {
        return em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

//    public Member findByToken(String token) {
//        return (Member) em.createQuery("select m from Member m where m.token = :token", Member.class)
//                .setParameter("token", token);
//    }
//  위의 코드를 아래코드로 대체하니까 쿼리가 제대로 실행되고 데이터베이스에 저장됌.
    public Member findByToken(String token) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.token = :token", Member.class);
        query.setParameter("token", token);
        List<Member> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

}
