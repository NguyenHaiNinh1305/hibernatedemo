package com.demo.repository;

import com.demo.GeneralMethod;
import com.demo.entity.Route;
import com.demo.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class RouteRepository implements GeneralMethod {
  Logger logger = Logger.getLogger(RouteRepository.class);

  @Override
  public List<Route> getAll() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Route> routes = session.createQuery("from Route ").list();
      return routes;
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
  public Route getById(int id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return session.get(Route.class, id);

    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return null;
  }


  public void create(Route obj) {
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
