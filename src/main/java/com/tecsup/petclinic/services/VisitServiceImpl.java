package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        findById(visit.getId());
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Integer id) {
        Visit visit = findById(id);
        visitRepository.delete(visit);
    }

    @Override
    public Visit findById(Integer id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found with id: " + id));
    }

    @Override
    public List<Visit> findByPetId(Integer petId) {
        return visitRepository.findByPetId(petId);
    }

    @Override
    public List<Visit> findByVetId(Integer vetId) {
        return visitRepository.findByVetId(vetId);
    }

    @Override
    public List<Visit> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return visitRepository.findByVisitDateBetween(startDate, endDate);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}
