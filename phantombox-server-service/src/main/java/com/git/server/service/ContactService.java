package com.git.server.service;


import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import com.git.domain.impl.Contact;
import com.git.server.api.repository.IContactRepository;
import com.git.server.api.service.IConnectionService;
import com.git.server.api.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link IContactService} interface implementation.
 * <p/>
 * Date: 15.11.12
 * Time: 19:23
 *
 * @author rpleshkov
 */
@Service("contactService")
public class ContactService extends AbstractBaseService<String, IContact> implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    private IConnectionService connectionService;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        boolean deleted = false;
        if (id != null) {
            IContact contact = contactRepository.findById(id);
            if (contact != null) {
                IConnection connection = contact.getConnection();
                if (connection != null && connection.getId() != null) {
                    connectionService.delete(connection.getId());
                }
                contactRepository.delete(contact);
                deleted = true;
            }
        }
        return deleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact createInstance() {
        return new Contact();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IContactRepository getRepository() {
        return contactRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IContact getByEmail(String email) {
        IContact contact = null;
        if (email != null) {
            contact = contactRepository.findByEmail(email);
        }
        return contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(IContact contact) {
        return contactRepository.exists(contact);
    }
}
