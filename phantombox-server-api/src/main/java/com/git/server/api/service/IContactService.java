package com.git.server.api.service;

import com.git.domain.api.IContact;


/**
 * ContactService interface.
 * <p/>
 * Date: 15.11.12
 * Time: 19:20
 *
 * @author rpleshkov
 */
public interface IContactService extends IBaseService<String, IContact> {

    /**
     * Gets by name.
     *
     * @param name name
     * @return @return {@link IContact}
     */
    IContact getByName(String name);

    /**
     * Gets by email.
     *
     * @param email email
     * @return {@link IContact}
     */
    IContact getByEmail(String email);

    /**
     * Exists.
     *
     * @param contact {@link IContact}
     * @return true if exist, false if not
     */
    boolean exists(IContact contact);
}
