compile: Calculator.java CalculatorImplementation.java CalculatorClient.java CalculatorServer.java
	javac -d ./ Calculator.java CalculatorClient.java CalculatorServer.java CalculatorImplementation.java

registry:
	rmiregistry &

server: CalculatorServer.class
	java -cp ./ CalculatorServer

client: CalculatorClient.class
	java -cp ./ CalculatorClient