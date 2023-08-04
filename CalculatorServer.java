import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
	
public class CalculatorServer {
    public static void main(String args[]) {
	try {
		// Create a skeleton of server
	    CalculatorImplementation obj = new CalculatorImplementation();
		// then we export the skelton to the rmiregistry so clients can see & access it
	    Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.rebind("Calculator", stub);

	    System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}