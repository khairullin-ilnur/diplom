package ru.darkvader.services;


import ru.darkvader.exception.NotFoundException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Khairullin on 18.08.2014.
 * Common service interface with CRUD.
 * Declares methods used to obtain and modify table.
 *
 * @author Khairullin
 */

public interface GenericCRUD<T extends Serializable> {

    /**
     * Creates a new object.
     *
     * @param object The created object.
     * @return The created object.
     */
    public T create(T object);

    /**
     * Deletes a object.
     *
     * @param objectId The id of the deleted object.
     * @return The deleted object.
     * @throws javassist.NotFoundException if no object is found with the given id.
     */
    public T delete(int objectId) throws NotFoundException;

    /**
     * Finds all objects.
     *
     * @return A list of objects.
     */
    public List<T> findAll();

    /**
     * Finds object by id.
     *
     * @param objectId The id of the wanted object.
     * @return The found object. If no event is found, this method returns null.
     */
    public T findById(int objectId) throws NotFoundException;

    /**
     * Updates the information of a object.
     *
     * @param object The updated object.
     * @return The updated object.
     * @throws NotFoundException if no object is found with given id.
     */
    public T update(T object) throws NotFoundException;

}
