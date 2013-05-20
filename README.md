java-socket-test
================

A simple Java socket client to test TCP connections to a server.

### Setting up project
This project uses Intellij IDEA as IDE and gradle as build script, to set it up, you could:
* Fork code in github or clone the repository to your local disk;
* Open IDEA and use "Import Project..."; Or you could run "gradle cleanIdea idea"
* Then you could adjust parameters in Main.java and run main() method;

### Usage
* Host: the host of the server you want to connect;
* Port: The port of the server you want to connect;
* ClientCount: How many socket clients you want to create, socket client will open a TCP connection to the server;
* ConnectIntervalInSeconds: How many seconds should sleep before creating a new socket client, if this interval is too short, socket might fail to open connection.


