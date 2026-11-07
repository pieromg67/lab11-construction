package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Override
    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet update(Vet vet) {
        findById(vet.getId());
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Integer id) {
        Vet vet = findById(id);
        vetRepository.delete(vet);
    }

    @Override
    public Vet findById(Integer id) {
        return vetRepository.findById(id)
                .orElseThrow(() -> new VetNotFoundException("Vet not found with id: " + id));
    }

    @Override
    public List<Vet> findByLastName(String lastName) {
        return vetRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<Vet> findByActive(Boolean active) {
        return vetRepository.findByActive(active);
    }

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
}
