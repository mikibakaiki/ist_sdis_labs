# TTT RMI client

This is the Java RMI implementation of the *Tic Tac Toe* game client.

The client depends on the interface module,
where the interface shared between server and client is defined.
The client needs to know the interface to generate the proxy.

## Instructions using Maven

Make sure to install the interface module **before** compiling the server.

 1. Compile the client and copy the properties file to the output directory via the terminal:
```
    mvn compile
```


 2. **(optional)** Generate launch scripts for Windows and Linux. This uses the
*appassembler* plugin's *assemble* goal, which is attached to the install phase:
```
    mvn install
```

 3. Run the RMI client:

  * Using Maven *exec* plugin:
```
   mvn exec:java
```

  * Using the Maven *appassembler* plugin:
    - On Windows: `target\appassembler\bin\ttt-rmi-client`

    - On Linux:   `target/appassembler/bin/ttt-rmi-client`


## Instructions using Eclipse

To configure the Maven project in Eclipse:

Select 'File', 'Import...', 'Maven'-'Existing Maven Projects'.

Then, 'Select root directory' and 'Browse' to the project base folder.
Check that the desired POM is selected and 'Finish'.

---
 - Revision date: 2019-03-05
 - [SD Faculty](mailto:leic-sod@disciplinas.tecnico.ulisboa.pt)
