# Spring2026Team2


How to play the game “The Last Explorer”:
1. Open the maven project on an IDE preferably Vs Code or IntelliJ.
2. Build the project by running the maven command mvn clean compile.
3. Through the game directory, run the Last Explorer by running the maven command  mvn exec:java “-Dexec.mainClass=game.main.Main”.
4. Test the game which has a test folder by running the maven command mvn test and mvn verify for additional information which the Jacoco report can be found within the site folder in target.
5. To complete the artifacts do the command mvn clean package and check inside the target folder for all artifacts.
