package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {
    Node<T> stackHead;
    int stackSize;

    private static class Node<T> {
        T value;
        Node<T> next;

        private Node(T element, Node<T> next) {
            this.value = element;
            this.next = next;
        }
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> stack = new LinkedStack<>();
        Arrays.stream(elements).forEach(stack::push);
        return stack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        requireNonNull(element);
        stackHead = new Node<>(element, stackHead);
        stackSize++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<T> element = stackHead;
        stackHead = stackHead.next;
        stackSize--;
        return element.value;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.stackSize;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

}
