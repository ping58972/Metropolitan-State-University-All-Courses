ICS 440-01 Parallel and Distributed Algorithms Fall 2021.

Data Sockets Lab
================
1. Run DataSocketsTest to validate your code.
2. Using RequestResponseMapping is optional. If you don't use it, look
   at the source to see the appropriate responses for various requests.
3. When the request of "GOODBYE" is sent up by the client and after the
   server responds with "BYE-BYE", the streams and Socket should be closed.
4. Use the writeUTF(String s) method on DataOutputStream to send a reqeust
   (or a response) over the OutputStream of the Socket.
5. Use the readUTF() method on DataInputStream to receive a request (or a
   response) from the InputStream of the Socket.