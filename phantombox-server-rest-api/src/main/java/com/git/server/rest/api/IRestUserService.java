package com.git.server.rest.api;

import com.git.domain.api.IUser;

/**
 * Rest user service.
 * <p/>
 * Date: 26.11.12
 * Time: 17:45
 *
 * @author rpleshkov
 */
public interface IRestUserService {

    /**
     * Login.
     *
     * @param name      user name
     * @param password  user password
     * @param ipAddress ip address
     * @return {@link IUser}
     */
    IUser login(String name, String password, String ipAddress);

    /**
     * Login.
     *
     * @param name     user name
     * @param password user password
     * @return {@link IUser}
     */
    IUser login(String name, String password);

    /**
     * logout.
     *
     * @param name     name
     * @param password password
     * @return true - if operation complete successful
     */
    boolean logout(String name, String password);


    /**
     * Add contact by  name.
     *
     * @param name        name
     * @param password    password
     * @param contactName name  of contact which need to add
     * @return {@link IUser}
     */
    IUser addContactByName(String name, String password, String contactName);


    /**
     * Add contact by email.
     *
     * @param name     name
     * @param password password
     * @param email    Email
     * @return {@link IUser}
     */
    IUser addContactByContactEmail(String name, String password, String email);

    /**
     * Remove contact by id.
     *
     * @param name      name
     * @param password  password
     * @param contactId contact id
     * @return true - if operation complete successful
     */
    boolean removeContactById(String name, String password, String contactId);

    /**
     * Check user exists.
     *
     * @param name     name
     * @param password password
     * @return true if exist, false if not
     */
    boolean checkUserExists(String name, String password);
}
