package com.git.server.storage;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import com.git.domain.api.IContact;
import com.git.domain.impl.Contact;
import com.git.server.api.repository.IContactRepository;
import com.git.server.storage.mongo.AbstractBaseRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * {@link IContactRepository} interface implementation.
 * <p/>
 * Date: 15.11.12
 * Time: 19:18
 *
 * @author rpleshkov
 */
@Repository("contactRepository")
public class ContactRepository extends AbstractBaseRepository<String, IContact>
    implements IContactRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends IContact> getDomainClass() {
        return Contact.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Set<IContact> contacts) {
        for (IContact contact : contacts) {
            insert(contact);
        }
    }

    @Override
    public IContact findByName(String name) {
        return getMongoTemplate().findOne(query(where("name")
            .is(name)), getDomainClass());
    }

    @Override
    public IContact findByEmail(String email) {
        return getMongoTemplate().findOne(query(where("email")
            .is(email)), getDomainClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(IContact contact) {
        IContact repContact = getMongoTemplate().findOne(query(where("email")
            .is(contact.getEmail())), getDomainClass());
        return repContact != null ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MongoTemplate getMongoTemplate() {
        return super.getMongoTemplate();
    }
}
