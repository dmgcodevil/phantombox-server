package com.git.server.web.ui.vaadin.modal;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import javax.annotation.PostConstruct;

/**
 * Register window.
 * <p/>
 * Date: 22.11.12
 * Time: 14:29
 *
 * @author rpleshkov
 */
public class RegisterWindow extends Window {

    @PostConstruct
    public void init() {
        addComponent(new Label("Register"));
    }
}
