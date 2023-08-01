import java.rmi.RemoteException;
import java.util.*;
import java.util.Collections;

public class CalculatorImplementation implements Calculator {
    // implementation for interface
    // using arraylist as stack
    private Stack<Integer> values = new Stack<>();

    // Constructor 
    public CalculatorImplementation() throws RemoteException{
        super();
    };
    // PRIVATE METHODS FOR THE pushPop METHODS
    // these private methods are helper functions
    // which perform the calculations such min, max, lcm 
    // & gcd operations on the Stack<Integer> values.

    // this is the helper function for the vectorGCD
    // function. This will help find the global 
    // gcd, where x is the current gcd of the previous elements 
    // and y is the current element for which we are trying to find 
    // the gcd of x & y
    private static int gcdHelper(int x, int y)
    {   
        if(y == 0)
        {
            return x;
        }
        return gcdHelper(y, x % y);
    }

    // this is the helper function for the lcm 
    // function. it calculates the lcm for 2
    // numbers
    private static int lcmHelper(int x, int y)
    {
        return (x * y) / gcdHelper(x, y);
    }

    // LCM calculates the least common multiple of an
    // Stack of integers 
    private static int lcm(Stack<Integer>arr)
    {
        // we have the lcm set to -1 if 
        // array is null
        int ans = arr.get(0);
        // we calculate the lcm of the answer
        // and the next avaliable element in
        // the Stack
        for (int i = 1; i < arr.size(); i++) {
            // calculate the lcm of the current lcm 
            // and next element using the lcmHelper 
            // function
            ans = lcmHelper(ans, arr.get(i));
        }
        return ans;
    }

    // this function calculates the greatest common divisor 
    // of the array by finding the gcd of the current gcd with 
    // the next avaliable element in the array 
    private static int gcd(Stack<Integer>arr)
    {   
        int ans = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            ans = gcdHelper(ans, arr.get(i));
        }

        return ans;
    }

    // PUBLIC METHODS ACCESSIBLE TO CLIENT
    public void pushValue(Integer val) 
    {
        this.values.add(val);
    }
    // PROBLEMATIC PART
    public void pushOperation(String operator)
    {
        // initialise answer
        int ans;
        if(operator.contains("min"))
        {
            // Use inbuilt functions to calculate minimum
            // & maximum rather than increase complexity with my 
            // own implmentation 
            ans = Collections.min(this.values);
        }
        else if (operator.contains("max"))
        {
            ans = Collections.max(this.values);
        }
        else if (operator.contains("gcd"))
        {
            // calculate the greater common divisor
            // if operator contains string "gcd"
            ans = gcd(this.values);
        }
        else
        {
            // else if string is not min, max or gcd, it must
            // be lcm, so calculate lcm
            ans = lcm(this.values);
        }
        // pop all values off of the stack
        this.values.clear();
        // push the final answer to the stack
        this.values.add(ans);
    }

    // this function will pop from the stack
    // return the value to the client
    public Integer pop() 
    {
        return this.values.pop();
    }
    
    // this function determines if stack is empty
    // by checking the size of the stack
    public boolean isEmpty()
    {
        if(this.values.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // This is the delay pop function
    public Integer delayPop(Integer millis) 
    {     
        int ans = -1;
        // sleep for x milliseconds
        try {
            Thread.sleep(millis);
            // then pop
            ans = this.values.pop();
        } catch (Exception e) {
            // handle exception
            System.err.println("Server exception: " + e.toString());
	        e.printStackTrace();
        }
        return ans;
    }
}