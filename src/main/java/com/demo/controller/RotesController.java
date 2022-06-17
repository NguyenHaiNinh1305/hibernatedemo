package com.demo.controller;

import com.demo.entity.Rotes;
import com.demo.service.RotesSer;
import oracle.jdbc.proxy.annotation.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Rotes")
public class RotesController {
  RotesSer rotesSer = new RotesSer();

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Rotes> getAllRotes() {
    return rotesSer.getAllRotes();
  }

  @Post
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void createRotes(Rotes rotes) {
    rotesSer.createRotes(rotes);
  }

  @GET
  @Path("/{id}/{dateparam}")
  @Produces(MediaType.APPLICATION_JSON)
  public float getTotalDistanceTaskAssignedOfDriverPerday(@PathParam("id") int id, @PathParam("dateparam") String dateparam) {
    return rotesSer.calcTotalDistanceTaskAssignedOfDriverPerday(id, dateparam);
  }
}
