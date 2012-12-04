package com.git.server.rest.web.controller;

import static com.git.domain.api.Constants.RESULT;
import com.git.domain.api.IUser;
import com.git.server.rest.api.IRestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

/**
 * User controller.
 * <p/>
 * Date: 26.11.12
 * Time: 18:30
 *
 * @author rpleshkov
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IRestUserService restUserService;

    @Autowired
    private View jsonView;

    private static final String USER_VIEW = "user";

    /**
     * Login.
     *
     * @param name      user name
     * @param password  user password
     * @param ipAddress ip address
     * @return {@link ModelAndView} user in json format
     */
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(@RequestParam String name, @RequestParam String password,
                              @RequestParam(required = false) String ipAddress) {
        IUser user = restUserService.login(name, password, ipAddress);
        return new ModelAndView(jsonView, USER_VIEW, user);
    }

    /**
     * Logout.
     *
     * @param name     user name
     * @param password user password
     * @return map
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public Map<String, Boolean> logout(@RequestParam String name, @RequestParam String password) {
        boolean res = restUserService.logout(name, password);
        Map<String, Boolean> response = new HashMap();
        response.put(RESULT, res);
        return response;
    }


    /**
     * Add contact by user name.
     *
     * @param name     name
     * @param password password
     * @param userName name  of user which need to add
     * @return {@link ModelAndView} user in json format
     */
    @RequestMapping(method = RequestMethod.GET, value = "/addContactByName")
    public ModelAndView addContactByUserName(@RequestParam String name,
                                             @RequestParam String password,
                                             @RequestParam String userName) {
        IUser user = restUserService.addContactByUserName(name, password, userName);
        return new ModelAndView(jsonView, USER_VIEW, user);
    }


    /**
     * Add contact by user name.
     *
     * @param name     name
     * @param password password
     * @param email    Email
     * @return {@link ModelAndView} user in json format
     */
    @RequestMapping(method = RequestMethod.GET, value = "/addContactByEmail")
    public ModelAndView addContactByContactEmail(@RequestParam String name,
                                                 @RequestParam String password,
                                                 @RequestParam String email) {
        IUser user = restUserService.addContactByContactEmail(name, password, email);
        return new ModelAndView(jsonView, USER_VIEW, user);
    }

    /**
     * Remove contact by id.
     *
     * @param name      name
     * @param password  password
     * @param contactId contact id
     * @return map
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/removeContact")
    public Map<String, Boolean> removeContactById(@RequestParam String name,
                                                  @RequestParam String password,
                                                  @RequestParam String contactId) {
        boolean res = restUserService.removeContactById(name, password, contactId);
        Map<String, Boolean> response = new HashMap();
        response.put(RESULT, res);
        return response;
    }

}
