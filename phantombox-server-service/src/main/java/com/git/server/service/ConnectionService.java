package com.git.server.service;


import com.git.domain.api.IConnection;
import com.git.domain.api.IProperty;
import com.git.domain.impl.Connection;
import com.git.server.api.repository.IBaseRepository;
import com.git.server.api.repository.IConnectionRepository;
import com.git.server.api.service.IConnectionService;
import com.git.server.api.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link IConnectionService} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/15/12
 * Time: 6:37 PM
 */
@Service("connectionService")
public class ConnectionService extends AbstractBaseService<String, IConnection> implements IConnectionService {

    @Autowired
    private IConnectionRepository connectionRepository;

    @Autowired
    private IPropertyService propertyService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IConnection createInstance() {
        return new Connection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IBaseRepository getRepository() {
        return connectionRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IConnection createDefaultConnection() {
        IConnection connection = new Connection();
        IProperty property = propertyService.getProperty();
        connection.setAudioPort(property.getAudioPort());
        connection.setVideoPort(property.getVideoPort());
        return connection;
    }
}
