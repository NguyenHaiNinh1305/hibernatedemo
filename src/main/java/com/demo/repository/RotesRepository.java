package com.demo.repository;

import com.demo.GeneralMethod;
import com.demo.entity.Rotes;
import com.demo.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class RotesRepository implements GeneralMethod {
  Logger logger = Logger.getLogger(RotesRepository.class);

  @Override
  public List<Rotes> getAll() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Rotes> Rotes = session.createQuery("from Rotes").list();
      return Rotes;
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
  public Rotes getById(int id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.get(Rotes.class, id);
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return null;
  }

  public void create(Rotes obj) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(obj);
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
