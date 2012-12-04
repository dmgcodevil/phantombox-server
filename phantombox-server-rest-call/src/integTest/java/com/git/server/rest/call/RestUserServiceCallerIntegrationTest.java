package com.git.server.rest.call;

import com.git.domain.api.IUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/4/12
 * Time: 3:47 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:com/git/server/rest/call/spring/phantombox-server-rest-call-context.xml"})
public class RestUserServiceCallerIntegrationTest {

    @Autowired
    private RestUserServiceCaller restUserServiceCaller;

    // TODO add storage. refactor it
    @Test
    public void test() {
        IUser user = restUserServiceCaller.login("alex", "123death", "192.168.1.1");
        boolean res = restUserServiceCaller.logout("alex1", "123death");
    }
}
