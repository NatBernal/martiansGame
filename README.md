# Martian Invaders: A Space Alien Elimination Game

## Description

Martian Invaders is a classic shooting game where the goal is to eliminate the aliens that randomly appear on the screen using a cannon controlled with arrow keys. The game features elements of speed, strategy, and coordination to provide an entertaining and challenging experience.

## Requirements

- Java Development Kit (JDK) 8 or later.
- A terminal or an IDE capable of compiling and running Java applications.
- A graphical environment capable of running Java Swing applications.

The game does not require external libraries or a build tool. The image assets and game configuration are stored in the `resources` directory.

## Compile and Run

From the project root, compile the source files into the ignored `bin` directory:

```text
javac -d bin src/App.java src/com/natalia/game/**/*.java
```

Then start the game:

```text
java -cp bin App
```

On Windows PowerShell, if the `**` wildcard is not expanded by the shell, use:

```powershell
javac -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp bin App
```

Run the commands from the project root so the application can locate `resources/game.propierties` and its images.

## Game Instructions

1. Move the cannon from left to right using the left and right arrow keys.
2. Fire bullets by pressing the spacebar key.
3. Eliminate the aliens before they reach the other side of the screen.
4. Earn points for each eliminated alien.

## Author

Nat Bernal
