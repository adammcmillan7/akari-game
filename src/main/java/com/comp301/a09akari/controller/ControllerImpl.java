package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class ControllerImpl implements ClassicMvcController {

  private final Model model;
  private int puzzle_index;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new NullPointerException();
    }
    this.model = model;
    puzzle_index = 0;
  }

  @Override
  public void clickNextPuzzle() {
    if (puzzle_index == model.getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }

    puzzle_index += 1;
    model.setActivePuzzleIndex(puzzle_index);
  }

  @Override
  public void clickPrevPuzzle() {
    if (puzzle_index == 0) {
      throw new IndexOutOfBoundsException();
    }

    puzzle_index -= 1;
    model.setActivePuzzleIndex(puzzle_index);
  }

  @Override
  public void clickRandPuzzle() {
    puzzle_index = (int) (Math.random() * model.getPuzzleLibrarySize());
    model.setActivePuzzleIndex(puzzle_index);
  }

  @Override
  public void clickResetPuzzle() {
    model.setActivePuzzleIndex(puzzle_index);
  }

  @Override
  public void clickCell(int r, int c) {
    // click = make corridor a lamp
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public String getPuzzleName() {
    return "Puzzle " + (puzzle_index + 1);
  }

  @Override
  public String getPuzzleSize() {
    int height = model.getActivePuzzle().getHeight();
    int width = model.getActivePuzzle().getWidth();

    return height + " x " + width;
  }

  @Override
  public Model getModel() {
    return model;
  }

  @Override
  public void selectPuzzle(int index) {
    puzzle_index = index;
    model.setActivePuzzleIndex(puzzle_index);
  }
}
