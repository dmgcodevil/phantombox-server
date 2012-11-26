package com.git.server.web.ui.vaadin.panel;


import com.git.server.web.ui.vaadin.components.table.UserTable;
import com.git.server.web.ui.vaadin.mediator.UserMediator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;

/**
 * User panel.
 * <p/>
 * Date: 22.11.12
 * Time: 15:40
 *
 * @author rpleshkov
 */
public class UserPanel extends Panel {

    private final FormLayout formLayout = new FormLayout();

    private UserTable userTable;

    private UserMediator userMediator;

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
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
    public UserPanel(UserMediator userMediator) {
        this.userMediator = userMediator;
        init();
        userTable = new UserTable(userMediator);
        formLayout.addComponent(userTable);
    }

    private void init() {
        formLayout.setMargin(true);
        setContent(formLayout);
    }
}
