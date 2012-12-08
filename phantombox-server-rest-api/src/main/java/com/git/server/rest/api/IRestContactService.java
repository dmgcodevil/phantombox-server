package com.git.server.rest.api;

import com.git.domain.api.IContact;

/**
 * Rest contact service interface.
 * <p/>
 * User: dmgcodevil
 * Date: 12/8/12
 * Time: 10:00 AM
 */
public interface IRestContactService {


    /**
     * Find contact by name.
     *
     * @param name contact name
     * @return {@link com.git.domain.api.IContact}
     */
    IContact findContactByName(String name);

    /**
     * Find contact by email.
     *
     * @param email contact email
     * @return {@link IContact}
     */
    IContact findContactByEmail(String email);
}
