import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
	
public class Server {
    public static void main(String args[]) {
	try {
	    Implementation obj = new Implementation();
	    Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    // registry.bind("Calculator", stub);
	    registry.rebind("Calculator", stub);

	    System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}