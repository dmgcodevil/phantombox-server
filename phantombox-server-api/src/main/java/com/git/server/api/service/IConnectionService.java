package com.git.server.api.service;

import com.git.domain.api.IConnection;

/**
 * ConnectionService interface.
 * <p/>
 * <p/>
 * User: dmgcodevil
 * Date: 11/15/12
 * Time: 6:33 PM
 */
public interface IConnectionService extends IBaseService<String, IConnection> {

    /**
     * Create connection with default parameters.
     *
     * @return {@link IConnection}
     */
    IConnection createDefaultConnection();

    /**
     * Create connection.
     *
     * @return {@link IConnection}
     */
    IConnection createConnection();
}
