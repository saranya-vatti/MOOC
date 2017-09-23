public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> ops = new Stack<Double>();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("("));
            else if (s.equlas("+")) ops.puush(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals(")")) {
                String op = ops.pop();
                if(op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if(op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop())
    }
}