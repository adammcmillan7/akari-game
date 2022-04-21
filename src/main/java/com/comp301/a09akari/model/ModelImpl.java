package com.comp301.a09akari.model;

import java.util.List;

public class ModelImpl implements Model{

    private PuzzleLibrary lib;
    private List<ModelObserver> observers;
    private int puzzle_index;
    private Puzzle active_puzzle;
    private int[][] lamps;

    public ModelImpl(PuzzleLibrary library) {
        if (library == null){
            throw new NullPointerException();
        }
        lib = library;
        puzzle_index = 0;
        active_puzzle = lib.getPuzzle(0);
        initLamps();

    }

    private void initLamps(){
        lamps = new int[active_puzzle.getHeight()][active_puzzle.getWidth()];
        for (int i=0;i<active_puzzle.getHeight();i++){
            for (int j=0;j<active_puzzle.getWidth();j++){
                lamps[i][j] = 0;
            }
        }
    }


    @Override
    public void addLamp(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if ((r<0) || (c<0)){
            throw new IndexOutOfBoundsException();
        }
        else if (active_puzzle.getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }

        lamps[r][c] = 1;
        observers.notify();

    }

    @Override
    public void removeLamp(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if ((r<0) || (c<0)){
            throw new IndexOutOfBoundsException();
         }
        else if (active_puzzle.getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        else if (lamps[r][c] != 1){
            throw new IllegalArgumentException();
        }

        lamps[r][c] = 0;
        observers.notify();

    }

    @Override
    public boolean isLit(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if ((r<0) || (c<0)){
            throw new IndexOutOfBoundsException();
        }
        else if (active_puzzle.getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        else if (isLamp(r,c)){
            //cell is a lamp
            return true;
        }

        boolean lit = false;

        //scroll right thru row
        for (int i=c+1;i<active_puzzle.getWidth();i++){
            if (active_puzzle.getCellType(r,i) != CellType.CORRIDOR){
                break;
            }
            else if (isLit(r,i)){
                lit = true;
            }

        }

        //scroll left thru row
        for (int i=c-1;i>=0;i--){
            if (active_puzzle.getCellType(r,i) != CellType.CORRIDOR){
                break;
            }
            else if (isLit(r,i)){
                lit = true;
            }

        }

        //scroll up
        for (int i=r+1;i<active_puzzle.getHeight();i++){
            if (active_puzzle.getCellType(r,i) != CellType.CORRIDOR){
                break;
            }
            else if (isLit(r,i)){
                lit = true;
            }
        }

        //scroll down
        for (int i=r-1;i>=0;i--){
            if (active_puzzle.getCellType(r,i) != CellType.CORRIDOR){
                break;
            }
            else if (isLit(r,i)){
                lit = true;
            }
        }

        return lit;
    }

    @Override
    public boolean isLamp(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if ((r<0) || (c<0)){
            throw new IndexOutOfBoundsException();
        }
        else if (active_puzzle.getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }

        if (lamps[r][c] == 1){
            return true;
        }
        else return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if ((r<0) || (c<0)){
            throw new IndexOutOfBoundsException();
        }
        else if (lamps[r][c] != 1){
            throw new IllegalArgumentException();
        }

        if (isLit(r,c)){
            return true;
        }
        else return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return active_puzzle;
    }

    @Override
    public int getActivePuzzleIndex() {
        return puzzle_index;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        if (index >= lib.size()){
            throw new IndexOutOfBoundsException();
        }

        puzzle_index = index;
        active_puzzle = lib.getPuzzle(puzzle_index);
        initLamps();
        observers.notify();
    }

    @Override
    public int getPuzzleLibrarySize() {
        return lib.size();
    }

    @Override
    public void resetPuzzle() {
        initLamps();
        observers.notify();
    }

    @Override
    public boolean isSolved() {
        boolean solved = true;

        for (int r=0;r<active_puzzle.getHeight();r++){
            for (int c=0;r<active_puzzle.getWidth();c++){
                switch (active_puzzle.getCellType(r,c)){
                    case WALL:
                        break;
                    case CLUE:
                        if (!isClueSatisfied(r,c)){
                            solved = false;
                        }
                        break;
                    case CORRIDOR:
                        if (isLampIllegal(r,c)){
                            solved = false;
                            break;
                        }
                        else if (!isLit(r,c)){
                            solved = false;
                            break;
                        }
                }
            }
        }
        observers.notify();
        return solved;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if ((r > active_puzzle.getHeight()) || (c > active_puzzle.getWidth())){
            throw new IndexOutOfBoundsException();
        }
        else if (active_puzzle.getCellType(r,c) != CellType.CLUE){
            throw new IllegalArgumentException();
        }

        int clue_count = active_puzzle.getClue(r,c);
        int real_count = 0;

        if (lamps[r+1][c] == 1){
            real_count += 1;
        }
        else if (lamps[r-1][c] == 1){
            real_count +=1;
        }
        else if (lamps[r][c+1] == 1){
            real_count += 1;
        }
        else if (lamps[r][c-1] == 1){
            real_count += 1;
        }

        if (real_count == clue_count){
            return true;
        }
        else return false;
    }
    

    @Override
    public void addObserver(ModelObserver observer) {
        if (observer == null){
            throw new NullPointerException();
        }

        observers.add(observer);

    }

    @Override
    public void removeObserver(ModelObserver observer) {
        if (observer == null){
            throw new NullPointerException();
        }
        else if (!observers.contains(observer)){
            throw new IllegalArgumentException();
        }

        observers.remove(observer);

    }
}
