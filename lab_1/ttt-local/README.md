# Tic Tac Toe

This is a Java implementation of the Tic Tac Toe game


## Instructions using Maven:

To compile:
```
  mvn compile
```

To run using exec plugin:
```
  mvn exec:java
```

To generate launch scripts for Windows and Linux:
  (appassembler:assemble is attached to install phase)
```
  mvn install
```

To run using appassembler plugin:

  On Windows:
```
    target\appassembler\bin\ttt-local
```
  On Linux:
```
    ./target/appassembler/bin/ttt-local
```


To configure the Maven project in Eclipse:
-----------------------------------------

'File', 'Import...', 'Maven'-'Existing Maven Projects'

'Select root directory' and 'Browse' to the project base folder.

Check that the desired POM is selected and 'Finish'.


--
[SD Faculty](mailto:leic-sod@disciplinas.tecnico.ulisboa.pt)

