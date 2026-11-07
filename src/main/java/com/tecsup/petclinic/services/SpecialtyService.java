package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;

import java.util.List;

public interface SpecialtyService {

    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    void delete(Integer id);

    Specialty findById(Integer id);

    List<Specialty> findByName(String name);

    List<Specialty> findAll();
}
