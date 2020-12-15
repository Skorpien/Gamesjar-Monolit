package com.monolit.gamesjar.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.router.*;

import java.util.Collections;

@Route("login")
@RouteAlias("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout {


    public LoginView() {
        LoginOverlay component = new LoginOverlay();
        component.addLoginListener(e -> component.close());
        Button open = new Button("Open login overlay",
                e -> component.setOpened(true));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
        component.setI18n(i18n);
        add(component);

    }

}
