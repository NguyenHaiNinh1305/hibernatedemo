package com.demo.service;

import com.demo.entity.Driver;
import com.demo.entity.Route;
import com.demo.entity.RouteAndTimes;
import com.demo.utils.InputNumberUtil;

import java.util.ArrayList;
import java.util.List;

public class RouteAndTimesSer {

  public RouteAndTimes createRouteAndTime(Driver driver) {
    RouteAndTimes routeAndTimes = new RouteAndTimes();
    routeAndTimes.setRoute(RouteSer.finRouteById());
    routeAndTimes.setTimesDrive(RotesSer.returnNumOfTask(driver));
    return routeAndTimes;
  }

  public List<RouteAndTimes> creatListRoutAndtimes(List<Route> routeList, Driver driver) {
    int num = 0;
    List<RouteAndTimes> routeAndTimesList = new ArrayList<>();
    do {
      System.out.println("Enter number of route assign task to driver");
      num = InputNumberUtil.returnInt();
    } while (num > routeList.size());
    for (int i = 0; i < num; i++) {
      RouteAndTimes routeAndTimes = createRouteAndTime(driver);
      routeAndTimesList.add(routeAndTimes);
    }
    return routeAndTimesList;
  }

}
