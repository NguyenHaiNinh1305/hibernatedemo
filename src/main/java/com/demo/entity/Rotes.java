package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Rotes")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Rotes implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_Rotes")
  @SequenceGenerator(name = "GEN_ID_Rotes", sequenceName = "SEQ_Rotes", allocationSize = 1)
  @Column(name = "Rotes_id")
  private int id;

  @OneToOne
  @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
  private Driver driver;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "rotes")
  private List<RouteAndTimes> routeAndTimes;

  public int getTotalTask() {
    int sum = 0;
    for (int i = 0; i < routeAndTimes.size(); i++) {
      sum += routeAndTimes.get(i).getTimesDrive();
    }
    return sum;
  }

  public float getTotalDistance() {
    float sum = 0;
    for (int i = 0; i < routeAndTimes.size(); i++) {
      sum += routeAndTimes.get(i).getRoute().getDistance() * routeAndTimes.get(i).getTimesDrive();
    }
    return sum;
  }
}
