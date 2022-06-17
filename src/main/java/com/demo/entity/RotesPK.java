package com.demo.entity;

import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Embeddable
@Data
public class RotesPK implements Serializable {
  @OneToMany
  @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
  private List<Driver> drivers;

  @OneToMany
  @JoinColumn(name = "route_id", referencedColumnName = "route_id")
  private List<Route> routes;
}
