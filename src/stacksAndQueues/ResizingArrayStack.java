package stacksAndQueues;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A stack implementation which uses the resizing array approach
 */
public class ResizingArrayStack<T> {
    private T[] items = null;

    private int aAccesses = 0;

    // Number of items in array and current item;
    private int N = 0;

    public ResizingArrayStack(int initialCapacity) {
        if (initialCapacity < 1) {
            items = (T[]) new Object[1];
        } else {
            items = (T[]) new Object[1];
        }
    }

    void push(T item) {
        if (N == items.length) {
            // Double the size of the array
            resizeArray(items.length * 2);
        }
        items[N++] = item;
        aAccesses++;

        System.out.println(String.format("N: %d,  Size[]: %d,  TA[]: %d", N, items.length, aAccesses));
        System.out.println(Arrays.toString(items));
    }

    private void resizeArray(int capacity) {
        T[] copy = (T[]) new Object[capacity];

        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
            aAccesses++;
        }
        items = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>(1);
        Scanner in = new Scanner(System.in);
        String item;
        while (!(item = in.next()).equals("quit")) {
            stack.push(item);
        }
    }
}
