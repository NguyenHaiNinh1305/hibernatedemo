package com.demo.service;

import com.demo.entity.Rotes;
import com.demo.repository.RotesRepository;
import com.demo.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;

import java.util.Date;
import java.util.List;


public class RotesSer {
  RotesRepository rotesRepository = new RotesRepository();

  static Logger logger = Logger.getLogger(RouteSer.class);

  public List<Rotes> getAllRotes() {
    return rotesRepository.getAll();
  }

  public int getTotalTaskOfDriverByDriverId(Rotes rotes) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      String sql = "select sum(rotes.times) AS s from rotes where rotes.driver_id = (:p) and rotes.date_assign_task = (:date)";
      SQLQuery query = session.createSQLQuery(sql);
      query.addScalar("s", new IntegerType());
      query.setParameter("p", rotes.getDriver_id());
      query.setParameter("date", rotes.getDateAssignTask());
      return (int) query.uniqueResult();
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return 0;
  }

  public float calcTotalDistanceTaskAssignedOfDriverPerday(int id, String dateParam) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      String sql = "select sum(route.distance) s from route " + "join rotes " + "on route.route_id = rotes.route_id " + "where rotes.driver_id = (:id) and to_char(rotes.date_assign_task) = to_char(TO_DATE((:dateparam), 'DD-MM-YY'))";
      SQLQuery query = session.createSQLQuery(sql);
      query.addScalar("s", new FloatType());
      query.setParameter("id", id);
      query.setParameter("dateparam", dateParam);
      return (Float) query.uniqueResult();
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return 0;
  }

  public int findRouteIdinRotesTableByRotesParam(Rotes rotes) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      String sql = "select rotes.route_id from rotes where rotes.route_id = (:p)";
      SQLQuery query = session.createSQLQuery(sql);
      query.setParameter("p", rotes.getRoute_id());
      return (int) query.uniqueResult();
    } catch (Exception e) {
      logger.error(e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return 0;
  }

  public boolean checkTimesToAssignTaskByDriverIdInCondition(Rotes rotes) {
    int num = getTotalTaskOfDriverByDriverId(rotes);
    if (num >= 0 && num <= 15) {
      return true;
    }
    return false;
  }

  public boolean createRotes(Rotes rotes) {
    if (DriverSer.hasDriverWithId(rotes.getDriver_id()) && RouteSer.hasRouteWithId(rotes.getRoute_id()) && checkTimesToAssignTaskByDriverIdInCondition(rotes) && findRouteIdinRotesTableByRotesParam(rotes) == 0) {
      int times = getTotalTaskOfDriverByDriverId(rotes) + rotes.getTimes();
      if (times <= 15) {
        rotes.setDateAssignTask(new Date());
        rotesRepository.create(rotes);
        return true;
      } else {
        logger.error("over tasks");
        return false;
      }
    } else {
      logger.error("cannot find or duplicate Route");
    }
    return false;
  }
}
