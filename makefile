compile: Calculator.java CalculatorImplementation.java CalculatorClient.java CalculatorServer.java
	javac -d ./ Calculator.java CalculatorServer.java CalculatorImplementation.java CalculatorClient.java
	java -cp ./ CalculatorServer &

registry:
	rmiregistry &

clean:
	rm *.class

client: CalculatorClient.class
	java -cp ./ CalculatorClient &