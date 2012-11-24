package com.git.server.service;


import com.git.domain.api.IProperty;
import com.git.domain.impl.Property;
import com.git.server.api.repository.IBaseRepository;
import com.git.server.api.repository.IPropertyRepository;
import com.git.server.api.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link IPropertyService} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/18/12
 * Time: 10:48 AM
 */
@Service("propertyService")
public class PropertyService extends AbstractBaseService<String, IProperty> implements IPropertyService {

    @Autowired
    private IPropertyRepository propertiesRepository;

    private static final int AUDIO_PORT = 5555;
    private static final int VIDEO_PORT = 7777;

    /**
     * {@inheritDoc}
     */
    @Override
    public IProperty createInstance() {
        IProperty properties = new Property();
        properties.setAudioPort(AUDIO_PORT);
        properties.setVideoPort(VIDEO_PORT);
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IBaseRepository getRepository() {
        return propertiesRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProperty getProperty() {
        IProperty property = null;
        property = propertiesRepository.getProperty();
        if (property == null) {
            property = createInstance();
        }
        return property;
    }
}
