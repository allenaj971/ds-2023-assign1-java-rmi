import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

    public CalculatorClient() {}

    public static void main(String[] args) {
		// 
		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Calculator stub = (Calculator) registry.lookup("Calculator");
			// lcm operation & pop testing
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(32);
			stub.pushOperation("lcm");
			int res = stub.pop();
			System.out.println("lcm? " + res);

			// max operation & delayPop testing
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("max");
			res = stub.delayPop(2000);
			System.out.println("max: " + res);

			// min operation & pop testing
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(30);
			stub.pushOperation("min");
			res = stub.pop();
			System.out.println("min: " + res);

			// gcd operations & delayPop testing
			stub.pushValue(10);
			stub.pushValue(20);
			stub.pushValue(31);
			stub.pushOperation("gcd");
			res = stub.delayPop(1500);
			System.out.println("gcd: " + res);

			// isEmpty testing
			boolean res1 = stub.isEmpty();
			System.out.println("is empty? " + res1);

			return;
		} catch (Exception e) {
			System.err.println("CalculatorClient exception: " + e.toString());
			e.printStackTrace();
		}
    }
}
