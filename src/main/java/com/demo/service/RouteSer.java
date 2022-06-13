package com.demo.service;

import com.demo.entity.Route;
import com.demo.utils.HibernateUtil;
import com.demo.utils.InputNumberUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RouteSer {
  static Logger logger = Logger.getLogger(RouteSer.class);

  public List<Route> createRoute() {
    List<Route> routes = new ArrayList<>();
    System.out.println("Please enter number of Routes");
    int num = InputNumberUtil.returnInt();
    for (int i = 0; i < num; i++) {
      Route route = new Route();
      route.input();
      routes.add(route);
    }
    return routes;
  }

  public void recordRouteToDatabase() {
    List<Route> routes = new ArrayList<>();
    routes = createRoute();
    for (int i = 0; i < routes.size(); i++) {
      Session session = null;
      try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Route route = routes.get(i);
        session.save(route);
        session.getTransaction().commit();
      } catch (Exception e) {
        logger.error(e);
      } finally {
        if (session != null) {
          session.close();
        }
      }
    }
  }


  public List<Route> getAllRoutesFromDatabase() {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      List<Route> routes = new ArrayList<>();
      routes = session.createQuery("from Route").list();
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

  public void showAllRoutes() {
    for (Route route : getAllRoutesFromDatabase()) {
      System.out.println(route);
    }
  }

  public static Route finRouteById() {
    do {
      System.out.println("Please type id");
      int routeId = InputNumberUtil.returnInt();
      Session session = null;
      try {
        session = HibernateUtil.getSessionFactory().openSession();
        Route route = session.get(Route.class, routeId);
        return route;
      } catch (Exception e) {
        logger.error(e);
      } finally {
        if (session != null) {
          session.close();
        }
      }
    } while (true);
  }
}


