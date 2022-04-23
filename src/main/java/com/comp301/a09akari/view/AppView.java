package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AppView implements FXComponent{

    private ClassicMvcController controller;

    public AppView(ClassicMvcController controller){
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox appview = new VBox();
        appview.setAlignment(Pos.CENTER);
        appview.setSpacing(10);

        //title and size
        TitleBox titlebox = new TitleBox(controller);
        appview.getChildren().add(titlebox.render());


        //game grid container
        GridContainer container = new GridContainer(controller);
        appview.getChildren().add(container.render());

        //controls
        ControlBox controlbox = new ControlBox(controller);
        appview.getChildren().add(controlbox.render());
        return appview;
    }
}
