package com.comp301.a09akari;

import com.comp301.a09akari.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void puzzleimp(){
    int[][] board = SamplePuzzles.PUZZLE_05;

    Puzzle puzzle = new PuzzleImpl(board);

    //assertEquals(CellType.CORRIDOR,puzzle.getCellType(0,0));
    //assertEquals(CellType.CLUE,puzzle.getCellType(0,4));


  }

  @Test
  public void modelimp(){
    int[][] board = SamplePuzzles.PUZZLE_01;
    Puzzle puzzle = new PuzzleImpl(board);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);

    Model model = new ModelImpl(lib);

    model.setActivePuzzleIndex(0);

    model.addLamp(0,0);


    assertTrue(model.isLamp(0,0));
    assertTrue(model.isLit(0,0));
    assertTrue(model.isLit(0,1));
    assertTrue(model.isLit(1,0));
    assertFalse(model.isLamp(0,1));
    assertFalse(model.isLit(2,1));

  }

  @Test
  public void iscluesat(){
    int[][] board = SamplePuzzles.PUZZLE_05;
    Puzzle puzzle = new PuzzleImpl(board);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);

    Model model = new ModelImpl(lib);

    model.setActivePuzzleIndex(0);

    model.addLamp(0,0);
/*
    assertFalse(model.isClueSatisfied(4,0));
    assertFalse(model.isClueSatisfied(5,1));
    assertTrue(model.isClueSatisfied(6,4));
    assertFalse(model.isClueSatisfied(1,5));
*/
    model.addLamp(3,0);
    model.addLamp(5,0);
    model.addLamp(4,1);

    assertTrue(model.isClueSatisfied(4,0));
  }
}
