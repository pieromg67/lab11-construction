package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.VetSpecialty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        VetSpecialty relation = vetSpecialtyService.assignSpecialtyToVet(1, 2);

        assertNotNull(relation);
        assertEquals(1, relation.getId().getVetId());
        assertEquals(2, relation.getId().getSpecialtyId());
        assertFalse(relation.getIsPrimary());
    }

    @Test
    public void testFindSpecialtiesByVet() {
        List<Specialty> specialties = vetSpecialtyService.findSpecialtiesByVet(2);

        assertFalse(specialties.isEmpty());
        assertTrue(specialties.stream().anyMatch(s -> s.getName().equals("radiology")));
    }

    @Test
    public void testFindVetsBySpecialty() {
        List<Vet> vets = vetSpecialtyService.findVetsBySpecialty(1);

        assertFalse(vets.isEmpty());
        assertTrue(vets.stream().anyMatch(v -> v.getId() == 2));
    }

    @Test
    public void testDeleteSpecialtyAssignment() {
        VetSpecialty relation = vetSpecialtyService.assignSpecialtyToVet(1, 3);

        vetSpecialtyService.deleteAssignment(1, 3);

        List<Specialty> specialties = vetSpecialtyService.findSpecialtiesByVet(1);
        assertFalse(specialties.stream().anyMatch(s -> s.getId().equals(3)));
        assertNotNull(relation.getId());
    }
}
