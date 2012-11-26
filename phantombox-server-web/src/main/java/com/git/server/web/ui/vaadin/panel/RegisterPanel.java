package com.git.server.web.ui.vaadin.panel;


import com.git.server.web.ui.vaadin.components.form.UserRegistrationForm;
import com.git.server.web.ui.vaadin.mediator.UserMediator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;

/**
 * Register panel.
 * <p/>
 * Date: 22.11.12
 * Time: 15:40
 *
 * @author rpleshkov
 */
public class RegisterPanel extends Panel {

    private final FormLayout formLayout = new FormLayout();

    private UserRegistrationForm registrationForm;

    private UserMediator userMediator;

    public UserRegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    public void setRegistrationForm(UserRegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    public UserMediator getUserMediator() {
        return userMediator;
    }

    public void setUserMediator(UserMediator userMediator) {
        this.userMediator = userMediator;
    }

    /**
     * Default constructor.
     */
    public RegisterPanel(UserMediator userMediator) {
        init();
        this.userMediator = userMediator;
        registrationForm = new UserRegistrationForm(userMediator);
        formLayout.addComponent(registrationForm);

    }

    private void init() {
        formLayout.setMargin(true);
        setContent(formLayout);
    }
}
