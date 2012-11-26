package com.git.server.service;


import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
import com.git.domain.impl.Contact;
import com.git.domain.impl.User;
import com.git.server.api.repository.IBaseRepository;
import com.git.server.api.repository.IUserRepository;
import com.git.server.api.service.IConnectionService;
import com.git.server.api.service.IContactService;
import com.git.server.api.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@link IUserService} interface implementation.
 * <p/>
 * Date: 15.11.12
 * Time: 20:35
 *
 * @author rpleshkov
 */
@Service("userService")
public class UserService extends AbstractBaseService<String, IUser> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IContactService contactService;

    @Autowired
    private IConnectionService connectionService;

    /**
     * Saves user.
     *
     * @param user {@link IUser}
     * @return result of operation
     */
    public boolean save(IUser user) {
        boolean saved = false;
        if (user != null && user.getContact() != null) {
            user.getContact().setConnection(connectionService.createDefaultConnection());
            saved = super.save(user);
        }
        return saved;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getByNameAndPassword(String name, String password) {
        IUser user = null;
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)) {
            user = userRepository.findByNameAndPassword(name, password);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser login(String name, String password, String ipAddress) {
        IUser user = userRepository.findByNameAndPassword(name, password);
        if (user != null) {
            if (user.getContact() != null && user.getContact().getId() != null) {
                user.getContact().setOnline(true);
                IConnection connection = user.getContact().getConnection();
                if (connection == null) {
                    connection = connectionService.createDefaultConnection();
                }
                connection.setIpAddress(ipAddress);
                connection.setConnected(new Date());
                user.getContact().setConnection(connection);
                contactService.update(user.getContact());
            }
        } else {
            user = createInstance();
            user.getContact().setOnline(false);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout(String name, String password) {
        boolean disconnected = false;
        IUser user = getByNameAndPassword(name, password);
        if (user != null) {
            if (user.getContact() != null) {
                user.getContact().setOnline(false);
                contactService.update(user.getContact());
                disconnected = true;
            }
        }
        return disconnected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addContactByUserName(String userId, String name) {
        IUser user = getById(userId);
        return addContactByUserName(user, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addContactByUserName(IUser user, String name) {
        boolean added = false;
        IUser newUser = userRepository.findByName(name);
        if (user != null && newUser != null) {
            user.getContacts().add(newUser.getContact());
            update(user);
            added = true;

        }
        return added;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addContactByEmail(String userId, String email) {
        IUser user = getById(userId);
        return addContactByEmail(user, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addContactByEmail(IUser user, String email) {
        boolean added = false;
        IContact contact = contactService.getByEmail(email);
        if (user != null && contact != null) {
            user.getContacts().add(contact);
            update(user);
            added = true;
        }
        return added;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(String userId, String contactId) {
        IUser user = getById(userId);
        return removeContactById(user, contactId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(IUser user, String contactId) {
        boolean result = false;
        if (user != null && contactId != null) {
            IContact contact = contactService.getById(contactId);
            if (contact.getId() != null) {
                user.getContacts().remove(contact);
                update(user);
                result = true;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        boolean deleted = false;
        IUser user = userRepository.findById(id);
        if (user != null) {
            IContact contact = user.getContact();
            if (contact != null && contact.getId() != null) {
                contactService.delete(contact.getId());
            }
            userRepository.delete(user);
            deleted = true;
        }
        return deleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser createInstance() {
        IUser user = new User();
        user.setContact(new Contact());
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IBaseRepository getRepository() {
        return userRepository;
    }

}
