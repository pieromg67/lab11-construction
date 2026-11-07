package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    @Test
    public void testCreateSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setName("oncology");
        specialty.setOffice("North Wing");
        specialty.setHOpen(9);
        specialty.setHClose(17);

        Specialty created = specialtyService.create(specialty);

        log.info("Created specialty: {}", created);
        assertNotNull(created.getId());
        assertEquals("oncology", created.getName());
        assertEquals("North Wing", created.getOffice());
    }

    @Test
    public void testFindSpecialtyById() {
        Specialty specialty = specialtyService.findById(1);

        assertNotNull(specialty);
        assertEquals("radiology", specialty.getName());
        assertEquals("Farewell", specialty.getOffice());
    }

    @Test
    public void testUpdateSpecialty() {
        Specialty specialty = specialtyService.create(new Specialty(null, "orthopedics", "Center", 8, 15));
        specialty.setOffice("South Wing");
        specialty.setHClose(16);

        Specialty updated = specialtyService.update(specialty);

        assertEquals("South Wing", updated.getOffice());
        assertEquals(16, updated.getHClose());
    }

    @Test
    public void testDeleteSpecialty() {
        Specialty specialty = specialtyService.create(new Specialty(null, "anesthesia", "East Wing", 7, 18));

        specialtyService.delete(specialty.getId());

        assertThrows(SpecialtyNotFoundException.class, () -> specialtyService.findById(specialty.getId()));
    }
}
