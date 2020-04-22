package com.monolit.gamesjar.views.room;

import com.monolit.gamesjar.backend.domain.GameNotFoundException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.monolit.gamesjar.views.main.MainView;
import com.monolit.gamesjar.backend.domain.Game;
import com.monolit.gamesjar.backend.domain.PlayingTime;
import com.monolit.gamesjar.backend.domain.Category;
import com.monolit.gamesjar.backend.service.DbService;

@Route(value = "room", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Room")
@CssImport("styles/views/room/room-view.css")
public class RoomView extends Div implements AfterNavigationObserver {
    @Autowired
    private DbService service;

    private Grid<Game> games;

    private TextField name = new TextField();
    private ComboBox<PlayingTime> time = new ComboBox<>();
    private ComboBox<Category> categories = new ComboBox<>();

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button create = new Button("New Game");

    private Binder<Game> binder;

    public RoomView() {
        setId("room-view");
        // Configure Grid
        games = new Grid<>();
        games.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
        games.setHeightFull();
        games.addColumn(Game::getName).setHeader("name").setSortable(true);
        games.addColumn(Game::getTimeToString).setHeader("time").setSortable(true);
        games.addColumn(Game::getCategories).setHeader("categories").setSortable(true);

        time.setItems(PlayingTime.values());
        categories.setItems(Category.values());
        //when a row is selected or deselected, populate form
        games.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

        // Configure Form
        binder = new Binder<>(Game.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);
        // note that password field isn't bound since that property doesn't exist in
        // Employee

        // the grid valueChangeEvent will clear the form too
        cancel.addClickListener(e -> games.asSingleSelect().clear());

        save.addClickListener(e -> {
            try {
                changeGame(games.asSingleSelect().getValue(), name.getValue(), time.getValue(), categories.getValue());
            } catch (GameNotFoundException gameNotFoundException) {
                gameNotFoundException.printStackTrace();
            }
            refresh();
        });

        delete.addClickListener(e -> {
                deleteGame(games.asSingleSelect().getValue());
                refresh();
        });

        create.addClickListener(e -> {
            createGame(name.getValue(), time.getValue(), categories.getValue());
            refresh();
        });

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorDiv = new Div();
        editorDiv.setId("editor-layout");
        FormLayout formLayout = new FormLayout();
        addFormItem(editorDiv, formLayout, name, "Game Title");
        addFormItem(editorDiv, formLayout, time, "Playing Time");
        addFormItem(editorDiv, formLayout, categories, "Category");
        createButtonLayout(editorDiv);
        splitLayout.addToSecondary(editorDiv);
    }

    private void createButtonLayout(Div editorDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        delete.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        create.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(delete, cancel, save);
        editorDiv.add(buttonLayout, create);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(games);
    }


    private void addFormItem(Div wrapper, FormLayout formLayout,
                             AbstractField field, String fieldName) {
        formLayout.addFormItem(field, fieldName);
        wrapper.add(formLayout);
        field.getElement().getClassList().add("full-width");
    }


    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Lazy init of the grid items, happens only when we are sure the view will be
        // shown to the user
        games.setItems(service.getAllGames());
    }

    public void refresh() {
        games.setItems(service.getAllGames());
    }

    private void populateForm(Game value) {
        // Value can be null as well, that clears the form
        binder.readBean(value);

        // The password field isn't bound through the binder, so handle that
       // password.setValue("");

    }

    public void createGame(String name, PlayingTime time, Category category) {
        Game game = new Game(name, time, category);
        service.saveGame(game);
    }

    public void changeGame(Game game, String name, PlayingTime time, Category category) throws GameNotFoundException {
        Game newGame = new Game(name, time, category);
        service.updateGame(newGame, game.getId());
    }

    public void deleteGame(Game game) {
        service.deleteGame(game.getId());
    }
}
