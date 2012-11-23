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
     * Exists.
     *
     * @param contact {@link IContact}
     * @return true if exist, false if not
     */
    boolean exists(IContact contact);
}
