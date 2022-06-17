package com.demo.service;

import com.demo.entity.Route;
import com.demo.repository.RouteRepository;

import java.util.List;

public class RouteSer {
  static RouteRepository routeRepository = new RouteRepository();

  public List<Route> getAllRoute() {
    return routeRepository.getAll();
  }

  public Route findRouteById(int id) {
    return routeRepository.getById(id);
  }

  public void createRoute(Route route) {
    routeRepository.create(route);

  }

  public static boolean hasRouteWithId(int id) {
    if (routeRepository.getById(id) != null) {
      return true;
    }
    return false;
  }
}


