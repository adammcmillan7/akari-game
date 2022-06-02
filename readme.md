## Introduction

This was the final project for a Computer Science course taken at UNC. All interfaces were provided by the professor, with some alterations by me. All other code is my original work, except where noted.

This project utilizes the model-view-controller design pattern and JavaFX to design a from-scratch GUI implementation of the logic puzzle game akari: https://en.wikipedia.org/wiki/Light_Up_(puzzle) . Some sample puzzles: https://www.puzle-light-up.com/ . The finished product can be run via Maven in IntelliJ by clicking the Maven tab, then double clicking on "javafx:run" under Plugins > javafx.


## Structure

### Puzzles

Each Puzzle encapsulates a 2d array of integers to represent the puzzle board, a 2d grid. (0,0) represents the top left corner of the game grid. Each integer represents a space on the grid being a clue (0-4), a wall (5), or an empty corridor (6). SamplePuzzles.java is a collection of 5 puzzles that are implemented in the final product and can be solved by the user. SamplePuzzles.java was provided and is not my work.

Each PuzzleLibrary encapsulates a list of puzzles, which can be edited with an add method and individually accessed using a getter method.

### Model

The Model implementation encapsulates a PuzzleLibrary of available puzzles, the active Puzzle and its library index, a list of observers, and an 2d integer array with the location of user-placed lamps on the game grid. It also features a method which determines if the puzzle has been solved. A puzzle is considered solved if all empty corridors are lit, no lamps are placed illegally, and all clues are satisfied. Refer to the above Wikipedia link for a more detailed explanation of the rules of the game. The Model implementation includes all necessary methods for playing the game and determining if the puzzle is solved.

### Controller

Controller acts as a "translator" between the Model and View in the MVC design pattern. As such, methods in Controller.java simply map buttons, clicks, and other user actions in the GUI to their proper methods in the Model, with some limited parameter validation and exception catching.


### View

View holds all code involved with the GUI. AppLauncher.java is the launching point of the application; the start() method launches the JavaFX GUI. AppLauncher creates a PuzzleLibrary from the Sample Puzzles and instatiates a Model that takes this PuzzleLibrary as a parameter. Likewise, instantiation of a Controller takes Model as a parameter. The GUI is composed of a multitude of components organized in a hierarchy as seen below. The parent component, AppView, is instantiated in AppLauncher.java.

![Chart1](https://user-images.githubusercontent.com/77686811/171518171-f80184b0-bcba-4a3a-b504-cbfa6b7c20d7.PNG)
![Chart2](https://user-images.githubusercontent.com/77686811/171518173-3d784083-59fd-43e0-8368-d1a5024e1dbe.PNG)


## Screenshots of Completed Application
![App2](https://user-images.githubusercontent.com/77686811/171518519-db91c8da-b629-4ea8-934c-27723c2498ab.png)

A clean puzzle board.

![App1](https://user-images.githubusercontent.com/77686811/171518518-9a61ecef-01f2-4fe6-a22d-c6d9ed799add.png)

A solved 7x7 puzzle.

![App3](https://user-images.githubusercontent.com/77686811/171518520-1bc8d09d-faf4-44f6-bf8a-01fd64912767.png)

A larger 10x10 puzzle.

![App4](https://user-images.githubusercontent.com/77686811/171518522-c48a4e8a-a47b-439b-96be-d6e588faf3a1.png)

An unsolved puzzle with unsatisfied clues and illegal lamps.
