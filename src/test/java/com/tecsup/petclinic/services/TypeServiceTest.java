package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testCreateType() {
        Type type = new Type();
        type.setName("parrot");
        type.setDescription("Pet bird");
        type.setActive(Boolean.TRUE);
        type.setSizeCategory("small");
        type.setAverageLifespan(20);
        type.setCareLevel("medium");

        Type created = typeService.create(type);

        log.info("Created type: {}", created);
        assertNotNull(created.getId());
        assertEquals("parrot", created.getName());
        assertEquals("Pet bird", created.getDescription());
    }

    @Test
    public void testFindTypeById() {
        Type type = typeService.findById(1);

        assertNotNull(type);
        assertEquals("cat", type.getName());
        assertEquals("Domestic feline", type.getDescription());
    }

    @Test
    public void testUpdateType() {
        Type type = typeService.create(new Type(null, "guinea pig", "Small rodent", true, "small", 6, "low"));
        type.setDescription("Domestic cavy");
        type.setCareLevel("medium");

        Type updated = typeService.update(type);

        assertEquals("Domestic cavy", updated.getDescription());
        assertEquals("medium", updated.getCareLevel());
    }

    @Test
    public void testDeleteType() {
        Type type = typeService.create(new Type(null, "ferret", "Small mammal", true, "small", 9, "medium"));

        typeService.delete(type.getId());

        assertThrows(TypeNotFoundException.class, () -> typeService.findById(type.getId()));
    }
}
