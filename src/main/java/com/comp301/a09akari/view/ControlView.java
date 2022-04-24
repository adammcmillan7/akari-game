package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent{

    private ClassicMvcController controller;

    public ControlView(ClassicMvcController controller){
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox box = new VBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        ControlBox controlbox = new ControlBox(controller);
        box.getChildren().add(controlbox.render());
        box.setTranslateY(20);

        return box;
    }
}
