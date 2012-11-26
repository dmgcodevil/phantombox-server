package com.git.server.api.repository;

import com.git.domain.api.IContact;

import java.util.Set;

/**
 * ContactRepository interface.
 * <p/>
 * Date: 15.11.12
 * Time: 19:01
 *
 * @author rpleshkov
 */
public interface IContactRepository extends IBaseRepository<String, IContact> {

    /**
     * Inserts list of {@link IContact} objects.
     *
     * @param contacts list of {@link IContact} objects
     */
    void insert(Set<IContact> contacts);

    /**
     * Find by email.
     *
     * @param email email
     * @return {@link IContact}
     */
    IContact findByEmail(String email);

    /**
     * Checks existence contact in storage.
     *
     * @param contact {@link IContact}
     * @return true if contact exist or false is not
     */
    boolean exists(IContact contact);

}
