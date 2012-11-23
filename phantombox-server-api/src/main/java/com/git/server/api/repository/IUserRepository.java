package com.git.server.api.repository;

import com.git.domain.api.IUser;

/**
 * UserRepository interface.
 * <p/>
 * Date: 15.11.12
 * Time: 20:30
 *
 * @author rpleshkov
 */
public interface IUserRepository extends IBaseRepository<String, IUser> {

    /**
     * Check user existence.
     *
     * @param user {@link IUser}
     * @return return 'true' if user exist, 'false' is not
     */
    boolean exists(IUser user);

    /**
     * Finds by name.
     *
     * @param name name
     * @return {@link IUser}
     */
    IUser findByName(String name);

    /**
     * Finds by email.
     *
     * @param email email
     * @return {@link IUser}
     */
    IUser findByEmail(String email);

    /**
     * Finds by name and password.
     *
     * @param name     name
     * @param password password
     * @return {@link IUser}
     */
    IUser findByNameAndPassword(String name, String password);
}
