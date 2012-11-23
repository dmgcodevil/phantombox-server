package com.git.server.api.repository;

import com.git.domain.api.IProperty;

/**
 * PropertyRepository interface.
 * <p/>
 * User: dmgcodevil
 * Date: 11/18/12
 * Time: 10:10 AM
 */
public interface IPropertyRepository extends IBaseRepository<String, IProperty> {

    /**
     * Getes property from storage.
     *
     * @return {@link IProperty}
     */
    IProperty getProperty();
}
