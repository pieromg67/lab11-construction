package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;

import java.util.List;

public interface TypeService {

    Type create(Type type);

    Type update(Type type);

    void delete(Integer id);

    Type findById(Integer id);

    List<Type> findByName(String name);

    List<Type> findAll();
}
