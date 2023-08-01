import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public Client() {}

    public static void main(String[] args) {
		// 
		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Calculator stub = (Calculator) registry.lookup("Calculator");
			// lcm operation 
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("lcm");
			int res = stub.pop();
			System.out.println("lcm? " + res);

			// max operation
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("max");
			res = stub.pop();
			System.out.println("max: " + res);

			// min operation
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("min");
			res = stub.pop();
			System.out.println("min: " + res);

			// gcd operations
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("gcd");
			res = stub.pop();
			System.out.println("gcd: " + res);

		} catch (Exception e) {
			System.err.println("CalculatorClient exception: " + e.toString());
			e.printStackTrace();
		}
    }
}
