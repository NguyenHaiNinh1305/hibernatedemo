package com.demo.entity;


import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_DRIVER")
  @SequenceGenerator(name = "GEN_ID_DRIVER", sequenceName = "SEQ_DRIVER", allocationSize = 1,initialValue=10000)
  @Column(name = "driver_id")
  private int driverID;

  @Column(name = "driver_name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone_number", nullable = false)
  private String phone;

  @Column(name = "level_driver", nullable = false)
  private String level;





}
