package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialtyService specialtyService;

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyService.create(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyService.update(specialty);
    }

    @Override
    public void delete(Integer id) {
        specialtyService.delete(id);
    }

    @Override
    public Specialty findById(Integer id) {
        return specialtyService.findById(id);
    }

    @Override
    public List<Specialty> findByName(String name) {
        return specialtyService.findByName(name);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyService.findAll();
    }
}
