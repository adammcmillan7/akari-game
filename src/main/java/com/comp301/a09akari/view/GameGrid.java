package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameGrid implements FXComponent {

  private final ClassicMvcController controller;

  public GameGrid(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    int height = controller.getModel().getActivePuzzle().getHeight();
    int width = controller.getModel().getActivePuzzle().getWidth();
    grid.setAlignment(Pos.CENTER);
    grid.setGridLinesVisible(false);
    grid.setHgap(3);
    grid.setVgap(3);
    grid.setBackground(
        new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0, false), Insets.EMPTY)));

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        switch (controller.getModel().getActivePuzzle().getCellType(r, c)) {
          case CORRIDOR:
            Label tile = new Label("0");
            int finalC = c;
            int finalR = r;
            tile.setOnMouseClicked(mouseEvent -> controller.clickCell(finalR, finalC));

            if (controller.getModel().isLit(r, c)) {
              tile.getStyleClass().add("lit_tile");
            } else {
              tile.getStyleClass().add("corridor");
            }

            if (controller.getModel().getActivePuzzle().getHeight() > 7) {
              tile.getStyleClass().add("small_tile");
            } else {
              tile.getStyleClass().add("big_tile");
            }

            if (controller.getModel().isLamp(r, c)) {
              // tile.getStyleClass().removeAll();
              ImageView img = new ImageView(new Image("light-bulb.png"));

              if (controller.getModel().getActivePuzzle().getHeight() > 7) {
                img.setFitWidth(22);
                img.setFitHeight(22);
                img.setTranslateX(10);
              } else {
                img.setFitWidth(32);
                img.setFitHeight(32);
                img.setTranslateX(10);
              }

              tile.setGraphic(img);
              tile.setAlignment(Pos.CENTER_RIGHT);

              if (controller.getModel().isLampIllegal(r, c)) {
                ImageView illegalimg = new ImageView(new Image("illegal-light-bulb.png"));

                if (controller.getModel().getActivePuzzle().getHeight() > 7) {
                  illegalimg.setFitWidth(22);
                  illegalimg.setFitHeight(22);
                  illegalimg.setTranslateX(10);
                } else {
                  illegalimg.setFitWidth(32);
                  illegalimg.setFitHeight(32);
                  illegalimg.setTranslateX(10);
                }
                tile.setGraphic(illegalimg);
                tile.setAlignment(Pos.CENTER_RIGHT);
              }
            }

            grid.add(tile, c, r);
            break;
          case WALL:
            Label wall_tile = new Label("5");
            wall_tile.getStyleClass().add("wall");
            if (controller.getModel().getActivePuzzle().getHeight() > 7) {
              wall_tile.getStyleClass().add("small_tile");
            } else {
              wall_tile.getStyleClass().add("big_tile");
            }
            grid.add(wall_tile, c, r);
            break;
          case CLUE:
            int clue = (controller.getModel().getActivePuzzle().getClue(r, c));
            Label clue_tile = new Label(Integer.toString(clue));
            if (controller.getModel().isClueSatisfied(r, c)) {
              clue_tile.getStyleClass().add("satisfied_clue");
            } else clue_tile.getStyleClass().add("clue");

            if (controller.getModel().getActivePuzzle().getHeight() > 7) {
              clue_tile.getStyleClass().add("small_tile");
            } else {
              clue_tile.getStyleClass().add("big_tile");
            }

            grid.add(clue_tile, c, r);
            break;
        }
      }
    }

    return grid;
  }
}
