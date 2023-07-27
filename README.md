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
