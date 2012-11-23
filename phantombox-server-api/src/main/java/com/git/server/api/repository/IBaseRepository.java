package com.git.server.api.repository;

import com.git.domain.api.IDomain;

import java.util.List;

/**
 * Base repository that represent CRUD operations.
 * <p/>
 * Date: 4/9/12
 *
 * @param <ID> The type of unique identifier.
 * @param <T>  The type of objects managed by repository.
 * @author dmgcodevil
 */
public interface IBaseRepository<ID, T extends IDomain> {

    /**
     * Inserts object.
     *
     * @param object Object to be insert.
     * @return generated id - ID
     */
    ID insert(T object);

    /**
     * Returns object by id.
     *
     * @param id Id of object
     * @return The object
     */
    T findById(ID id);

    /**
     * Find all entities in database.
     *
     * @return List
     */
    List<T> findAll();

    /**
     * Updates the object.
     *
     * @param object Object to be update.
     */
    void update(T object);

    /**
     * Deletes the object by id.
     *
     * @param object Object to be delete.
     */
    void delete(T object);

}
