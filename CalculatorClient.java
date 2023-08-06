import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.io.File;

public class CalculatorClient implements Runnable {
	Calculator stub;
	// To perform the multithreaded testing, the CalculatorClient
	// extends Runnable. The constructor takes in the client's individual
	// stub session is then stored in the CalculatorClient. 
	// This is so that the multiple clients testing can be performed using 
	// Runnable. 
	public CalculatorClient(Calculator stub)
	{
		this.stub = stub;
	}
    // the commandExecutor performs the commands of the user with the user's id, stub and commands,
	// which contains the command (i.e. 'lcm', 'pushOperation') and its associated
	// value (if required for operations such as pushOperations and pushValues). This returns void
	private void commandExecutor(String cmds[], String id)
	{	
		if(cmds[0].contains("pushOperation"))
		{
			try {
				this.stub.pushOperation(id, cmds[1]);
			} catch (Exception e) {
				System.err.println("Error: " + e.toString());
			}
		}
		else if(cmds[0].contains("pushValue"))
		{
			try {
				this.stub.pushValue(id, Integer.valueOf(cmds[1]));
			} catch (Exception e) {
				System.err.println("Error: " + e.toString());
			}
		}
		else if(cmds[0].contains("pop"))
		{
			try {
				System.out.println(this.stub.pop(id));
			} catch (Exception e) {
				System.err.println("Error: " + e.toString());
			}
		}
		else if(cmds[0].contains("isEmpty"))
		{
			try {
				System.out.println(this.stub.isEmpty(id));
			} catch (Exception e) {
				System.err.println("Error: " + e.toString());
			}
		}
		else if(cmds[0].contains("delayPop"))
		{
			try {
				System.out.println(this.stub.delayPop(id, Integer.valueOf(cmds[1])));
			} catch (Exception e) {
				System.err.println("Error: " + e.toString());
			}
		}
	};

	// the fileInputProcessor processes the text input file for
	// each user's commands for automated testing. It takes the 
	// user id so that it can be passed to the commandExecutor
	// to find the user's stack. This returns void
	public void fileInputProcessor(String id)
	{
		try {
			// to get the input file associated with each thread, the 
			// String type to concatenate the thread number to get the relevant 
			// test input was used. 
			String file = "./TestInput" + Thread.currentThread().getName() + ".txt";
			// the getName() method includes "Thread-", so it is filtered out
			file = file.replace("Thread-", "");

			// initialisation of the file input processor and loading the associated
			// input file with its respective thread.
			Scanner in = new Scanner(new File(file));
			// each command in the input test file is delimited with newline
			// so I initialised it here. 
			in.useDelimiter("\n");

			// we use this exit criteria to check if user 
			// is inputing data or would like to exit
			// the program
			boolean exit = false;
			while(exit == false)
			{
				String cmd="";
				cmd+=in.nextLine();
				// we split the String into command and value 
				// and store them into the cmds[] array. 
				// cmds[0] contains the command keyword (pushOperation,
				// pushValue, etc.) and cmds[1] stores the value (i.e.
				// 'pushOperation lcm' or 'delayPop 1500')
				String cmds[] = cmd.split(" ");
				// exit if the user types into the command "exit"
				// so the while loop exits
				if(cmds[0].contains("exit"))
				{
					exit = true;
				}
				// pass the user id, command and value to the commandExecutor()
				// function
				commandExecutor(cmds, id);
			}
			// close the input handler once we are completed reading the entire
			// input text file
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.toString());
		}
	}

	
	// this is the multithread run function.
	// from here, we generate a client id for
	// each thread (representing each client)
	// then we pass those client IDs to the fileInputProcessor.
	// this returns void
	public void run()
	{
		String id = "empty";
		try {
			// generate a unique user id so that 
			// user can access only their stack
			id = this.stub.createUserID();
		} catch (Exception e) {
			System.err.println("Error: " + e.toString());
		}
		// once we generate the unique user id, 
		// we pass the user id to the fileInputProcessor
		// to perform the commands of the user
		fileInputProcessor(id);
	}
	
    public static void main(String[] args)
    {
        String host = (args.length < 1) ? null : args[0];
		try {
			// find the rmiregistry 
			Registry registry = LocateRegistry.getRegistry(host);
			// from the rmiregistry, we lookup the stub with the name "Calculator"
			Calculator stub = (Calculator) registry.lookup("Calculator");

			// to test multiple clients, I have started 4 clients using a for loop
			// running from 0 to 3 inclusive.
			for (int i = 0; i < 4; i++) {
				// we pass the client's stub to the 
				Thread t = new Thread(new CalculatorClient(stub));
				t.start();
			}
		} catch (Exception e) {
			System.err.println("CalculatorClient exception: " + e.toString());
			e.printStackTrace();
		}
    }
}
