import java.rmi.RemoteException;
import java.util.*;

public class CalculatorImplementation implements Calculator {
    // to create the functionality for the unique client stack,
    // the hashmap data structure was used, by mapping the user's
    // ID to their respective stack. 
    private Map<String, Stack<Integer>> values = new HashMap<>();

    // Constructor 
    public CalculatorImplementation() throws RemoteException{
        super();
    };
    // PRIVATE METHODS FOR THE pushPop METHODS
    // these private methods are helper functions
    // which perform the calculations such min, max, lcm 
    // & gcd operations on the Stack<Integer> values.

    // this is the helper function for the vectorGCD
    // function. This will return the global 
    // gcd, where x is the current gcd of the previous elements 
    // and y is the current element for which we are trying to find 
    // the gcd of x & y.
    private static int gcdHelper(int x, int y)
    {   
        if(y == 0)
        {
            return x;
        }
        return gcdHelper(y, x % y);
    }

    // this is the helper function for the lcm 
    // function. it returns the lcm for 2
    // numbers. 
    private static int lcmHelper(int x, int y)
    {
        return (x * y) / gcdHelper(x, y);
    }

    // LCM returns the least common multiple of a
    // stack of integers.
    private static int lcm(Stack<Integer>stack)
    {
        int ans = stack.get(0);
        // we calculate the lcm of the answer
        // and the next avaliable element in
        // the stack
        for (int i = 1; i < stack.size(); i++) {
            // calculate the lcm of the current lcm 
            // and next element using the lcmHelper 
            // function
            ans = lcmHelper(ans, stack.get(i));
        }
        return ans;
    }

    // this function returns the greatest common divisor 
    // of the array by finding the gcd of the current gcd with 
    // the next avaliable element in the array.
    private static int gcd(Stack<Integer>stack)
    {   
        int ans = stack.get(0);
        for (int i = 1; i < stack.size(); i++) {
            ans = gcdHelper(ans, stack.get(i));
        }

        return ans;
    }

    // PUBLIC METHODS ACCESSIBLE TO CLIENT

    // This helper function creates a user ID for the user
    // so that they have their own stack in the map.
    // this method must be called prior to using other 
    // public methods because they require the ID this method
    // generates. Returns a unique user id used to access the
    // stack. 
    public String createUserID()
    {
        // create a unique ID for the user to access
        // their stack
        String Id = UUID.randomUUID().toString();
        // add a new stack with the new user ID
        this.values.put(Id, new Stack<>());
        // return this id to the user so that
        // they can access their stack by passing
        // this ID to the other methods.
        return Id;
    }

    // pushValue takes the id and val and searches for the 
    // user's stack in the map. Once finding the user's stack
    // pushes the value to the top of the stack. It returns void.
    public void pushValue(String id, Integer val) 
    {
        this.values.get(id).push(val);
    }

    // pushOperation takes in the id and the operator. 
    // based on the operator passed into the argument,
    // we call the respective if statement, calculate the
    // value based on the operator, clear the stack and push 
    // the final result to the user's stack. 
    public void pushOperation(String id, String operator)
    {
        // check if the stack has values, else do nothing
        if(this.values.get(id).size() > 0)
        {
            int ans;
            if(operator.contains("min"))
            {
                // Use inbuilt functions to calculate minimum
                // & maximum rather than increase complexity with my 
                // own implmentation 
                ans = Collections.min(this.values.get(id));
            }
            else if (operator.contains("max"))
            {
                ans = Collections.max(this.values.get(id));
            }
            else if (operator.contains("gcd"))
            {
                // calculate the greater common divisor
                // if operator contains string "gcd"
                ans = gcd(this.values.get(id));
            }
            else
            {
                // else if string is not min, max or gcd, it must
                // be lcm, so calculate lcm
                ans = lcm(this.values.get(id));
            }
            // pop all values off of the stack
            this.values.get(id).clear();
            // push the final answer to the stack
            this.values.get(id).add(ans);
        }
    }

    // this function takes the user's id and 
    // pops the value from the stack if there 
    // is a value on the stack, else returns null
    public Integer pop(String id) 
    {
        if(this.values.get(id).size() == 0)
        {
            // return null because no
            // value exists on the user's stack
            return null;
        }
        else
        {
            // else return the value to the client
            return this.values.get(id).pop();
        }
    }
    
    // this function takes the user ID &
    // determines whether their stack is empty
    // it will return true for empty stack, and 
    // false otherwise. 
    public boolean isEmpty(String id)
    {
        return this.values.get(id).isEmpty();
    }

    // This is the delay pop function
    // it takes the client ID and the time to sleep
    // before returning the value at the top of the
    // user's stack. this function will return the 
    // popped value on the top of the stack after 
    // millis milliseconds. 
    public Integer delayPop(String id, Integer millis) 
    {    
        // check if user's stack size is greater than 0
        // then only attempt to delayPop
        if(this.values.get(id).size() > 0)
        { 
            int ans = -1;
            // sleep for millis milliseconds
            try {
                Thread.sleep(millis);
                
                // return the value on the stack 
                ans = this.values.get(id).pop();
            } catch (Exception e) {
                // handle exception by printing error
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
            return ans;
        }
        else
        {
            // else just return null
            return null;
        }
    }
}
