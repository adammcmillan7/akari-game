package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // model
    int[][] puz1 = SamplePuzzles.PUZZLE_01;
    int[][] puz2 = SamplePuzzles.PUZZLE_02;
    int[][] puz3 = SamplePuzzles.PUZZLE_03;
    int[][] puz4 = SamplePuzzles.PUZZLE_04;
    int[][] puz5 = SamplePuzzles.PUZZLE_05;

    PuzzleLibrary lib = new PuzzleLibraryImpl();
    Puzzle puzzle1 = new PuzzleImpl(puz1);
    Puzzle puzzle2 = new PuzzleImpl(puz2);
    Puzzle puzzle3 = new PuzzleImpl(puz3);
    Puzzle puzzle4 = new PuzzleImpl(puz4);
    Puzzle puzzle5 = new PuzzleImpl(puz5);

    lib.addPuzzle(puzzle1);
    lib.addPuzzle(puzzle2);
    lib.addPuzzle(puzzle3);
    lib.addPuzzle(puzzle4);
    lib.addPuzzle(puzzle5);

    Model model = new ModelImpl(lib);

    // controller
    ClassicMvcController controller = new ControllerImpl(model);

    // view
    AppView app = new AppView(controller);

    // scene
    Scene scene = new Scene(app.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    // refresh render
    model.addObserver(
        (Model m) -> {
          scene.setRoot(app.render());
        });
    stage.setHeight(700);
    stage.setWidth(700);
    stage.setTitle("Akari Light-Up Puzzles");
    stage.show();
  }
}
