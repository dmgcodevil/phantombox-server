package com.git.server.storage;


import com.git.domain.api.IProperty;
import com.git.domain.impl.Property;
import com.git.server.api.repository.IPropertyRepository;
import com.git.server.storage.mongo.AbstractBaseRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link IPropertyRepository} interface implementations.
 * <p/>
 * User: dmgcodevil
 * Date: 11/18/12
 * Time: 10:10 AM
 */
@Repository("propertyRepository")
public class PropertyRepository extends AbstractBaseRepository<String, IProperty>
    implements IPropertyRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends IProperty> getDomainClass() {
        return Property.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProperty getProperty() {
        IProperty property = null;
        List<IProperty> propertieses = findAll();
        if (CollectionUtils.isNotEmpty(propertieses)) {
            property = propertieses.get(FIRST_ELEMENT);
        }
        return property;
    }
}
