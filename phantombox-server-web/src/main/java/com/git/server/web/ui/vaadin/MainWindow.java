package com.git.server.web.ui.vaadin;


import com.git.server.api.service.IUserService;
import com.git.server.web.common.UIConstants;
import com.git.server.web.ui.vaadin.mediator.UserMediator;
import com.git.server.web.ui.vaadin.panel.RegisterPanel;
import com.git.server.web.ui.vaadin.panel.UserPanel;
import com.vaadin.Application;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;


/**
 * Main window.
 * <p/>
 * Date: Jul 24, 2012
 *
 * @author Raman Pliashkou
 */
@Configurable(preConstruction = true)
public class MainWindow extends Application {

    private final MenuBar menubar = new MenuBar();

    private final Window main = new Window(UIConstants.MAIN_TITLE);

    private UserPanel userPanel;
    private RegisterPanel registerPanel;

    private UserMediator userMediator;

    @Autowired
    private IUserService userService;

    @Override
    public void init() {
        setTheme(UIConstants.UI_THEME);
        userMediator = new UserMediator();
        userMediator.setUserService(userService);
        userPanel = new UserPanel(userMediator);
        registerPanel = new RegisterPanel(userMediator);
        userMediator.setRegisterPanel(registerPanel);
        userMediator.setUserPanel(userPanel);
        userPanel.setVisible(false);
        registerPanel.setVisible(false);

        main.addComponent(menubar);
        main.addComponent(userPanel);
        main.addComponent(registerPanel);
        configureMenuBar();
        setMainWindow(main);
    }

    private void configureMenuBar() {

        MenuBar.MenuItem adminItem = menubar.addItem(UIConstants.ADMIN, null, null);

        menubar.addItem(UIConstants.REGISTRATION, new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                userPanel.setVisible(false);
                registerPanel.setVisible(true);

            }
        });


        MenuBar.Command userTableCommand = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                userPanel.setVisible(true);
                userPanel.getUserTable().refreshTable();
                registerPanel.setVisible(false);
            }
        };
        adminItem.addItem(UIConstants.USERS, userTableCommand);
    }

}
