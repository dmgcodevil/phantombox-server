package com.git.server.storage;

import com.git.domain.api.IConnection;
import com.git.domain.impl.Connection;
import com.git.server.api.repository.IConnectionRepository;
import com.git.server.storage.mongo.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link IConnectionRepository} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/15/12
 * Time: 6:30 PM
 */
@Repository("connectionRepository")
public class ConnectionRepository extends AbstractBaseRepository<String, IConnection>
    implements IConnectionRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends IConnection> getDomainClass() {
        return Connection.class;
    }

}
