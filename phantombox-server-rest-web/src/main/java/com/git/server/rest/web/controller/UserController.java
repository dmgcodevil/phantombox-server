package com.git.server.rest.web.controller;

import com.git.domain.api.IUser;
import com.git.server.rest.api.IRestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
}
