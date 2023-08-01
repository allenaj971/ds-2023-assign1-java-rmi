compile: Calculator.java Implementation.java Client.java Server.java
	rm *.class 
	javac -d ./ Calculator.java Client.java Server.java Implementation.java
	java -cp ./ Server &

registry:
	rmiregistry &

clean:
	rm *.class

client: Client.class
	java -cp ./ Client &