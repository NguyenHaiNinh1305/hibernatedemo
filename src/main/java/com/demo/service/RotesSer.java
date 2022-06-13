package com.demo.service;

import com.demo.entity.Driver;
import com.demo.entity.Rotes;
import com.demo.entity.RouteAndTimes;
import com.demo.utils.HibernateUtil;
import com.demo.utils.InputNumberUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class RotesSer {
  static Logger logger = Logger.getLogger(RouteSer.class);

  public void createRoter(Driver driver, List<RouteAndTimes> routeAndTimes) {
    Rotes rotes = new Rotes();
    rotes.setDriver(driver);
    rotes.setRouteAndTimes(routeAndTimes);
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(rotes);
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

  public static Rotes finRotesByDriverId(Driver driver) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      String hql = "From Rotes where Rotes.driver_id = :parameter";
      Query query = session.createQuery(hql);
      query.setParameter("parameter", driver.getDriverID());
      Rotes rotes = (Rotes) query.uniqueResult();
      return rotes;
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

  public static int returnNumOfTask(Driver driver) {
    Rotes rotes = finRotesByDriverId(driver);
    if (rotes != null) {
      int num = 0;
      do {
        System.out.println("Please enter num of task maximum " + (15 - rotes.getTotalTask()));
        num = InputNumberUtil.returnInt();
        return num;
      } while ((15 - rotes.getTotalTask()) - num < 0);
    }
    return 0;
  }

  public void sortRotesByDriverName() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      String hql = "from Rotes join Driver on Rotes.driver_Id = Driver.driver_Id order by Driver.name";
      Query query = session.createQuery(hql);
    } catch (Exception e) {
      session.getTransaction().rollback();
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  public List<Rotes> getAllRotesFromDatabase() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Rotes> rotesLists = session.createQuery("from Rotes").list();
      return rotesLists;
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

  public void showAllRotes() {
    for (Rotes rotes : getAllRotesFromDatabase()) {
      System.out.println(rotes);
    }
  }

}
