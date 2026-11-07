package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.VetSpecialty;

import java.util.List;

public interface VetSpecialtyService {

    VetSpecialty assignSpecialtyToVet(Integer vetId, Integer specialtyId);

    List<Specialty> findSpecialtiesByVet(Integer vetId);

    List<Vet> findVetsBySpecialty(Integer specialtyId);

    void deleteAssignment(Integer vetId, Integer specialtyId);
}
