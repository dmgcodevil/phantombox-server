package com.git.server.service;

import com.git.domain.api.IUser;
import com.git.domain.util.helper.UserDomainBuilder;
import com.git.server.api.service.IUserService;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User service integration test.
 * <p/>
 * User: dmgcodevil
 * Date: 11/24/12
 * Time: 2:05 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-context.xml",
    "classpath:spring/test-mongo-config.xml"})
public class UserServiceIntegrationTest {

    @Autowired
    private IUserService userService;

    private IUser user;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        user = UserDomainBuilder.buildUser();
        userService.save(user);
    }

    /**
     * Test save.
     */
    @Test
    public void testSave() {
        Assert.assertNotNull(user.getId());
    }

    /**
     * Test GetById.
     */
    @Test
    public void testGetById() {
        IUser repUser = userService.getById(user.getId());
        Assert.assertNotNull(repUser);
        Assert.assertTrue(user.equals(repUser));
    }

    /**
     * Test update.
     */
    @Test
    public void testUpdate() {
        IUser newUser = UserDomainBuilder.buildUser();
        userService.save(newUser);
        IUser repUser = userService.getById(newUser.getId());
        repUser.setName("new name");
        repUser.getContact().setName("new contact name");
        repUser.getContact().getConnection().setIpAddress("223.222.221.12");
        userService.update(repUser);
        Assert.assertFalse(newUser.equals(repUser));
    }

    /**
     * Test delete.
     */
    @Test
    public void testDelete() {
        IUser newUser = UserDomainBuilder.buildUser();
        userService.save(newUser);
        IUser repUser = userService.getById(newUser.getId());
        Assert.assertNotNull(repUser);
        Assert.assertNotNull(repUser.getId());
        userService.delete(repUser.getId());
        repUser = userService.getById(newUser.getId());
        Assert.assertNull(repUser.getId());
    }

    /**
     * After each.
     */
    @After
    public void afterEach() {
        userService.delete(user.getId());
    }

}
