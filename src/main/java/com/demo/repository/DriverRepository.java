package com.demo.repository;

import com.demo.GeneralMethod;
import com.demo.entity.Driver;
import com.demo.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class DriverRepository implements GeneralMethod {
  Logger logger = Logger.getLogger(DriverRepository.class);

  @Override
  public List<Driver> getAll() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Driver> drivers = session.createQuery("from Driver").list();
      return drivers;
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return null;
  }

  @Override
  public Driver getById(int id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return session.get(Driver.class, id);
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return null;
  }


  public void create(Driver driver) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
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
