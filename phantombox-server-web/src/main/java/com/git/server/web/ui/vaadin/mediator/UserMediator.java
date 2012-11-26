package com.git.server.web.ui.vaadin.mediator;


import com.git.domain.api.IUser;
import com.git.server.api.service.IUserService;
import com.git.server.web.ui.vaadin.panel.RegisterPanel;
import com.git.server.web.ui.vaadin.panel.UserPanel;

/**
 * User mediator.
 * <p/>
 * Date: 22.11.12
 * Time: 18:18
 *
 * @author rpleshkov
 */
public class UserMediator {

    private IUserService userService;

    private RegisterPanel registerPanel;

    private UserPanel userPanel;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public RegisterPanel getRegisterPanel() {
        return registerPanel;
    }

    public void setRegisterPanel(RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    public void setUserPanel(UserPanel userPanel) {
        this.userPanel = userPanel;
    }

    public void saveUser(IUser user) {
        userService.save(user);
        //userPanel.getUserTable().refreshTable();
    }

    public void deleteUser(String userId) {
        userService.delete(userId);
        userPanel.getUserTable().refreshTable();
    }
}
