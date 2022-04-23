package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControlButtons implements FXComponent{

    private ClassicMvcController controller;

    public ControlButtons(ClassicMvcController controller){
        this.controller = controller;
    }

    @Override
    public Parent render() {
        HBox controls = new HBox();
        controls.setAlignment(Pos.CENTER);
        controls.setSpacing(10);


        //prev puzzle
        Button previous = new Button();
        ImageView prev = new ImageView(new Image("previous-button.png"));
        prev.setFitWidth(30);
        prev.setFitHeight(30);
        previous.setGraphic(prev);
        previous.setOnAction((ActionEvent event) -> {
            controller.clickPrevPuzzle();
        });
        if (controller.getModel().getActivePuzzleIndex() == 0){
          previous.setDisable(true);
        }

        controls.getChildren().add(previous);

        //vbox for random/dropdown
        PuzzleSelect puzzleSelect = new PuzzleSelect(controller);
        controls.getChildren().add(puzzleSelect.render());

        //nex puzzle
        Button next = new Button();
        ImageView next_img = new ImageView(new Image("next-button.png"));
        next_img.setFitHeight(30);
        next_img.setFitWidth(30);
        next.setGraphic(next_img);
        next.setOnAction((ActionEvent event) -> {
            controller.clickNextPuzzle();
        });
        if (controller.getModel().getActivePuzzleIndex() == controller.getModel().getPuzzleLibrarySize()-1){
          next.setDisable(true);
        }

        controls.getChildren().add(next);

        return controls;
    }
}
