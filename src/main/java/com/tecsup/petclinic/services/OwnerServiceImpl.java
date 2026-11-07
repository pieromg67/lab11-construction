package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        findById(owner.getId());
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Integer id) {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    @Override
    public Owner findById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<Owner> findByCity(String city) {
        return ownerRepository.findByCityIgnoreCase(city);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
