package com.abc.stringcalc.dto;

import java.io.*;

/**
 * Sent from the server to the client to indicate that the 
 * <tt>DisconnectRequest</tt> has been received and that the server is ready
 * for the client to end the session. At any time after the 
 * server sends this object, the client is free to close the connection. 
 */
public class DisconnectResponse implements Serializable {
}
