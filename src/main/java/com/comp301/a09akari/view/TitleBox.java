package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TitleBox implements FXComponent {

  private final ClassicMvcController controller;

  public TitleBox(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox titlebox = new VBox();
    Label puzzletitle = new Label();
    Label size = new Label();
    titlebox.setAlignment(Pos.TOP_CENTER);
    puzzletitle.setAlignment(Pos.TOP_CENTER);
    size.setAlignment(Pos.TOP_CENTER);
    puzzletitle.getStyleClass().add("puzzle_title");
    size.getStyleClass().add("size");

    puzzletitle.setText(controller.getPuzzleName());
    size.setText(controller.getPuzzleSize());

    titlebox.getChildren().add(puzzletitle);
    titlebox.getChildren().add(size);

    return titlebox;
  }
}
