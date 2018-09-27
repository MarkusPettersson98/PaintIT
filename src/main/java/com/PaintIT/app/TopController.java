package com.PaintIT.app;


import Util.ViewFactory;


import javafx.scene.layout.Pane;
import javafx.util.Pair;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class TopController {

    private static HashMap<String, Pane> applicationPanes = new HashMap<>();

    @Getter private Pane currentView = new Pane();

    public TopController(List<Pane> panes) {
        for(Pane pane : panes) {
            System.out.println(pane.getClass().getSimpleName());
            applicationPanes.put(pane.getClass().getSimpleName(), pane);
        }
    }

    public void show(String url) {
        currentView.getChildren().clear();
        currentView.getChildren().add(applicationPanes.get(url));
    }


}
