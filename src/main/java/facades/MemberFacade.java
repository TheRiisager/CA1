package facades;

import dto.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getMembersCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long memberCount = (long) em.createQuery("SELECT COUNT(m) FROM Member m").getSingleResult();
            return memberCount;
        } finally {
            em.close();
        }

    }

    public List<MemberDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> members = query.getResultList();
        List<MemberDTO> memberDTOs = new ArrayList();
        members.forEach((Member member) -> {
            memberDTOs.add(new MemberDTO(member));
        });
        return memberDTOs;
    }
}
