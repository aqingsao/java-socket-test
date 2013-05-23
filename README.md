java-socket-test
================

A simple Java socket client to test TCP connections to a server.

### Background
When developing a notification pushing server for Android OS, a widely used method is to keep a TCP connection between server and android client. However, there are limits in Linux for how many files a process could open, so we have to optimize system configuration to improve the limits, say using "ulimit -n" to improve hard limits and soft limits.
After optimizing, we need a client to test how many TCP connections a server could support, this is why this project is created.

### Setting up project
This project uses Intellij IDEA as IDE and gradle as build script, to set it up, you could:
* Fork code in github or clone the repository to your local disk;
* Open IDEA and use "Import Project..."; Or you could run "gradle cleanIdea idea"(which maybe not work sometimes)

### Usage
* You could adjust host、port and connection limit in Main class and run main method directly;
* Or you could run "gradle clean jar", and run "java -jar build/libs/java-socket-test.jar <host> <port> <connectionLimit>"

### You'd better to know
* This project will reconnect for up to 11 times if failed to connect to a server, and it will sleep for 24 hours to keep all TCP connections alive;
* For a client, it will try to connect to server continuously without sleeping, so that you could easily get a huge number of connections quickly;
* For a client, there might be a OS level limit for TCP connections, so if it failed, it might be a client problem instead of a server problem;
* You'd better install this tool on different machines, say 10 PCs, so that you could get 10(clients) * 10000(connections/client)=100,000 connections.
