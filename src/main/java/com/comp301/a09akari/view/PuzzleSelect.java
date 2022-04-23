package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class PuzzleSelect implements FXComponent{

    private ClassicMvcController controller;

    public PuzzleSelect(ClassicMvcController controller){
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox selection = new VBox();
        selection.setAlignment(Pos.CENTER);
        selection.setSpacing(10);


        //random puzzle button
        Button random = new Button("random puzzle!");
        random.setOnAction((ActionEvent event) ->{
            controller.clickRandPuzzle();
        });
        selection.getChildren().add(random);

        //dropdown to select puzzles
        MenuItem p1 = new MenuItem("Puzzle 1");
        MenuItem p2 = new MenuItem("Puzzle 2");
        MenuItem p3 = new MenuItem("Puzzle 3");
        MenuItem p4 = new MenuItem("Puzzle 4");
        MenuItem p5 = new MenuItem("Puzzle 5");
        MenuButton puzzles = new MenuButton("Choose a Puzzle!",null, p1, p2, p3, p4, p5);
        puzzles.setAlignment(Pos.CENTER);
        selection.getChildren().add(puzzles);


        p1.setOnAction((ActionEvent event) ->
                controller.selectPuzzle(0));
        p2.setOnAction((ActionEvent event) -> controller.selectPuzzle(1));
        p3.setOnAction((ActionEvent event) -> controller.selectPuzzle(2));
        p4.setOnAction((ActionEvent event) -> controller.selectPuzzle(3));
        p5.setOnAction((ActionEvent event) -> controller.selectPuzzle(4));

        return selection;
    }
}
