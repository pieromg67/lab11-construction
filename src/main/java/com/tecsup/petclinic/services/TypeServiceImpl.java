package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
import com.tecsup.petclinic.repositories.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public Type create(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type update(Type type) {
        findById(type.getId());
        return typeRepository.save(type);
    }

    @Override
    public void delete(Integer id) {
        Type type = findById(id);
        typeRepository.delete(type);
    }

    @Override
    public Type findById(Integer id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new TypeNotFoundException("Type not found with id: " + id));
    }

    @Override
    public List<Type> findByName(String name) {
        return typeRepository.findByName(name)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
}
