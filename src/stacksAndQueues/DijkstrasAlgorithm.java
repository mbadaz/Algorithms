package stacksAndQueues;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Dijkstra's algorithm utilizes stacks evaluates infix expression e.g (1 + (2 * 3))
 */
public class DijkstrasAlgorithm {

    private static final String operators = "*/+-";
    private ResizingArrayStack<Integer> valuesStack;
    private ResizingArrayStack<String> operatorStack;

    public DijkstrasAlgorithm() {
        valuesStack = new ResizingArrayStack<>(8);
        operatorStack = new ResizingArrayStack<>(8);
    }

    private int simplify(int firstVal, int secondVal, String operator) {

        switch (operator) {
            case "*":
                return firstVal * secondVal;
            case "/":
                return firstVal / secondVal;
            case "+":
                return firstVal + secondVal;
            case "-":
                return firstVal + secondVal;
            default:
                return 0;
        }
    }

    public int evaluate(String expression) {
        String[] inputString = expression.split("");

        for (int x = 0; x < inputString.length; x++) {

            if (operators.contains(inputString[x])) {
//                push operator to the operator stack
                operatorStack.push(inputString[x]);
            } else if (inputString[x].equals(")")) {
//                Pop first two values from the values stack and apply the first operator on them
//                then push the result back to the values stack
                int val2 = valuesStack.pop();
                int val1 = valuesStack.pop();
                valuesStack.push(simplify(val1, val2, operatorStack.pop()));
            } else if (inputString[x].equals("(") || inputString[x].equals(" ")) {
//                Do nothing
            } else {
//                push value to values stack.
                valuesStack.push(Integer.parseInt(inputString[x]));
            }
        }

        return valuesStack.pop();
    }

    public static void main(String[] args) {
        DijkstrasAlgorithm algorithm = new DijkstrasAlgorithm();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String input;

        while (!(input = sc.nextLine()).isEmpty()) {
            if (input.equals("quit")) {
                break;
            }
            System.out.println("The answer is: " + algorithm.evaluate(input));
        }
    }
}
