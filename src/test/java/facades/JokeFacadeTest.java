package facades;

import entities.Joke;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    private Joke j1, j2, j3, j4;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        j1 = new Joke("Knock! Knock! Who's there? Candice. Candice who? Candice door open, or am I stuck out here?", "Knock, knock", "Joakim Skaarup Stensnæs");
        j2 = new Joke("Knock! Knock! Who's there? Voodoo. Voodoo who? Voodoo you think you are, asking all these questions?", "Knock, knock", "Joakim Skaarup Stensnæs");
        j3 = new Joke("The skeleton canceled the gallery showing of his skull-ptures because his heart wasn't in it.", "Skeleton pun", "Frederik Riisager Johnsen");
        j4 = new Joke("Q. How does a computer get drunk? A. It takes screenshots." ,"Computer pun", "Lasse Emil Støvrin Larsen");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

        @Test
        public void testGetAllJokes() {
        assertEquals(4, facade.getAllJokes().size(), "Expects four members in the database");
    }
}