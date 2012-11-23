package com.git.server.api.service;

import com.git.domain.api.IProperty;


/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/18/12
 * Time: 10:47 AM
 */
public interface IPropertyService extends IBaseService<String, IProperty> {

    /**
     * Get application property.
     *
     * @return {@link IProperty}
     */
    IProperty getProperty();
}
