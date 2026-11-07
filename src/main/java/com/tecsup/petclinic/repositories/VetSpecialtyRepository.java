package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetSpecialtyRepository extends JpaRepository<VetSpecialty, VetSpecialtyId> {

    @Query("select vs from vet_specialties vs join fetch vs.specialty s where vs.id.vetId = :vetId")
    List<VetSpecialty> findByVetId(@Param("vetId") Integer vetId);

    @Query("select vs from vet_specialties vs join fetch vs.vet v where vs.id.specialtyId = :specialtyId")
    List<VetSpecialty> findBySpecialtyId(@Param("specialtyId") Integer specialtyId);
}
