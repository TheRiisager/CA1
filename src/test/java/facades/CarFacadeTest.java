/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Lasse Emil
 */
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    private Car c1;
    private Car c2;
    private Car c3;
    private Car c4;
    private Car c5;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        c1 = new Car(1997, "Ford", "E350", "FE852963741", "AF 12 345", "Benjamin David Choleva", 3000);
        c2 = new Car(1999, "Chevy", "Venture", "CH741852963", "CE 98 765", "Frederik Riisager Johnsen", 4900);
        c3 = new Car(2000, "Chevy", "Venture", "CH123456789", "CE 14 253", "Benjamin David Choleva", 5000);
        c4 = new Car(1996, "Jeep", "Grand Cherokee", "GC159487263", "CG 26 351", "Lasse Emil Støvring Larsen", 4799);
        c5 = new Car(1997, "Volvo", "V70", "VO623519478", "VO 94 963", "Joakim Skaarup Stensnæs", 44799);
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Car").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
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
    public void testGetAllMembers() {
        assertEquals(5, facade.getAllCars().size(), "Expects five cars in the database");
    }

}
