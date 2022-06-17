package com.demo.controller;

import com.demo.entity.Route;
import com.demo.service.RouteSer;
import oracle.jdbc.proxy.annotation.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Route")
public class RouteController {

  RouteSer routeSer = new RouteSer();

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Route> getAllRoutes() {
    return routeSer.getAllRoute();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Route getRouteById(@PathParam("id") int id) {
    return routeSer.findRouteById(id);
  }

  @Post
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void createRoute(Route route) {
    routeSer.createRoute(route);
  }
}
