package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class GridContainer implements FXComponent {

  private final ClassicMvcController controller;

  public GridContainer(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox container = new HBox();
    container.setAlignment(Pos.CENTER);
    GameGrid grid = new GameGrid(controller);
    container.getChildren().add(grid.render());
    return container;
  }
}
