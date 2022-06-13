package com.demo.entity;

import com.demo.utils.InputNumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Route")
public class Route implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_Route")
  @SequenceGenerator(name = "GEN_ID_Route", sequenceName = "SEQ_Route", allocationSize = 1, initialValue = 100)
  @Column(name = "route_id", nullable = false)
  private int routeId;

  @Column(name = "distance", nullable = false)
  private float distance;

  @Column(name = "bustop", nullable = false)
  private int busStop;


  public void input() {

    System.out.println("Input distance: ");
    distance = InputNumberUtil.returnFloatVar();
    System.out.println("Input number of busstop: ");
    busStop = InputNumberUtil.returnInt();
  }

}
