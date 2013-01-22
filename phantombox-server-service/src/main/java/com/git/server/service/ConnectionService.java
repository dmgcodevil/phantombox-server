package com.git.server.service;


import com.git.domain.api.IConnection;
import com.git.domain.api.IProperty;
import com.git.domain.impl.Connection;
import com.git.domain.util.net.IPAddress;
import com.git.server.api.repository.IBaseRepository;
import com.git.server.api.repository.IConnectionRepository;
import com.git.server.api.service.IConnectionService;
import com.git.server.api.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private static final String GATEWAY = "224.0.0.1";

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
    @Deprecated
    @Override
    public IConnection createDefaultConnection() {
        IConnection connection = new Connection();
        IProperty property = propertyService.getProperty();
        connection.setAudioPort(property.getAudioPort());
        connection.setVideoPort(property.getVideoPort());
        return connection;
    }

    // TODO create check on max(free) ip. throw exception if free addresses not exist.
    @Override
    public IConnection createConnection() {
        IConnection newConnection = null;
        Set<String> ips = new HashSet();
        List<IConnection> connections = getAll();

        for (IConnection connection : connections) {
            ips.add(connection.getIpAddress());
        }
        IPAddress ipAddress = new IPAddress(GATEWAY);

        do {
            ipAddress = ipAddress.next();
        } while (ips.contains(ipAddress.toString()));

        newConnection = new Connection();
        newConnection.setIpAddress(ipAddress.toString());
        newConnection.setConnected(new Date());
        save(newConnection);

        return newConnection;
    }
}
