import java.rmi.Remote;
import java.rmi.RemoteException;
// remote interface
public interface Calculator extends Remote {
    // API definition
    public void pushValue(Integer val) throws RemoteException;
    public void pushOperation(String operator) throws RemoteException;
    public Integer pop() throws RemoteException;
    public boolean isEmpty() throws RemoteException;
    public Integer delayPop(Integer millis) throws RemoteException;
}