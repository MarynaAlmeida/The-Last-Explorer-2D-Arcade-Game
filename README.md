# The Last Explorer: 2D Arcade Game - CMPT276
A 2D arcade style game built using Java and managed with Maven for Introduction to Software Engineering (CMPT276) class. 
Players take on the role of an adventurer navigating in a maze environement to survive and complete challenges.
*(Note: Developed as part of a collaborative team project at SFU.)*

## How to play the game
1. Open the maven project on an IDE preferably Vs Code or IntelliJ.
2. Build the project by running the maven command mvn clean compile.
3. Through the game directory, run the Last Explorer by running the maven command  mvn exec:java “-Dexec.mainClass=game.main.Main”.
4. Test the game which has a test folder by running the maven command mvn test and mvn verify for additional information which the Jacoco report can be found within the site folder in target.
5. To complete the artifacts do the command mvn clean package and check inside the target folder for all artifacts.
