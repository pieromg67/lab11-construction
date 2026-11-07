package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVetId(1);
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("General checkup");
        visit.setCost(new BigDecimal("80.00"));

        Visit created = visitService.create(visit);

        log.info("Created visit: {}", created);
        assertNotNull(created.getId());
        assertEquals(1, created.getPetId());
        assertEquals(1, created.getVetId());
        assertEquals("General checkup", created.getDescription());
    }

    @Test
    public void testFindVisitById() {
        Visit visit = visitService.findById(1);

        assertNotNull(visit);
        assertEquals(7, visit.getPetId());
        assertEquals("rabies shot", visit.getDescription());
    }

    @Test
    public void testUpdateVisit() {
        Visit visit = visitService.create(new Visit(null, 1, 1, LocalDate.now().plusDays(5), "Vaccination", new BigDecimal("120.00")));
        visit.setDescription("Follow-up review");
        visit.setCost(new BigDecimal("140.00"));

        Visit updated = visitService.update(visit);

        assertEquals("Follow-up review", updated.getDescription());
        assertEquals(new BigDecimal("140.00"), updated.getCost());
    }

    @Test
    public void testDeleteVisit() {
        Visit visit = visitService.create(new Visit(null, 1, 1, LocalDate.now().plusDays(10), "Diagnostic", new BigDecimal("220.00")));

        visitService.delete(visit.getId());

        assertThrows(VisitNotFoundException.class, () -> visitService.findById(visit.getId()));
    }
}
