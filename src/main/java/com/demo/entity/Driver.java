package com.demo.entity;


import java.io.Serializable;
import java.util.Scanner;
import javax.persistence.*;
import com.demo.utils.InputNumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Driver`")
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

  @OneToOne(mappedBy = "driver")
  private Rotes rotes;

  @Column(name = "level_driver", nullable = false)
  private String level;

  public void input() {
    Scanner sc;
    System.out.println("Input full name: ");
    sc = new Scanner(System.in);
    name = sc.nextLine();
    System.out.println("Input address: ");
    sc = new Scanner(System.in);
    address = sc.nextLine();
    System.out.println("Input phone Number: ");
    phone = sc.nextLine();
    System.out.println("Pleas type choose level 1-A 2-B 3-C 4-D 5-E 6-F ");
    boolean check = false;
    do {
      int choose = InputNumberUtil.returnInt();
      switch (choose) {
        case 1:
          level = "A";
          check = true;
          break;
        case 2:
          level = "B";
          check = true;
          break;
        case 3:
          level = "C";
          check = true;
          break;
        case 4:
          level = "D";
          check = true;
          break;
        case 5:
          level = "E";
          check = true;
          break;
        case 6:
          level = "F";
          check = true;
          break;
        default:
          System.out.println("Have no selection for this number");
          System.out.println(("Please re-type!"));
          break;
      }
    } while (check == false);
  }

}
