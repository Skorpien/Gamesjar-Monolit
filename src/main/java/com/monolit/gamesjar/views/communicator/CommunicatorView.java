package com.monolit.gamesjar.views.communicator;

import com.monolit.gamesjar.backend.communicator.CommunicatorGui;
import com.monolit.gamesjar.backend.communicator.domain.History;
import com.monolit.gamesjar.backend.communicator.service.DialogService;
import com.monolit.gamesjar.backend.communicator.service.HistoryService;
import com.monolit.gamesjar.backend.observer.Observer;
import com.monolit.gamesjar.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "communicator", layout = MainView.class)
@RouteAlias(value = "Communicator", layout = MainView.class)
@PageTitle("Communicator")
public class CommunicatorView extends Div implements AfterNavigationObserver, Observer {

    private CommunicatorGui communicatorGui;
    private HistoryService historyService;

    private TextField textField = new TextField();
    private TextField name = new TextField("Enter your name");
    private TextArea messages = new TextArea();
    private Button send = new Button("Send");
    private Button clear = new Button("Clear History");

    @Autowired
    public CommunicatorView(CommunicatorGui communicatorGui, HistoryService historyService) throws Exception {
        this.communicatorGui = communicatorGui;
        this.historyService = historyService;
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(messages,name, textField, send, clear);
        add(horizontalLayout);
        addObserver();
        send.addClickListener(event ->
        {
            try {
                sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        clear.addClickListener(event ->
        {
            try {
                clearHistory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        UI ui = UI.getCurrent();
        ui.setPollInterval(1000);
        ui.addPollListener(x -> {
            try {
                messages.setValue(historyService.getHistory(2L).getHistory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        messages.setValueChangeTimeout(2000);
    }


    public void sendMessage() throws Exception {
        communicatorGui.sentMessage(2L, name.getValue(), textField.getValue());
        messages.setValue(historyService.getHistory(2L).getHistory());
    }


    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        try {
            messages.setValue(historyService.getHistory(2L).getHistory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addObserver() throws Exception {
        historyService.getHistory(2L).registerObserver(this);
    }

    @Override
    public void update(History history) {
        messages.setValue(history.getHistory());
    }

    public void clearHistory() throws Exception {
        historyService.getHistory(2L).setHistory("");
    }
}
