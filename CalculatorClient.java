import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

    private CalculatorClient() {}

    public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Calculator stub = (Calculator) registry.lookup("Calculator");
			// push operations
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("max");
			int response1 = stub.pop();
			System.out.println("max" + ":" + response1);

			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("min");
			int response2 = stub.pop();
			System.out.println("min" + ":" + response2);

			stub.pushValue(2389);
			stub.pushValue(2323);
			stub.pushValue(3440);
			stub.pushOperation("gcd");
			int response3 = stub.pop();
			System.out.println("gcd" + ":" + response3);

			stub.pushValue(2389);
			stub.pushValue(2323);
			stub.pushValue(3440);
			stub.pushOperation("gcd");
			int response4 = stub.pop();
			System.out.println("gcd" + ":" + response4);
			
		} catch (Exception e) {
			System.err.println("CalculatorClient exception: " + e.toString());
			e.printStackTrace();
		}
    }
}
