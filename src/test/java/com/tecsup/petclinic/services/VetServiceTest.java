package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testCreateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Ana");
        vet.setLastName("Mendoza");
        vet.setEmail("ana.mendoza@petclinic.com");
        vet.setPhone("5551112222");
        vet.setActive(Boolean.TRUE);

        Vet created = vetService.create(vet);

        log.info("Created vet: {}", created);
        assertNotNull(created.getId());
        assertEquals("Ana", created.getFirstName());
        assertEquals("Mendoza", created.getLastName());
        assertTrue(created.getActive());
    }

    @Test
    public void testFindVetById() {
        Vet vet = vetService.findById(1);

        assertNotNull(vet);
        assertEquals("James", vet.getFirstName());
        assertEquals("Carter", vet.getLastName());
    }

    @Test
    public void testUpdateVet() {
        Vet vet = vetService.create(new Vet(null, "Rosa", "Perez", "rosa.perez@petclinic.com", "5559876543", true));
        vet.setPhone("9998887777");
        vet.setEmail("rosa.updated@petclinic.com");

        Vet updated = vetService.update(vet);

        assertEquals("9998887777", updated.getPhone());
        assertEquals("rosa.updated@petclinic.com", updated.getEmail());
    }

    @Test
    public void testDeleteVet() {
        Vet vet = vetService.create(new Vet(null, "Luis", "Torres", "luis.torres@petclinic.com", "5553334444", true));

        vetService.delete(vet.getId());

        assertThrows(VetNotFoundException.class, () -> vetService.findById(vet.getId()));
    }
}
