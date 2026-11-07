package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;

import java.util.List;

public interface VetService {

    Vet create(Vet vet);

    Vet update(Vet vet);

    void delete(Integer id);

    Vet findById(Integer id);

    List<Vet> findByLastName(String lastName);

    List<Vet> findByActive(Boolean active);

    List<Vet> findAll();
}
