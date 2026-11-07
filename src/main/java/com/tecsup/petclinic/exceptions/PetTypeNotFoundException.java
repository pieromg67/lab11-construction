package com.tecsup.petclinic.exceptions;

public class PetTypeNotFoundException extends RuntimeException {

    public PetTypeNotFoundException(String message) {
        super(message);
    }
}
