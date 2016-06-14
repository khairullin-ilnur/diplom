package ru.darkvader.exception;


public class NotFoundException extends Exception {

    private static final String MESSAGE_TEMPLATE = "Not found %s with id: %d";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int objectId, Class objectClass) {
        super(String.format(MESSAGE_TEMPLATE, objectClass.getSimpleName().toLowerCase(), objectId));
    }

}
