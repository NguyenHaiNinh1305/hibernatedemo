package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "Rotes")
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@IdClass(Rotes.class)
public class Rotes implements Serializable {

  @Id
  @Column(name = "driver_id")
  private int driver_id;

  @Id
  @Column(name = "route_id")
  private int route_id;

  @Column(name = "times", nullable = false)
  private int times;

  @Column(name = "date_assign_task")
  @Temporal(value = TemporalType.DATE)
  private Date dateAssignTask;

}
