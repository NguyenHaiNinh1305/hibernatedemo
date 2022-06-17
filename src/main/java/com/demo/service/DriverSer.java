package com.demo.service;

import com.demo.entity.Driver;
import com.demo.enumerator.Level;
import com.demo.repository.DriverRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class DriverSer {
  Logger logger = Logger.getLogger(DriverSer.class);
  static DriverRepository driverRepository = new DriverRepository();

  public List<Driver> getAllDriver() {
    return driverRepository.getAll();
  }

  public Driver findDriverById(int id) {
    return driverRepository.getById(id);
  }

  public void createDriver(Driver driver) {
    for (int i = 0; i < Level.values().length; i++) {
      if (Level.values()[i].toString() == driver.getLevel()) {
        driverRepository.create(driver);
        return;
      }
    }
    logger.debug("No enum constant");
  }

  public static boolean hasDriverWithId(int id) {
    if (driverRepository.getById(id) != null) {
      return true;
    }
    return false;
  }
}
