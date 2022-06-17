package com.demo.controller;

import com.demo.entity.Driver;
import com.demo.service.DriverSer;
import oracle.jdbc.proxy.annotation.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Driver")
public class DriverController {

  DriverSer driverSer = new DriverSer();

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Driver> getAllDrivers() {
    return driverSer.getAllDriver();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Driver getDriverById(@PathParam("id") int id) {
    return driverSer.findDriverById(id);
  }

  @Post
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void createDriver(Driver driver) {
    driverSer.createDriver(driver);
  }
}
