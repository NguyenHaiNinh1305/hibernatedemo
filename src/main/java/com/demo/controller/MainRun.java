package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.entity.Driver;
import com.demo.entity.Rotes;
import com.demo.entity.Route;
import com.demo.entity.RouteAndTimes;
import com.demo.service.DriverSer;
import com.demo.service.RotesSer;
import com.demo.service.RouteAndTimesSer;
import com.demo.service.RouteSer;
import com.demo.utils.InputNumberUtil;

public class MainRun {
  private DriverSer driverSer = new DriverSer();
  private RouteSer routeSer = new RouteSer();
  private RotesSer rotesSer = new RotesSer();
  private RouteAndTimesSer routeAndTimesSer = new RouteAndTimesSer();

  public void processTask() {
    int choose = 0;
    do {
      showMenu();
      choose = InputNumberUtil.returnInt();
      switch (choose) {
        case 1:
          driverSer.recordDriverToDatabase();
          break;
        case 2:
          routeSer.recordRouteToDatabase();
          break;
        case 3:
          Driver driver = driverSer.finDriverById();
          List<Route> routes = routeSer.getAllRoutesFromDatabase();
          List<RouteAndTimes> routeAndTimesList = routeAndTimesSer.creatListRoutAndtimes(routes, driver);
          rotesSer.createRoter(driver, routeAndTimesList);
          break;
        case 4:
          Driver driverA = driverSer.finDriverById();
          Rotes rotes = RotesSer.finRotesByDriverId(driverA);
          System.out.println("Total task is" + rotes.getTotalTask());
          break;
        case 5:
          rotesSer.sortRotesByDriverName();
          rotesSer.showAllRotes();
          break;
        case 6:
          driverSer.showAllDrivers();
          break;
        case 7:
          routeSer.showAllRoutes();
          break;
        case 8:
          rotesSer.showAllRotes();
          break;
        case 10:
          System.out.println("finish");
          return;
        default:
          System.out.println("Please re-type correct selection");
          break;
      }
    } while (choose != 10);
  }

  public static void showMenu() {
    System.out.println("/****************************************/");
    System.out.println("1. Add Drivers.\n" + "2. Add Routes.\n" + "3. Assign tasks.\n"
      + "4. Sort task list from driver name.\n" + "5. Calculate Total distances of drivers.\n" + "6. Load all Drivers from file.\n"
      + "7. Load all Routes form file.\n" + "8. Load all roters form file.\n" + "10. Exit.");
    System.out.println("/****************************************/");
  }


}
