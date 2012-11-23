package com.git.server.api.service;


import com.git.domain.api.IUser;

/**
 * UserService interface.
 * <p/>
 * Date: 15.11.12
 * Time: 20:33
 *
 * @author rpleshkov
 */
public interface IUserService extends IBaseService<String, IUser> {


    /**
     * Login user in the system.
     *
     * @param name      name
     * @param password  password
     * @param ipAddress ip address
     * @return {@link IUser}
     */
    IUser login(String name, String password, String ipAddress);

    /**
     * logout user.
     *
     * @param name     name
     * @param password password
     */
    void logout(String name, String password);


    /**
     * Add contact by name.
     *
     * @param userId user id
     * @param name   name
     * @return result
     */
    boolean addContactByName(String userId, String name);

    /**
     * Add contact by email.
     *
     * @param userId user id
     * @param email  email
     * @return result
     */
    boolean addContactByEmail(String userId, String email);
}
