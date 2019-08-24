package stacksAndQueues;

import java.util.Scanner;

/**
 * A stack implementation which uses the linked list approach
 */
public class LinkedListStack<T> {
    Node first = null;
    int items = 0;

    class Node {
        T item;
        Node next;
    }

    public void push(T item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        items++;
    }

    public T pop() {
        if (isEmpty()) {
            new Exception("Cannot pop from an empty stack").printStackTrace();
        }
        T item = first.item;
        Node oldFirst = first;
        Node newFirst = oldFirst.next;
        first = newFirst;
        oldFirst = null;

        items--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(size());
        builder.append("[");
        Node bufferFirst = first;

        while (bufferFirst != null) {
            builder.append(bufferFirst.item.toString());

            if (bufferFirst.next != null) {
                builder.append(", ");
            }

            bufferFirst = bufferFirst.next;
        }

        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        final String USAGE_TEXT = "Usage: [arg] [item] \n Where \"arg\" is the stack operation (pop/push) and \"item\" is the item to push. \n Enter \"quit\" to exit. \n";

        LinkedListStack<String> stack = new LinkedListStack<>();
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
