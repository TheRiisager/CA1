package facades;

import dto.JokeDTO;
import entities.Joke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joakim Skaarup Stensnæs
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //returns a list of JokeDTO's, using MySQL to get the complete table from DB
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j", Joke.class);
        List<Joke> jokes = query.getResultList();
        List<JokeDTO> JokeDTOs = new ArrayList();
        jokes.forEach((Joke joke) -> {
            JokeDTOs.add(new JokeDTO(joke));
        });
        return JokeDTOs;
    }

    //returns a JokeDTO, using the parsed ID as a parameter in MySQL
    public JokeDTO getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j WHERE j.id=:id", Joke.class);
        query.setParameter("id", id);
        return new JokeDTO(query.getSingleResult());
    }

    //returns a JokeDTO, using MySQL to randomize which joke, from the DB
    public JokeDTO getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j ORDER BY FUNCTION('RAND')", Joke.class);
        query.setMaxResults(1);
        return new JokeDTO(query.getSingleResult());
    }
}
