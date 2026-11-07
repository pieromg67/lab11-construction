package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        findById(specialty.getId());
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Integer id) {
        Specialty specialty = findById(id);
        specialtyRepository.delete(specialty);
    }

    @Override
    public Specialty findById(Integer id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found with id: " + id));
    }

    @Override
    public List<Specialty> findByName(String name) {
        return specialtyRepository.findByName(name)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
