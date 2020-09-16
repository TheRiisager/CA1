/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lasse Emil
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

//    public static CarFacade getCarFacade(EntityManagerFactory EMF) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CarDTO createCar(int id, int year, String make, String model, String serialNumber, String plate, String owner, double price) {
        Car car = new Car(year, make, model, serialNumber, plate, owner, price);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(car);
    }

    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> cars = query.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        cars.forEach((Car car) -> {
            carDTOs.add(new CarDTO(car));
        });
        return carDTOs;
    }

    public CarDTO getCarById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car e2 = em.find(Car.class, id);
            CarDTO e1 = new CarDTO(e2);
            return e1;
        } finally {
            em.close();
        }
    }
//
//    public static void main(String[] args) {
//          EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//             EntityManager em = emf.createEntityManager();
//        Car c1 = new Car(1997, "Ford", "E350", "FE852963741", "AF 12 345", "Benjamin David Choleva", 3000);
//        Car c2 = new Car(1999, "Chevy", "Venture", "CH741852963", "CE 98 765", "Frederik Riisager Johnsen", 4900);
//        Car c3 = new Car(2000, "Chevy", "Venture", "CH123456789", "CE 14 253", "Benjamin David Choleva", 5000);
//        Car c4 = new Car(1996, "Jeep", "Grand Cherokee", "GC159487263", "CG 26 351", "Lasse Emil Støvring Larsen", 4799);
//        Car c5 = new Car(1997, "Volvo", "V70", "VO623519478", "VO 94 963", "Joakim Skaarup Stensnæs", 44799);
//        try {
//            em.getTransaction().begin();
//            em.createQuery("DELETE from Car").executeUpdate();
//            em.persist(c1);
//            em.persist(c2);
//            em.persist(c3);
//            em.persist(c4);
//            em.persist(c5);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        
//        
//    }
    
    
    
    
    
    
    
    
}
