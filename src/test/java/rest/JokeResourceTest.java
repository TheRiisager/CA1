package rest;

import entities.Joke;
import entities.Member;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Joke j1, j2, j3, j4;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
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

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/jokes/all").then().statusCode(200);
    }

    //This test assumes the database contains two four
    @Test
    public void testGetJokes() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", hasSize(4));

        //
        given()
                .contentType("application/json")
                .get("/jokes/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("type", hasItem("Skeleton pun"));
    }

//    @Test
//    public void testCount() throws Exception {
//        given()
//        .contentType("application/json")
//        .get("/xxx/count").then()
//        .assertThat()
//        .statusCode(HttpStatus.OK_200.getStatusCode())
//        .body("count", equalTo(4));   
//    }
}
