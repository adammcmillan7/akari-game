package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle{

    private int[][] board;

    public PuzzleImpl(int[][] board) {
        if (board == null){
            throw new NullPointerException();
        }
        this.board = board;
    }


    @Override
    public int getWidth() {
        return board.length;
    }

    @Override
    public int getHeight() {
        return board[0].length;
    }

    @Override
    public CellType getCellType(int r, int c) {
        if ((r > getHeight()) || (c > getWidth())){
            throw new IndexOutOfBoundsException();
        }

        switch (board[r][c]){
            case 5:
                return CellType.WALL;
            case 6:
                return CellType.CORRIDOR;
            default:
                return CellType.CLUE;
        }

    }

    @Override
    public int getClue(int r, int c) {
        if ((r > getHeight()) || (c > getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if (getCellType(r,c) != CellType.CLUE){
            throw new IllegalArgumentException();
        }

        return board[r][c];
    }


}
