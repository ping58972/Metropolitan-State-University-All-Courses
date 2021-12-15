# sender and receiver program

- Enveronment: Java version 11 up.

- run program in terminal or command line:
    First, run the server program:
    - open a terminal in server folder.
    - type command line: 
        - MacOS or Linux: "java UdpFileReceiver.java" 
        - Windows: "javac UdpFileReceiver.java" 
                then "java UdpFileReceiver"
    Then waiting for client to send a file come.
    second, run the client program to send a file:
    - open a terminal in client folder.
    - type command line: 
        - MacOS or Linux: "java UdpFileSender.java " follow by a file name that want to send.
            - example: "java UdpFileSender.java panda.jpg"
        - Windows: "javac UdpFileSender.java"
            then "java UdpFileSender " follow by a file name that want to send.
            - example: "java UdpFileSender panda.jpg"
    Finally, two terminal output the results and print out all packet info.

### Projext Discribe: 									

    Write a sender program and a receiver program, where the sender accepts a file as a command line parameter (any binary file on your hard disk), breaks it into smaller chunks, and sends it to the receiver using UDP.

    The receiver will concatenate the pieces it receives andwill store it to a file on its end.  
    The sender and receiver will write out a detailed log of what they are doing to the console. 
        - for each datagram sent, the sender will write the [packet#]-[start byte offset]-[end byte offset].
        - for each datagram received, the receiver will write the [packet#]-[start byte offset]-[end byte offset]
    Note:
        -use the UDP protocol for this project.
        -transmit a binary file (image or sound file)

 * ICS460-01 Fall2021, Project 1 receiver program -  server side.
 * Write by Team #3: 
  		Asha Hassan, Ahmad Elsaid, Chitakhone Siharath, Nalongsone Danddank, Seth Prokop, Kou Yang, 