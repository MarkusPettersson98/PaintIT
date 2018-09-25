package com.PaintIT.app;

import Util.ViewFactory;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import lombok.Getter;

import java.util.HashMap;

public class TopController {

    private static HashMap<String, Pane> applicationPanes = new HashMap<>();

    @Getter private static Pane currentView = new Pane();

    private static TopController instance;

    private TopController() {
        for(Pair<String, Pane> viewPair : ViewFactory.createAllViews()) {
            applicationPanes.put(viewPair.getKey(), viewPair.getValue());
        }
    }

    public static void show(String url) {
        currentView.getChildren().clear();
        currentView.getChildren().add(applicationPanes.get(url));
    }

    public static TopController getInstance() {
        if(instance == null) instance = new TopController();
        return instance;
    }


}
