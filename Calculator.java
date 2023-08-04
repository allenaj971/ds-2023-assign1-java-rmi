import java.rmi.Remote;
import java.rmi.RemoteException;
// This file defines the remote interface, defining
// how the client can interact with the server.
public interface Calculator extends Remote {
    // to facilitate multiple users with their unique stack
    // I have resorted to using a map, which maps the client
    // id to their stack, on which, all the operations can be
    // performed. Prior to calling each method, the client must call
    // the createUserID() function use the other methods.
    public String createUserID() throws RemoteException;
    public void pushValue(String id, Integer val) throws RemoteException;
    public void pushOperation(String id, String operator) throws RemoteException;
    public Integer pop(String id)  throws RemoteException;
    public boolean isEmpty(String id) throws RemoteException;
    public Integer delayPop(String id, Integer millis) throws RemoteException;
}