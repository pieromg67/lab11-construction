package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {

    private final TypeService typeService;

    @Override
    public Type create(Type type) {
        return typeService.create(type);
    }

    @Override
    public Type update(Type type) {
        return typeService.update(type);
    }

    @Override
    public void delete(Integer id) {
        typeService.delete(id);
    }

    @Override
    public Type findById(Integer id) {
        return typeService.findById(id);
    }

    @Override
    public List<Type> findByName(String name) {
        return typeService.findByName(name);
    }

    @Override
    public List<Type> findAll() {
        return typeService.findAll();
    }
}
