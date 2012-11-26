package com.git.server.rest.web.service;

import com.git.domain.api.IUser;
import com.git.server.api.service.IUserService;
import com.git.server.rest.api.IRestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link IRestUserService} interface implementation.
 * <p/>
 * Date: 26.11.12
 * Time: 18:17
 *
 * @author rpleshkov
 */
@Service("restUserService")
public class RestUserService implements IRestUserService {

    @Autowired
    private IUserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser login(String name, String password, String ipAddress) {
        return userService.login(name, password, ipAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout(String name, String password) {
        return userService.logout(name, password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByUserName(String name, String password, String userName) {
        IUser user = userService.getByNameAndPassword(name, password);
        if (user != null) {
            userService.addContactByUserName(user, userName);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByContactEmail(String name, String password, String email) {
        IUser user = userService.getByNameAndPassword(name, password);
        if (user != null) {
            userService.addContactByEmail(user, email);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(String name, String password, String contactId) {
        boolean result = false;
        IUser user = userService.getByNameAndPassword(name, password);
        if (user != null) {
            result = userService.removeContactById(user, contactId);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExists(String name, String password) {
        IUser user = userService.getByNameAndPassword(name, password);
        return user != null ? true : false;
    }
}
