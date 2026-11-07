package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;
    private final VetSpecialtyRepository vetSpecialtyRepository;

    @Override
    public VetSpecialty assignSpecialtyToVet(Integer vetId, Integer specialtyId) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new VetNotFoundException("Vet not found with id: " + vetId));
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found with id: " + specialtyId));

        VetSpecialty entity = new VetSpecialty();
        entity.setId(new VetSpecialtyId(vetId, specialtyId));
        entity.setVet(vet);
        entity.setSpecialty(specialty);
        entity.setCertificationDate(LocalDate.now());
        entity.setYearsExperience(3);
        entity.setIsPrimary(Boolean.FALSE);
        entity.setNotes("Assigned in test");
        return vetSpecialtyRepository.save(entity);
    }

    @Override
    public List<Specialty> findSpecialtiesByVet(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId)
                .stream()
                .map(VetSpecialty::getSpecialty)
                .toList();
    }

    @Override
    public List<Vet> findVetsBySpecialty(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId)
                .stream()
                .map(VetSpecialty::getVet)
                .toList();
    }

    @Override
    public void deleteAssignment(Integer vetId, Integer specialtyId) {
        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);
        if (!vetSpecialtyRepository.existsById(id)) {
            throw new IllegalArgumentException("Assignment not found for vetId=" + vetId + " and specialtyId=" + specialtyId);
        }
        vetSpecialtyRepository.deleteById(id);
    }
}
