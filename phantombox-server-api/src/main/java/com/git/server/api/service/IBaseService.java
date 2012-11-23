package com.git.server.api.service;

import com.git.domain.api.IDomain;

import java.util.List;

/**
 * BaseService interface.
 * <p/>
 * User: dmgcodevil
 * Date: 11/15/12
 * Time: 5:46 PM
 *
 * @param <ID> The type of unique identifier.
 * @param <T>  The type of objects managed by service.
 */
public interface IBaseService<ID, T extends IDomain> {

    /**
     * Gets object by id.
     *
     * @param id object id
     * @return object
     */
    T getById(ID id);

    /**
     * Saves object.
     *
     * @param object object for save
     * @return updating result
     */
    boolean save(T object);

    /**
     * Updates object.
     *
     * @param object {@link T}
     * @return updating result
     */
    boolean update(T object);

    /**
     * Saves or updates object.
     *
     * @param object object
     * @return result of operation
     */
    boolean saveOrUpdate(T object);

    /**
     * Deletes object by id.
     *
     * @param id object id
     * @return deleting result
     */
    boolean delete(ID id);

    /**
     * Gets list of object.
     *
     * @return list of object
     */
    List<T> getAll();

    /**
     * Create new object instance.
     *
     * @return {@link T}
     */
    T createInstance();
}
