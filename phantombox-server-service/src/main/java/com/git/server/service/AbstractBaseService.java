package com.git.server.service;


import com.git.domain.api.IDomain;
import com.git.server.api.repository.IBaseRepository;
import com.git.server.api.service.IBaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link IBaseService} implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/15/12
 * Time: 5:48 PM
 *
 * @param <ID> The type of unique identifier.
 * @param <T>  The type of objects managed by service.
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class AbstractBaseService<ID, T extends IDomain> implements IBaseService<ID, T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T getById(ID id) {
        T object = null;
        if (id != null) {
            object = (T) getRepository().findById(id);
        }
        return object != null ? object : createInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(T object) {
        boolean saved = false;
        if (object != null) {
            getRepository().insert(object);
            saved = object.getId() != null ? true : false;
        }

        return saved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(T object) {
        boolean updated = false;
        if (object != null && object.getId() != null) {
            getRepository().update(object);
            updated = true;
        }
        return updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean saveOrUpdate(T object) {
        boolean result = false;
        if (object != null) {
            if (object.getId() != null) {
                result = save(object);
            } else {
                result = update(object);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(ID id) {
        boolean deleted = false;
        T object = (T) getRepository().findById(id);
        if (object != null) {
            getRepository().delete(object);
            deleted = true;
        }
        return deleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getAll() {
        List<T> entities = null;
        entities = getRepository().findAll();
        if (entities == null) {
            entities = new ArrayList();
        }
        return entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract T createInstance();

    /**
     * Get repository.
     *
     * @return current {@link IBaseRepository} implementation
     */
    protected abstract IBaseRepository getRepository();
}
