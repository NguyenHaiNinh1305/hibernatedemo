package com.demo;

import com.demo.entity.Driver;

import java.util.List;

public interface GeneralMethod {

   <T> List<T> getAll();
   <T> T getById(int id);

//  void create(Object obj);
}
