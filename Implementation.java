import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Implementation implements Calculator{
    // implementation for interface
    private ArrayList<Integer> values = new ArrayList<>();

    // PRIVATE METHODS FOR THE pushPop METHODS
    public Implementation() throws RemoteException{
        super();
    };

    public ArrayList<Integer> debug()
    {
        return this.values;
    }

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
    // arraylist of integers 
    private static int lcm(ArrayList<Integer>arr)
    {
        // we have the lcm set to -1 if 
        // array is null
        int ans = arr.get(0);
        // we calculate the lcm of the answer
        // and the next avaliable element in
        // the arraylist
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
    private static int gcd(ArrayList<Integer>arr)
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
            ans = lcm(this.values);
        }
        this.values.clear();
        this.values.add(ans);
    }

    public Integer pop() 
    {
        int ans = this.values.get(this.values.size() - 1);
        this.values.remove(this.values.size() - 1);
        return ans;
    }

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
    public Integer delayPop(Integer millis) 
    {
        int ans = -1;
        // sleep for x milliseconds
        try {
            Thread.sleep(millis);
            // then pop
            ans = this.values.get(this.values.size() - 1);
            this.values.remove(this.values.size() - 1);
        } catch (Exception e) {
            // handle exception
            System.err.println("Server exception: " + e.toString());
	        e.printStackTrace();
        }
        return ans;
    }
}