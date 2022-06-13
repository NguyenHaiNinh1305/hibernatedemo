package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.demo.entity.Driver;
import com.demo.utils.HibernateUtil;
import com.demo.utils.InputNumberUtil;

public class DriverSer {
  Logger logger = Logger.getLogger(DriverSer.class);

  public List<Driver> createDriver() {
    List<Driver> drivers = new ArrayList<>();
    System.out.println("Please enter number of Drivers");
    int num = InputNumberUtil.returnInt();
    for (int i = 0; i < num; i++) {
      Driver driver = new Driver();
      driver.input();
      drivers.add(driver);
    }
    return drivers;
  }

  public void recordDriverToDatabase() {
    List<Driver> drivers = new ArrayList<>();
    drivers = createDriver();
    for (int i = 0; i < drivers.size(); i++) {
      Session session = null;
      try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Driver driver = drivers.get(i);
        session.save(driver);
        session.getTransaction().commit();
      } catch (Exception e) {
        session.getTransaction().rollback();
        logger.error(e);
      } finally {
        if (session != null) {
          session.close();
        }
      }
    }
  }

  public List<Driver> getAllDriversFromDatabase() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Driver> drivers = session.createQuery("from Driver").list();
      return drivers;
    } catch (Exception e) {
      session.getTransaction().rollback();
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return null;
  }

  public void showAllDrivers() {
    for (Driver driver : getAllDriversFromDatabase()) {
      System.out.println(driver);
    }
  }

  public Driver finDriverById() {
    do {
      System.out.println("Please type id");
      int driverId = InputNumberUtil.returnInt();
      Session session = null;
      try {
        session = HibernateUtil.getSessionFactory().openSession();
        Driver driver = session.get(Driver.class, driverId);
        return driver;
      } catch (Exception e) {
        logger.error(e);
        session.getTransaction().rollback();
      } finally {
        if (session != null) {
          session.close();
        }
      }
    } while (true);
  }
}
