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

    public static CarFacade getCarFacade(EntityManagerFactory EMF) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CarDTO createCar(int id, int year, String model, String make, String serialNumber, String plate, String owner, double price) {
        Car car = new Car(year, model, make, serialNumber, plate, owner, price);
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

}
