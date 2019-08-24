package stacksAndQueues;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A stack implementation which uses the resizing array approach
 */
public class ResizingArrayStack<T> {
    private T[] items = null;

    // Number of items in array and current item;
    private int N = 0;

    public ResizingArrayStack(int initialCapacity) {
        if (initialCapacity < 1) {
            items = (T[]) new Object[4];
        } else {
            items = (T[]) new Object[initialCapacity];
        }
    }

    void push(T item) {
        if (N == items.length) {
            // Double the size of the items array when it gets full
            resizeArray(items.length * 2);
        }
        items[N++] = item;
    }

    public T pop() {
        T item = items[--N];
        items[N] = null;

//      Half the size of the array when it is one quarter full
        if (N > 0 && N == items.length / 4) {
            resizeArray(items.length / 2);
        }
        return item;
    }

    /**
     * Resizes the stack's items' array
     *
     * @param newCapacity is the new capacity of the items array
     */
    private void resizeArray(int newCapacity) {
        T[] copy = (T[]) new Object[newCapacity];

        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    public int size() {
        return N;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    //    For testing purposes
    public static void main(String[] args) {
        final String USAGE_TEXT = "Usage: [arg] [item] \n Where \"arg\" is the stack operation (pop/push) and \"item\" is the item to push. \n Enter \"quit\" to exit. \n";

        ResizingArrayStack<String> stack = new ResizingArrayStack<>(1);
        Scanner in = new Scanner(System.in);
        String input;
        String[] inputArgs;

        while (!(input = in.nextLine()).equals("quit")) {
            inputArgs = input.split(" ");

            if (inputArgs.length == 0) {
                System.out.print(USAGE_TEXT);
            }

            switch (inputArgs[0]) {
                case "pop":
                    System.out.println("Popped: " + stack.pop());
                    System.out.println(stack.toString());
                    System.out.println("Items in stack: " + stack.size());
                    break;
                case "push":
                    if (inputArgs.length < 2) {
                        System.out.println(USAGE_TEXT);
                        break;
                    }
                    stack.push(inputArgs[1]);
                    System.out.println(stack.toString());
                    break;
                default:
                    System.out.print(USAGE_TEXT);
            }
        }
    }


}
