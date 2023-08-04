# Distributed Systems - COMP SCI 3012 2023: Assignment 1
Assignment Description
Objective
To gain an understanding of how remote method invocation in Java works, synchronisation, and to develop a working example of a Java RMI system. This will be essential in developing future applications as you will quickly learn all of the pitfalls inherent in developing standards-based clients and multi-threaded servers.

To gain an understanding of how a distributed system works, this first assignment phase involves developing a simple Java RMI application. This will involve developing both the client and server side of a distributed application: a simple calculator server.

The calculator server operates a stack and clients push values and operations on to the stack. While, usually, each client should have its own stack, you may use a single stack on the server. You may also assume that operations are always sensible: that is, we will only push an operator after pushing at least on value and we will only pop when there is a value on the stack. You may also assume that the operator provided will only be one of the four displayed types and that the values are always integers.

Following the directions discussed in lectures, you should create a Java RMI Server that supports the following remote methods:

void pushValue(int val);
This method will take val and push it on to the top of the stack.

void pushOperation(String operator); 
This method will push a String containing an operator ("min", "max", "lcm", "gcd") to the stack, which will cause the server to pop all the values on the stack and:

for min - push the min value of all the popped values;
for max - push the max value of all the popped values
for lcm - push the least common multiple of all the popped values;
for gcd - push the greatest common divisor of all the popped values.
int pop(); 
This method will pop the top of the stack and return it to the client.

boolean isEmpty(); 
This method will return true if the stack is empty, false otherwise.

int delayPop(int millis); 
This method will wait millis milliseconds before carrying out the pop operation as above.
<!-- TIPS FROM THE LECTURE -->
<!--
    - Makefile: has to compile 
    - Readme: 1. they want to how to run code
    - Client: we should be able to use it to test out the functions required for the assignment
    - Multiple Client at the same time: script which will start running clients at the same time 
        - use multithreading to start multiple clients at the same time?
    Bonus marks
        - add ID (hashmap clientID -> stack)
        - ***factory rmi***
 -->
## How to compile & run program:
To initialise the rmi registry, you must run:
    make registry 

To compile and run the server, you must run:
    make compile
    make server

To compile the automated client testing run:
    make client

To compare the output of the Client to the expected outputs run:
    make outputCompare

To clean up the compiled files run:
    make clean

## ASSIGNMENT ARCHITECTURE
### Program files and structure
The program has several files. I will describe their functionality below:
- Calculator.java

- CalculatorClient.java

- CalculatorImplementation.java

- CalculatorServer.java

- Makefile

- TestInput text files

- Output text files

- ExpectedOutput text files

### Functionality


### Testing 
#### Acceptance testing: Verifying whether the whole system works as intended.
#### Integration testing: Ensuring that software components or functions operate together.
#### Unit testing: Validating that each software unit performs as expected. A unit is the smallest testable component of an application.
#### Functional testing: Checking functions by emulating business scenarios, based on functional requirements. Black-box testing is a common way to verify functions.
#### Performance testing: Testing how the software performs under different workloads. Load testing, for example, is used to evaluate performance under real-life load conditions.
#### Regression testing: Checking whether new features break or degrade functionality. Sanity testing can be used to verify menus, functions and commands at the surface level, when there is no time for a full regression test.
#### Stress testing: Testing how much strain the system can take before it fails. Considered to be a type of non-functional testing.
#### Usability testing: Validating how well a customer can use a system or web application to complete a task.
