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
     * Gets by name and password.
     *
     * @param name     name
     * @param password password
     * @return {@link IUser}
     */
    IUser getByNameAndPassword(String name, String password);

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
     * Login user in the system.
     *
     * @param name     name
     * @param password password
     * @return {@link IUser}
     */
    IUser login(String name, String password);

    /**
     * logout user.
     *
     * @param name     name
     * @param password password
     * @return true - if user was disconnect
     */
    boolean logout(String name, String password);


    /**
     * Add contact by name.
     *
     * @param userId user id
     * @param name   name
     * @return result
     */
    boolean addContactByName(String userId, String name);


    /**
     * Add contact by name.
     *
     * @param user {@link IUser} which will be add contact
     * @param name name
     * @return result
     */
    boolean addContactByName(IUser user, String name);

    /**
     * Add contact by email.
     *
     * @param userId user id
     * @param email  email
     * @return result
     */
    boolean addContactByEmail(String userId, String email);

    /**
     * Add contact by email.
     *
     * @param user  {@link IUser}
     * @param email email
     * @return result
     */
    boolean addContactByEmail(IUser user, String email);

    /**
     * Remove contact by id.
     *
     * @param userId    user id
     * @param contactId contact id
     * @return true - if operation complete successful
     */
    boolean removeContactById(String userId, String contactId);

    /**
     * Remove contact by id.
     *
     * @param user      {@link IUser}
     * @param contactId contact id
     * @return true - if operation complete successful
     */
    boolean removeContactById(IUser user, String contactId);
}
