package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlBox implements FXComponent {

  private final ClassicMvcController controller;

  public ControlBox(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox controlbox = new VBox();
    controlbox.setAlignment(Pos.CENTER);
    controlbox.setSpacing(7);

    // reset button
    Button reset = new Button("reset");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    controlbox.getChildren().add(reset);

    String message = "";
    if (controller.getModel().isSolved()) {
      message = "Success!";
    }
    Label msg = new Label(message);
    msg.getStyleClass().add("message");
    controlbox.getChildren().add(msg);

    // h-box of controls
    ControlButtons controlbuttons = new ControlButtons(controller);
    controlbox.getChildren().add(controlbuttons.render());

    return controlbox;
  }
}
