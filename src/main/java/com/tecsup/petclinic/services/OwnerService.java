package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;

import java.util.List;

public interface OwnerService {

    Owner create(Owner owner);

    Owner update(Owner owner);

    void delete(Integer id);

    Owner findById(Integer id);

    List<Owner> findByLastName(String lastName);

    List<Owner> findByCity(String city);

    List<Owner> findAll();
}
