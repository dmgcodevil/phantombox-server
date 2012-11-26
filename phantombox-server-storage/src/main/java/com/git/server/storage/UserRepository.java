package com.git.server.storage;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.git.domain.api.IUser;
import com.git.domain.impl.User;
import com.git.server.api.repository.IUserRepository;
import com.git.server.storage.mongo.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link IUserRepository} interface implementation.
 * <p/>
 * Date: 15.11.12
 * Time: 20:31
 *
 * @author rpleshkov
 */
@Repository("userRepository")
public class UserRepository extends AbstractBaseRepository<String, IUser>
    implements IUserRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends IUser> getDomainClass() {
        return User.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(IUser user) {
        return findByNameAndPassword(user.getName(), user.getPassword()) != null ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser findByName(String name) {
        return getMongoTemplate().findOne(query(where("name").is(name)), getDomainClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public IUser findByEmail(String email) {
        return getMongoTemplate().findOne(query(where("user.contact.email")
            .is(email)), getDomainClass());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IUser findByNameAndPassword(String name, String password) {
        return getMongoTemplate().findOne(query(where("name").is(name)
            .and("password").is(password)), getDomainClass());
    }
}
