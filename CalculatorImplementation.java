import java.util.Stack;
import java.util.Vector;

public class CalculatorImplementation implements Calculator{
    // implementation for interface
    private Stack<Integer> values = new Stack<>();
    private Stack<String> ops = new Stack<>();

    // PRIVATE METHODS FOR THE pushPop METHODS
    private int minimum(Vector<Integer>arr)
    {
        int ans = Integer.MAX_VALUE;
        for (Integer x : arr) {
            if(x < ans)
            {
                ans = x;
            }
        }
        return ans;
    }

    private int maximum(Vector<Integer>arr)
    {
        int ans = Integer.MIN_VALUE;
        for (Integer x : arr) {
            if(x > ans)
            {
                ans = x;
            }
        }
        return ans;
    }

    private int gcd(int x, int y)
    {
        while(y > 0)
        {
            double temp = y;
            y = x % y;
            x = (int)temp;
        }
        return x;
    }

    private int lcm(int x, int y)
    {
        return x * (y / gcd(x,y));
    }

    private int vectorLCM(Vector<Integer>arr)
    {
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            ans = lcm(ans, arr.get(i));
        }
        return ans;
    }

    private int vectorGCD(Vector<Integer>arr)
    {   
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            ans = gcd(ans, arr.get(i));
        }
        return ans;
    }

    // PUBLIC METHODS ACCESSIBLE TO CLIENT
    public void pushValue(Integer val) 
    {
        values.push(val);
    }
    
    public void pushOperation(String operator)
    {
        ops.push(operator);

        // temp array
        Vector<Integer> temp = new Vector<>(values.size());
        // pop off of stack
        while(values.isEmpty() == false)
        {
            temp.add(values.pop());
        }
        // check if temp is non-zero size
        if(temp.size() != 0)
        {
            String op = ops.pop();
        
            switch (op) {
                case "min":
                    values.push(minimum(temp));
                    break;
                case "max":
                    values.push(maximum(temp));
                    break;
                case "gcd":
                    values.push(vectorGCD(temp));
                case "lcm":
                    values.push(vectorLCM(temp));
                default:
                    break;
        }
        }

    }

    public Integer pop() 
    {
        return values.pop();
    }

    public boolean isEmpty()
    {
        if(values.size() == 0)
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
            ans = values.pop();
        } catch (Exception e) {
            // handle exception
            System.err.println("Server exception: " + e.toString());
	        e.printStackTrace();
        }
        return ans;
    }
}