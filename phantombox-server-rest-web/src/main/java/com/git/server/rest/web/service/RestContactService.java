package com.git.server.rest.web.service;

import com.git.domain.api.IContact;
import com.git.server.api.service.IContactService;
import com.git.server.rest.api.IRestContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/8/12
 * Time: 10:02 AM
 */
@Service("restContactService")
public class RestContactService implements IRestContactService {

    @Autowired
    private IContactService contactService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact findContactByName(String name) {
        return contactService.getByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact findContactByEmail(String email) {
        return contactService.getByEmail(email);
    }
}
