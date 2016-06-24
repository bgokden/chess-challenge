# chess-challenge
Uniquely places given chess pieces on MxN board

This application finds all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N where none of the pieces is in a position to take any of the others.
Assume the colour of the piece does not matter, and that there are no pawns among the pieces.
Program which takes as input as:
- The dimensions of the board: M, N
- The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the board.
As output, the program lists all the unique configurations to the console for which all of the pieces can be placed on the board without threatening each other.
To run the program execute the command:

    `mvn exec:java`

To run the tests execute the command:

    `mvn test`

To run the sonar execute the command:

    `mvn sonar:sonar`

Before running sonar start sonar service (as docker command):
    `docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube`

Test Case For Pieces:[KNIGHT, KING, KING, QUEEN, QUEEN, BISHOP, BISHOP] on 7x7 board
- Time: 1055124 ms
- Number of Unique Solutions: 2148780
