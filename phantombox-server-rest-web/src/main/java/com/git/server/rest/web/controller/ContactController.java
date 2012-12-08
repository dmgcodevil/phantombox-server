package com.git.server.rest.web.controller;

import com.git.domain.api.IContact;
import com.git.server.rest.api.IRestContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * Contact controller.
 * <p/>
 * User: dmgcodevil
 * Date: 12/8/12
 * Time: 10:04 AM
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private IRestContactService restContactService;

    @Autowired
    private View jsonView;

    private static final String CONTACT_VIEW = "contact";

    /**
     * Find contact by name.
     *
     * @param name contact name
     * @return {@link ModelAndView} contact in json format
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findContactByName")
    public ModelAndView findContactByName(@RequestParam String name) {
        IContact contact = restContactService.findContactByName(name);
        return new ModelAndView(jsonView, CONTACT_VIEW, contact);
    }

    /**
     * Find contact by email.
     *
     * @param email email
     * @return {@link ModelAndView} contact in json format
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findContactByEmail")
    public ModelAndView findContactByEmail(@RequestParam String email) {
        IContact contact = restContactService.findContactByEmail(email);
        return new ModelAndView(jsonView, CONTACT_VIEW, contact);
    }
}
