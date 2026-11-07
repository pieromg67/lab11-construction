package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Ana");
        owner.setLastName("Torres");
        owner.setAddress("Calle Principal 123");
        owner.setCity("Lima");
        owner.setTelephone("5551234567");

        Owner created = ownerService.create(owner);

        log.info("Created owner: {}", created);
        assertNotNull(created.getId());
        assertEquals("Ana", created.getFirstName());
        assertEquals("Torres", created.getLastName());
        assertEquals("Lima", created.getCity());
    }

    @Test
    public void testFindOwnerById() {
        Owner owner = ownerService.findById(1);

        assertNotNull(owner);
        assertEquals("George", owner.getFirstName());
        assertEquals("Franklin", owner.getLastName());
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = ownerService.create(new Owner(null, "Luis", "Paredes", "Av. siempre viva 22", "Arequipa", "5557654321"));
        owner.setCity("Cusco");
        owner.setTelephone("7778889999");

        Owner updated = ownerService.update(owner);

        assertEquals("Cusco", updated.getCity());
        assertEquals("7778889999", updated.getTelephone());
    }

    @Test
    public void testDeleteOwner() {
        Owner owner = ownerService.create(new Owner(null, "Marta", "Quispe", "Jr. Ayacucho 10", "Trujillo", "5551122334"));

        ownerService.delete(owner.getId());

        assertThrows(OwnerNotFoundException.class, () -> ownerService.findById(owner.getId()));
    }
}
