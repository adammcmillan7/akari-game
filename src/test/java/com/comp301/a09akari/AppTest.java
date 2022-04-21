package com.comp301.a09akari;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.comp301.a09akari.model.*;
import org.junit.Test;

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

    System.out.println(puzzle.getHeight());
  }

  @Test
  public void modelimp(){
    int[][] board = SamplePuzzles.PUZZLE_01;
    Puzzle puzzle = new PuzzleImpl(board);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);

    Model model = new ModelImpl(lib);

  }
}
