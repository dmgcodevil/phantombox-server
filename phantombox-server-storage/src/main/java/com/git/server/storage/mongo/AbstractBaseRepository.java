package com.git.server.storage.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.git.domain.api.IDomain;
import com.git.server.api.repository.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Abstract base repository.
 * <p/>
 * Date: Jun 14, 2012
 *
 * @param <ID> The type of unique identifier.
 * @param <T>  The type of objects managed by repository.
 * @author Raman Pliashkou
 */
public abstract class AbstractBaseRepository<ID, T extends IDomain>
    implements IBaseRepository<ID, T> {

    /**
     * FIRST ELEMENT.
     */
    public static final int FIRST_ELEMENT = 0;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Gets mongo template object.
     *
     * @return mongo template.
     */
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * Sets mongo template object.
     *
     * @param mongoTemplate {@link MongoTemplate}
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ID insert(T document) {
        document.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(document);
        return (ID) document.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T document) {
        mongoTemplate.remove(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(ID id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), getDomainClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T document) {
        mongoTemplate.save(document);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return (List<T>) getMongoTemplate().findAll(getDomainClass());
    }

    /**
     * Gets domain object class.
     *
     * @return domain object class
     */
    protected abstract Class<? extends T> getDomainClass();
}
