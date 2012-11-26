package com.git.server.storage;

import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import com.git.domain.api.IUser;
import com.git.domain.util.helper.UserDomainBuilder;
import com.git.server.api.repository.IConnectionRepository;
import com.git.server.api.repository.IContactRepository;
import com.git.server.api.repository.IUserRepository;
import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * User repository integration test.
 * <p/>
 * Date: 15.11.12
 * Time: 20:18
 *
 * @author rpleshkov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-context.xml",
    "classpath:spring/test-mongo-config.xml"})
public class UserRepositoryIntegrationTest {

    private IUser user;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IConnectionRepository connectionRepository;

    @Autowired
    private IContactRepository contactRepository;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        user = UserDomainBuilder.buildUser();
        IContact contact = UserDomainBuilder.buildContact();
        contactRepository.insert(contact);
        user.getContacts().add(contact);
        userRepository.insert(user);
    }

    /**
     * Test insert.
     */
    @Test
    public void testInsert() {
        Assert.assertNotNull(user.getId());
    }

    /**
     * Test findById.
     */
    @Test
    public void testFindById() {
        Assert.assertNotNull(user.getId());
        IUser repUser = userRepository.findById(user.getId());
        Assert.assertNotNull(repUser);
        Assert.assertEquals(user, repUser);
    }

    /**
     * Test update.
     */
    @Test
    public void testUpdate() {
        IUser newUser = UserDomainBuilder.buildUser();
        userRepository.insert(newUser);
        IUser repUser = userRepository.findById(newUser.getId());
        repUser.setName("new name");
        repUser.getContact().setName("new contact name");
        repUser.getContact().getConnection().setIpAddress("223.222.221.12");
        userRepository.update(repUser);
        Assert.assertFalse(newUser.equals(repUser));
    }

    /**
     * Test delete.
     */
    @Test
    public void testDelete() {
        IUser newUser = UserDomainBuilder.buildUser();
        userRepository.insert(newUser);
        IUser repUser = userRepository.findById(newUser.getId());
        Assert.assertNotNull(repUser);
        Assert.assertNotNull(repUser.getId());
        deleteUser(repUser);
        repUser = userRepository.findById(newUser.getId());
        Assert.assertNull(repUser);
    }

    /**
     * Test find by email.
     */
    @Test
    public void testFindByEmail() {
        IUser repUser = userRepository.findByEmail(user.getContact().getEmail());
        Assert.assertNotNull(repUser);
        Assert.assertEquals(user, repUser);
    }

    /**
     * After each.
     */
    @After
    public void afterEach() {
        deleteUser(user);
    }

    private void deleteUser(IUser deluser) {
        if (deluser != null) {
            IContact contact = deluser.getContact();
            Set<IContact> contacts = deluser.getContacts();
            if (CollectionUtils.isNotEmpty(contacts)) {
                if (!contacts.remove(contact)) {
                    deleteContact(contact);
                }
                deleteContact(contacts);
            }

            userRepository.delete(deluser);
        }
    }


    private void deleteContact(Set<IContact> contacts) {
        if (CollectionUtils.isNotEmpty(contacts)) {
            for (IContact contact : contacts) {
                deleteContact(contact);
            }
        }
    }

    private void deleteContact(IContact contact) {
        if (contact != null) {
            IConnection connection = contact.getConnection();
            if (connection != null) {
                connectionRepository.delete(connection);
            }
            contactRepository.delete(contact);
        }
    }
}
