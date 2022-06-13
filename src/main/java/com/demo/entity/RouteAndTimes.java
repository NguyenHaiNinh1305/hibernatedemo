package com.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RouteAndTimes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteAndTimes implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_RouteAndTimes")
  @SequenceGenerator(name = "GEN_ID_RouteAndTimes", sequenceName = "SEQ_RouteAndTimes", allocationSize = 1)
  @Column(name = "rt_ID", nullable = false)
  private int id;

  @OneToOne
  @JoinColumn(name = "route_id", referencedColumnName = "route_id")
  private Route route;

  @Column(name = "times_drive", nullable = false)
  private int timesDrive;

  @ManyToOne
  @JoinColumn(name = "Rotes_id")
  private Rotes rotes;


}
