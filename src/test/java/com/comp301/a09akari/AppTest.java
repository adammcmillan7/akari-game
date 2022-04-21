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

  @Test
  public void isSolved(){
    int[][] board = SamplePuzzles.PUZZLE_05;
    Puzzle puzzle = new PuzzleImpl(board);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);

    Model model = new ModelImpl(lib);

    model.setActivePuzzleIndex(0);

    assertFalse(model.isSolved());
    model.addLamp(3,0);
    model.addLamp(5,0);
    model.addLamp(4,1);
    model.addLamp(0,1);
    model.addLamp(6,2);
    model.addLamp(5,3);
    model.addLamp(0,5);
    model.addLamp(1,4);
    model.addLamp(2,5);

    assertTrue(model.isClueSatisfied(4,0));
    assertTrue(model.isClueSatisfied(5,1));
    assertTrue(model.isClueSatisfied(6,4));
    assertTrue(model.isClueSatisfied(1,5));

    assertTrue(model.isSolved());
  }

  @Test
  public void isSolvedAllPuzzles(){
    int[][] puz1 = SamplePuzzles.PUZZLE_01;
    int[][] puz2 = SamplePuzzles.PUZZLE_02;
    int[][] puz3 = SamplePuzzles.PUZZLE_03;
    int[][] puz4 = SamplePuzzles.PUZZLE_04;

    PuzzleLibrary lib = new PuzzleLibraryImpl();
    Puzzle puzzle1 = new PuzzleImpl(puz1);
    Puzzle puzzle2 = new PuzzleImpl(puz2);
    Puzzle puzzle3 = new PuzzleImpl(puz3);
    Puzzle puzzle4 = new PuzzleImpl(puz4);

    lib.addPuzzle(puzzle1);
    lib.addPuzzle(puzzle2);
    lib.addPuzzle(puzzle3);
    lib.addPuzzle(puzzle4);

    Model model = new ModelImpl(lib);
    //Puzzle 1
    assertFalse(model.isSolved());
    model.addLamp(0,3);
    model.addLamp(1,1);
    model.addLamp(2,5);
    assertFalse(model.isSolved());
    model.addLamp(3,4);
    model.addLamp(3,6);
    model.addLamp(4,5);
    model.addLamp(5,2);
    assertFalse(model.isSolved());
    model.addLamp(5,6);
    model.addLamp(6,0);
    model.addLamp(6,3);

    assertTrue(model.isSolved());

    //Puzzle 2
    model.setActivePuzzleIndex(1);
    assertFalse(model.isSolved());
    model.addLamp(0,1);
    model.addLamp(0,4);
    model.addLamp(1,3);
    model.addLamp(2,0);
    model.addLamp(2,2);
    model.addLamp(2,9);
    model.addLamp(3,1);
    model.addLamp(4,6);
    model.addLamp(5,3);
    assertFalse(model.isSolved());
    model.addLamp(5,7);
    model.addLamp(6,2);
    model.addLamp(6,5);
    model.addLamp(6,8);
    assertFalse(model.isSolved());
    model.addLamp(7,0);
    model.addLamp(7,4);
    model.addLamp(7,9);
    model.addLamp(8,8);
    assertFalse(model.isSolved());
    model.addLamp(9,2);
    model.addLamp(9,7);

    assertTrue(model.isSolved());

    //Puzzle 3
    model.setActivePuzzleIndex(2);
    assertFalse(model.isSolved());
    model.addLamp(0,1);
    model.addLamp(0,5);
    model.addLamp(1,4);
    model.addLamp(1,6);
    assertFalse(model.isSolved());
    model.addLamp(2,5);
    model.addLamp(3,0);
    model.addLamp(4,1);
    model.addLamp(5,0);
    assertFalse(model.isSolved());
    model.addLamp(5,3);
    model.addLamp(6,2);
    model.addLamp(6,6);

    assertTrue(model.isSolved());

    //illegal lamp
    model.addLamp(2,0);
    assertFalse(model.isSolved());

    //Puzzle 4
    model.setActivePuzzleIndex(3);
    assertFalse(model.isSolved());

    model.addLamp(0,2);
    model.addLamp(0,9);
    assertFalse(model.isSolved());
    model.addLamp(1,7);
    model.addLamp(2,0);
    model.addLamp(2,6);
    model.addLamp(3,1);
    model.addLamp(4,7);
    model.addLamp(5,9);
    assertFalse(model.isSolved());
    model.addLamp(6,4);
    model.addLamp(7,0);
    model.addLamp(7,3);
    model.addLamp(7,8);
    assertFalse(model.isSolved());
    model.addLamp(8,2);
    model.addLamp(9,0);
    model.addLamp(9,5);
    model.addLamp(9,9);

    assertTrue(model.isSolved());

    model.removeLamp(3,1);
    assertFalse(model.isSolved());


  }

  @Test
  public void smallPuzzles(){
    int[][] sm1 = {
            {6}
    };
    Puzzle puzzle = new PuzzleImpl(sm1);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);
    Model model = new ModelImpl(lib);

    assertFalse(model.isSolved());

    model.addLamp(0,0);
    assertTrue(model.isSolved());
  }
}
